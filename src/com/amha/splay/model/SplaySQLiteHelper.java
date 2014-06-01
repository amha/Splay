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
	
	//Smile
	protected static final String SMILE_TABLE = "smiles";
	protected static final String SMILE_COLUMN_ID = "_id";
	protected static final String SMILE_COLUMN_IMAGE_PATH = "imagePath";
	protected static final String SMILE_COLUMN_BACKGROUND_COLOR = "backgroundColor";

	//Positive
	protected static final String POSITIVE_TABLE = "positive";
	protected static final String POSITIVE_ID = "_id";
	protected static final String POSITIVE_TEXT = "text";
	protected static final String POSITIVE_BG_COLOR = "color";
	
	//Net
	protected static final String NET_TABLE = "internet_slang";
	protected static final String NET_ID = "_id";
	protected static final String NET_TEXT = "text";
	protected static final String NET_BG_COLOR = "color";
	
	//Table creation statements.
	private static final String CREATE_MESSAGE_TABLE = "CREATE TABLE "
			+ MESSAGES_TABLE + " ( " + COLUMN_ID
			+ " INTEGER PRIMARY KEY autoincrement not null, "
			+ COLUMN_MESSAGE + " text not null, " 
			+ COLUMN_BG_COLOR + " INTEGER)";

	private static final String CREATE_SMILE_TABLE = "CREATE TABLE "
			+ SMILE_TABLE + " ( " + SMILE_COLUMN_ID
			+ " INTEGER PRIMARY KEY autoincrement, "
			+ SMILE_COLUMN_IMAGE_PATH + " text, " 
			+ SMILE_COLUMN_BACKGROUND_COLOR + " INTEGER)";
	
	private static final String CREATE_POSITIVE_TABLE = "CREATE TABLE "
			+ POSITIVE_TABLE + " ( " + POSITIVE_ID
			+ " INTEGER PRIMARY KEY autoincrement, "
			+ POSITIVE_TEXT + " text not null, "
			+ POSITIVE_BG_COLOR + " INTEGER)";
	
	private static final String CREATE_NET_TABLE = "CREATE TABLE "
			+ NET_TABLE + " ( " + NET_ID
			+ " INTEGER PRIMARY KEY autoincrement, "
			+ NET_TEXT + " text not null, "
			+ NET_BG_COLOR + " INTEGER)";
	
	//Constructor
	public SplaySQLiteHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database){
		database.execSQL(CREATE_MESSAGE_TABLE);
		database.execSQL(CREATE_SMILE_TABLE);
		database.execSQL(CREATE_POSITIVE_TABLE);
		database.execSQL(CREATE_NET_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		Log.w(SplaySQLiteHelper.class.getName(), 
				"Upgrading database from version " + oldVersion + 
				" to " + newVersion + ", which will destroy all data");
		db.execSQL("DROP TABLE IF EXISTS " + MESSAGES_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + SMILE_TABLE);
	
		onCreate(db);		
	}
}
