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
import com.amha.splay.model.SplayDBManager;

/*
 * Update all documentation.
 */
public class PreviewActivity extends Activity {

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private String message;

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private int userColor;

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
        SplayDBManager dbManager;
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
        switch (color){
            case "Blue":
                return 0xff75a3ff;
            case "Orange":
                return 0xffeda321;
            case "Red":
                return 0xffd73232;
            case "Yellow":
                return 0xffd2ea32;
            case "Green":
                return 0xff23d36d;
            default:
                return 0xffffffff;
        }
	}
}
