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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SplaySQLiteHelper extends SQLiteOpenHelper{

	//Database variables.
	private static final String DATABASE_NAME = "splay.db";
	private static final int DATABASE_VERSION = 1;
	
	//Defining Schemas; Custom Messages
	protected static final String MESSAGES_TABLE = "messages";
	protected static final String COLUMN_ID = "_id";
	protected static final String COLUMN_MESSAGE = "message";
	protected static final String COLUMN_BG_COLOR = "bgColor";
	
	//Table creation statements.
	private static final String CREATE_MESSAGE_TABLE = "CREATE TABLE "
			+ MESSAGES_TABLE + " ( " + COLUMN_ID
			+ " INTEGER PRIMARY KEY autoincrement not null, "
			+ COLUMN_MESSAGE + " text not null, " 
			+ COLUMN_BG_COLOR + " INTEGER)";

	//Constructor
	public SplaySQLiteHelper(Context context){

        super(context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(CREATE_MESSAGE_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        Log.w(SplaySQLiteHelper.class.getName(),
				"Upgrading database from version "
                        + oldVersion
                        + " to "
                        + newVersion
                        + ", which will destroy all data");

		db.execSQL("DROP TABLE IF EXISTS " + MESSAGES_TABLE);
		onCreate(db);		
	}
}
