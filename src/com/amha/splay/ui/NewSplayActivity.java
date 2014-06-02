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
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amha.splay.R;

public class NewSplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_color);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_color, menu);
		return true;
	}
	
	/*
	 * Retrieves input from form and passes that data to the Preview Activity.
	 * 
	 */
	public void previewMessage(View view){
		//Retrieve contents of the EditText view.
		
		Intent mIntent = getIntent();
		
		//EditText userMessage = (EditText)findViewById(R.id.editText1);		
		String message = mIntent.getStringExtra("userMessage");
		
		//Retrieve selected radio button.
		RadioGroup mRadioGroup = (RadioGroup)findViewById(R.id.color_selection);
		RadioButton mRadio = (RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());
		
		//Map selected radio button to a background color.
		int bgColor = 0;		
		switch (mRadio.getId()){
			case R.id.blue:
				bgColor = 0xff75a3ff;
				break;
			case R.id.orange:
				bgColor = 0xffeda321;
				break;
			case R.id.yellow:
				bgColor = 0xffd2ea32;
				break;
			case R.id.red:
				bgColor = 0xffd73232;
				break;
			case R.id.green:
				bgColor = 0xff23d36d;
		}
		
		//Add user entered data to an Intent and then start the Preview Activity.
		Intent intent = new Intent(this, PreviewActivity.class);
		intent.putExtra("userSelection", message);
		intent.putExtra("color", bgColor);
		startActivity(intent);
	}

}
