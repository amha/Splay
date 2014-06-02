/*
 * Copyright (C) 2014 Amha Mogus amha.mogus@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amha.splay.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SplayDBManager {

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private SQLiteDatabase database;

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private SplaySQLiteHelper dbHelper;
	
	//Message Column Headers.
	protected static String[] allMessagesColumns = { 
			SplaySQLiteHelper.COLUMN_ID, 
			SplaySQLiteHelper.COLUMN_MESSAGE,
			SplaySQLiteHelper.COLUMN_BG_COLOR 
			};

	//Constructor.
	public SplayDBManager(Context context){
		dbHelper = new SplaySQLiteHelper(context);
	}

    /**
     * Opens a database that will be used for reading and writing.
     *
     * @throws SQLException
     */
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

    /**
     * Closes the SQLite database object.
     */
	public void close(){
		database.close();
	}
	
	public Text createMessage(String text, int bgColor){

        ContentValues values = new ContentValues();
		values.put(SplaySQLiteHelper.COLUMN_MESSAGE, text);
		values.put(SplaySQLiteHelper.COLUMN_BG_COLOR, bgColor);
		
		//Get column id
		long insertId = database.insert(
                SplaySQLiteHelper.MESSAGES_TABLE,
                null,
                values);

		//Retrieving the db_row that was created and converting to an object
		Cursor cursor = database.query(
                SplaySQLiteHelper.MESSAGES_TABLE, allMessagesColumns,
				SplaySQLiteHelper.COLUMN_ID + " = " + insertId,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        Text newMessage = cursorToMessage(cursor);
		cursor.close();

        return newMessage;
	}
	/*
	public boolean deleteMessage(int recordNumber){
        int result = database.delete(
                SplaySQLiteHelper.MESSAGES_TABLE,
                SplaySQLiteHelper.COLUMN_ID + "=" + recordNumber,
                null);

        if(result == 1)
            return true;
        else
            return false;
   	}*/
	
	/**
	 * Return all records from the Messages table.
     *
     * @return A cursor object with a records from the Messages table.
	 */
	public Cursor getAllMessagesAsCursor(){
		String orderBy = allMessagesColumns[0]+" DESC"; 
		Cursor cursor = database.query(
				SplaySQLiteHelper.MESSAGES_TABLE, 
				allMessagesColumns, 
				null, 
				null, 
				null,  
				null,
				orderBy,
				null);		
		cursor.moveToFirst();		
		return cursor;
	}
	
	/**
	 * Converts a database record into a Text object.
     *
	 */
	private Text cursorToMessage(Cursor cursor){

        Text message = new Text();

        message.setMessage(cursor.getString(1));
		message.setBgColor(cursor.getInt(2));

        return message;
	}

	public void populateDBTables(){
		
		/**
		createSmile("happy", Color.GREEN);
		createSmile("silly", Color.GREEN);
		createSmile("neutral", Color.GREEN);
		createSmile("sour", Color.CYAN);
		createSmile("sad", Color.MAGENTA);
		createSmile("random", Color.YELLOW);

		
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Good Vibes Only", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Never Give Up", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Think Positive", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "You're Awesome", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Thank You", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Keep Going", 0xfff62b19);

		addText(SplaySQLiteHelper.MESSAGES_TABLE, "=)", 0xff288df6);
		addText(SplaySQLiteHelper.MESSAGES_TABLE, "=(", 0xff288df6);
		addText(SplaySQLiteHelper.MESSAGES_TABLE, ";-)", 0xff288df6);
		addText(SplaySQLiteHelper.MESSAGES_TABLE, "8-]", 0xff288df6);
		addText(SplaySQLiteHelper.MESSAGES_TABLE, "=P", 0xff288df6);
         */

	}

    /**
     * Retrieves a database record from the messages table.
     *
     * @param ID The identifier of the database record to be retrieved.
     * @return
     */
    public Cursor getMessageRecord(int ID){
       //Creating the raw query.
       String query = "SELECT * FROM messages WHERE _id =" + ID;
       return database.rawQuery(query, null);
    }

    /**
     * Update a database record from the Messages table.
     *
     * @param id Unique row identifier.
     * @param text User supplied string.
     * @param bgColor The background color represented as an integer.
     * @return
     */
    public int updateMessageRecord(int id, String text, int bgColor){

        String whereClause = "_id = " + id;

        ContentValues contentValues = new ContentValues();
        contentValues.put("message", text);
        contentValues.put("bgColor", bgColor);

        return database.update("messages", contentValues, whereClause, null);
    }
}