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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.amha.splay.R;
import com.amha.splay.ui.FormFragment.OnFormSubmittedListener;


/**
 * Form where users create Splay's
 *
 */
public class CustomTextActivity extends Activity implements OnFormSubmittedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_splay);		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_message, menu);
		return true;
	}	
 
	public void onFormSubmit(View v){

		//Pass Form Data to Preview
		TextView mTextView = (TextView)findViewById(R.id.form_view_edit_text);
		
		if(mTextView.getText().length() < 1){

			Toast.makeText(this, "You gotta enter some text.", Toast.LENGTH_SHORT).show();

        }else{

            FormFragment mFormFragment = (FormFragment)getFragmentManager().findFragmentById(R.id.form);

			String[] userData = mFormFragment.getFormData();
			String userText = userData[0];
			String userColor = userData[1];

			Intent mIntent = new Intent(this, PreviewActivity.class);		
			mIntent.putExtra("TEXT", userText);
			mIntent.putExtra("COLOR", userColor);

            startActivity(mIntent);

		}
	}

    public void onFormCancel(View v){
        finish();
    }

}