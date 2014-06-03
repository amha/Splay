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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.amha.splay.R;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.ui.FormFragment.OnFormSubmittedListener;

/**
 * Displays a form that is used to create a slay message.
 *
 */
public class CustomTextActivity extends Activity implements OnFormSubmittedListener {

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private SplayDBManager dbManager;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_splay);

        EditText mEditText = (EditText)findViewById(R.id.form_view_edit_text);

        //Submit form when user hits "Done" softkey
        mEditText.setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    onFormSubmit(v);
                }

                return false;
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.new_message, menu);
		return false;
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

			saveMessage(userText, userColor);
            Toast.makeText(this, "You made a 'Splay! Awesome.", Toast.LENGTH_SHORT).show();

            //Lanuch new 'splay
            Intent intent = new Intent(this, TextDisplayViewPager.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

		}
	}

    public void onFormCancel(View v){
        finish();
    }

    /**
     * Creates a new database record in the Message table based on user input.
     *
     * @param message Text derived from user input.
     * @param color Background color derived from radio button selection.
     */
    public void saveMessage(String message, String color){
        dbManager = new SplayDBManager(this);
        dbManager.open();
        dbManager.createMessage(message, convertBGColor(color));
        dbManager.close();
    }


    /**
     * Converts the string name of a color into it's integer equivalent.
     *
     * @param color Color name, such as Blue or Red.
     * @return Hexidecimal representation of a color.
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