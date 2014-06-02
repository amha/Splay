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
package com.amha.splay.ui;

import com.amha.splay.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class TextDisplayActivity extends Activity {

	//private SplayDBManager dbManager;	
	private String message;	
	private int userColor;

    ActionBar aBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_carta);
		
		aBar = getActionBar();
		
		//Get Message and Color values
		Intent intent = getIntent();
		message = intent.getStringExtra("userSelection");
		userColor = intent.getIntExtra("color", 0xffffffff);
		
		//Set Background Color
		RelativeLayout layout = new RelativeLayout(this);
		layout.setBackgroundColor(userColor);
		
		//Format Message
		TextView mTextView = new TextView(this);
		LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		mTextView.setLayoutParams(params);
		mTextView.setPadding(10, 5, 10, 5);
		mTextView.setText(message);
		mTextView.setTextSize(60);
		mTextView.setTypeface(Typeface.SANS_SERIF);
		mTextView.setTextColor(Color.WHITE);
		mTextView.setShadowLayer(1, 1, 1, Color.BLACK);
		
		mTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);		
				
		mTextView.setClickable(true);
		mTextView.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){				
			//TODO: ...
				if(aBar.isShowing()){
					aBar.hide();
				}
				else{
					aBar.show();
				}
			}
		});		
		layout.addView(mTextView);		
		setContentView(layout);
	}
				
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.view_carta, menu);
		return false;
	}
}