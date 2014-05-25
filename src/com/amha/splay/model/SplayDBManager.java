package com.amha.splay.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SplayDBManager {
	//SQLite references.
	private SQLiteDatabase database;
	private SplaySQLiteHelper dbHelper;
	
	//Message Column Headers.
	protected static String[] allMessagesColumns = { 
			SplaySQLiteHelper.COLUMN_ID, 
			SplaySQLiteHelper.COLUMN_MESSAGE,
			SplaySQLiteHelper.COLUMN_BG_COLOR 
			};

	//Smile Column Headers.	
	protected static String[] allSmileColumns = {
			SplaySQLiteHelper.SMILE_COLUMN_ID,
			SplaySQLiteHelper.SMILE_COLUMN_IMAGE_PATH,
			SplaySQLiteHelper.SMILE_COLUMN_BACKGROUND_COLOR
	};
	
	//Positive Column Headers.
	protected static String[] allPositiveColumns = {
			SplaySQLiteHelper.POSITIVE_ID,
			SplaySQLiteHelper.POSITIVE_TEXT,
			SplaySQLiteHelper.POSITIVE_BG_COLOR
	};
	
	//Internet Column Headers.
	protected static String[] allNetColumns = {
		SplaySQLiteHelper.NET_ID,
		SplaySQLiteHelper.NET_TEXT,
		SplaySQLiteHelper.NET_BG_COLOR
	};
	
	//Constructor. 
	public SplayDBManager(Context context){
		dbHelper = new SplaySQLiteHelper(context);
	}
	
	//Open connection to messages database.
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	//Close connection to messages database.
	public void close(){
		database.close();
	}
	
	public Text createMessage(String text, int bgColor){
		ContentValues values = new ContentValues();
		values.put(SplaySQLiteHelper.COLUMN_MESSAGE, text);
		values.put(SplaySQLiteHelper.COLUMN_BG_COLOR, bgColor);
		
		//Get column id
		long insertId = database.insert(SplaySQLiteHelper.MESSAGES_TABLE, null, values);

		//Retrieving the db_row that was created  and converting to an object
		Cursor cursor = database.query(SplaySQLiteHelper.MESSAGES_TABLE, allMessagesColumns, 
				SplaySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
		cursor.moveToFirst();
		Text newMessage = cursorToMessage(cursor);
		cursor.close();
		return newMessage;
	}
	
	public void deleteMessage(Text message){
		//TODO:	
	}
	
	/*
	 * Get all records from the Messages table as a ListView<Carta>
	 */
	public List<Text> getAllMessages(){
		List<Text> messages = new ArrayList<Text>();
		Cursor cursor = database.query(
				SplaySQLiteHelper.MESSAGES_TABLE, 
				allMessagesColumns, 
				null, 
				null, 
				null, 
				null, 
				null);
		
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			Text message = cursorToMessage(cursor);
			messages.add(message);
			cursor.moveToNext();
		}
		cursor.close();
		return messages;
	}

	/*
	 * Return all records from the Messages table.
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
	
	/*
	 * Helper method that converts a database record into a Carta object.
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
		*/
		
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Good Vibes Only", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Never Give Up", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Think Positive", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "You're Awesome", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Thank You", 0xfff62b19);
		addText(SplaySQLiteHelper.POSITIVE_TABLE, "Keep Going", 0xfff62b19);

		addText(SplaySQLiteHelper.NET_TABLE, "=)", 0xff288df6);
		addText(SplaySQLiteHelper.NET_TABLE, "=(", 0xff288df6);
		addText(SplaySQLiteHelper.NET_TABLE, ";-)", 0xff288df6);
		addText(SplaySQLiteHelper.NET_TABLE, "8-]", 0xff288df6);
		addText(SplaySQLiteHelper.NET_TABLE, "=P", 0xff288df6);
	}
	
	
	public void createSmile(String image_path, int color){
		ContentValues values = new ContentValues();
		values.put(SplaySQLiteHelper.SMILE_COLUMN_IMAGE_PATH, image_path);
		values.put(SplaySQLiteHelper.SMILE_COLUMN_BACKGROUND_COLOR, color);
		database.insert(SplaySQLiteHelper.SMILE_TABLE, null, values);		
	}
	
	/*
	 * Return all records from the Smile table.
	 */
	public Cursor getAllSmilesAsCursor(){
		Cursor cursor = database.query(
				SplaySQLiteHelper.SMILE_TABLE, 
				allSmileColumns, 
				null, 
				null, 
				null, 
				null, 
				null);
		
		cursor.moveToFirst();		
		return cursor;
	}
	
	/**
	 * Creates a text entry within the database.
	 * 
	 */
	public void addText(String tableName, String text, int bgColor){
		//Create object that will be entered into a table.
		ContentValues values = new ContentValues();
		
		if(tableName.equals("positive")){
			values.put(allPositiveColumns[1], text);
			values.put(allPositiveColumns[2], bgColor);	
		} 
		else if(tableName.equals("internet_slang")){
			values.put(allNetColumns[1], text);
			values.put(allNetColumns[2], bgColor);		
		}
		
		database.insert(tableName, null, values);
	}
	
	public Cursor getText(String tableName, String[] columns){
		Cursor c = database.query(
				tableName, 
				columns, 
				null, 
				null, 
				null, 
				null, 
				null);
		c.moveToFirst();
		return c;
	}
	
	
	/*
	 * Return all records from the Messages table.
	 */
	public Cursor getAllPositiveAsCursor(){
		Cursor cursor = database.query(
				SplaySQLiteHelper.POSITIVE_TABLE, 
				allPositiveColumns, 
				null, 
				null, 
				null, 
				null, 
				null);		
		cursor.moveToFirst();		
		return cursor;
	}
	
	/*
	 * Return all records from the Messages table.
	 */
	public Cursor getAllNetAsCursor(){
		Cursor cursor = database.query(
				SplaySQLiteHelper.NET_TABLE, 
				allNetColumns, 
				null, 
				null, 
				null, 
				null, 
				null);		
		cursor.moveToFirst();		
		return cursor;
	}

    public Cursor getMessageRecord(int ID){

       String query = "SELECT * FROM messages WHERE _id =" + ID;
       Cursor cursor = database.rawQuery(query, null);

       return cursor;
    }

    public int updateMessageRecord(int id, String text, int bgColor){

        int rowsaffected;
        String whereClause = "_id = " + id;

        ContentValues contentValues = new ContentValues();
        contentValues.put("message", text);
        contentValues.put("bgColor", bgColor);

        rowsaffected = database.update("messages", contentValues, whereClause, null);

        return rowsaffected;
    }
}
