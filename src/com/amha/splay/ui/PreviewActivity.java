/*
 * Copyright (C) 2013 Amha Mogus
 * 
 * Licensed under my own imagination, for which monetary profit or self-aggrandizement
 * undermine the quality and intention of the authors purpose.
 * 
 */
package com.amha.splay.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amha.splay.R;
import com.amha.splay.SplayMainActivity;
import com.amha.splay.TextDisplayViewPager;
import com.amha.splay.model.SplayDBManager;

/*
 * Update all documentation.
 */
public class PreviewActivity extends Activity {

	private SplayDBManager dbManager;	
	private String message;	
	private int userColor;
	private int textColor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_preview);

		//Get Message and Color values
		Intent intent = getIntent();
		message = intent.getStringExtra("TEXT");
		userColor = convertBGColor(intent.getStringExtra("COLOR"));

		TextView mTextView = (TextView)findViewById(R.id.preview_text);
		mTextView.setText(message);

		RelativeLayout mLayout = (RelativeLayout)findViewById(R.id.preview);
		mLayout.setBackgroundColor(userColor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		/* Inflate the menu; this adds items to the action bar if it is present. */
		//getMenuInflater().inflate(R.menu.preview, menu);
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		/*
        switch(item.getItemId()){
		case R.id.action_save:
			saveMessage(this);
			return true;
		case R.id.action_remove:
			Intent intent = new Intent(this, SplayMainActivity.class);
			startActivity(intent);
		}*/
		return false;
	}
	
	/**
	 * Creates a new database record based on the users input and 
	 * calls the CartaListActivity.
     *
	 */
	public void saveMessage(View v){
		dbManager = new SplayDBManager(this);
        dbManager.open();
        dbManager.createMessage(message, userColor);
        dbManager.close();

        //Lanuch new 'splay
        Intent intent = new Intent(this, TextDisplayViewPager.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
	}

    /**
     * Return to the Main Activity.
     *
     */
    public void cancelPreview (View v){
        Intent intent = new Intent(this, SplayMainActivity.class);
        startActivity(intent);
    }

	/**
	 * Converts string that represents color into it's integer equivalent.
     *
	 */
	public static int convertBGColor(String color){
		
		if(color.equals("Blue")){
			return 0xff75a3ff;
		}
		else if(color.equals("Orange")){
			return 0xffeda321;
		}
		else if(color.equals("Red")){
			return 0xffd73232;
		}
		else if(color.equals("Yellow")){
			return 0xffd2ea32;
		}
		else if(color.equals("Green")){
			return 0xff23d36d;
		}
		else if(color.equals("Black")){
			return 0xff000000;
		}
		return 0xffffffff;
	}
}
