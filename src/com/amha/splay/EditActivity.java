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
package com.amha.splay;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.ui.TextDisplayViewPager;

/**
 * A form that edits a Splay object.
 *
 * @author Amha Mogus
 */
public class EditActivity extends Activity {

    private SplayDBManager dbManager;
    private int recordID;
    private int currentPage;
    private RadioGroup mRadios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent args = getIntent();
        recordID = args.getIntExtra("CURRENT_RECORD_NUMBER", -1);
        currentPage = args.getIntExtra("CURRENT_PAGE", -1);

        dbManager = new SplayDBManager(this);
        dbManager.open();

        Cursor c = dbManager.getMessageRecord(recordID);
        c.moveToFirst();

        EditText editText = (EditText)findViewById(R.id.editMessageValue);
        editText.setText(c.getString(1));
        editText.setSelection(c.getString(1).length());

        mRadios = (RadioGroup)findViewById(R.id.radioGroupEdit);
        mRadios.check(bgColorAsString(c.getInt(2)));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.edit, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEditRecord(View view){
        //Get Form Values
        //Spinner mSpinner = (Spinner)findViewById(R.id.editColorSpinner);
        //int bgColor = PreviewActivity.convertBGColor(mSpinner.getSelectedItem().toString());
        EditText mEdit = (EditText)findViewById(R.id.editMessageValue);

        //Update Database
       // int result =
                dbManager.updateMessageRecord(
                        recordID,
                        mEdit.getText().toString(),
                        convertIDToColor(mRadios.getCheckedRadioButtonId()));

        Toast.makeText(this, "You edited your 'Splay. Cool.", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(getApplicationContext(), TextDisplayViewPager.class);
        intent.putExtra("SELECTED_LIST_ITEM", currentPage);
        startActivity(intent);
    }

    public void cancelEdit(View v){
        finish();
    }

    /**
     * Helper method that maps a color value to its Array Adapter position.
     *
     * @param color integer value that is stored in the database.
     * @return Array Adapter position.
     */
    private int bgColorAsString(int color) {
        int radioID = 0;
        switch (color){
            case 0xff2196F3:    // Blue
                radioID = R.id.blueRadioButtonEdit;
                break;
            case 0xff4CAF50:    // Green
                radioID = R.id.greenRadioButtonEdit;
                break;
            case 0xffFF9800:    // Orange
                radioID = R.id.orangeRadioButtonEdit;
                break;
            case 0xffF44336:    // Red
                radioID = R.id.redRadioButtonEdit;
                break;
            case 0xffFFEB3B:    // Yellow
                radioID = R.id.yellowRadioButtonEdit;
                break;
        }
        return radioID;
    }

    public int convertIDToColor(int color){

        switch (color){
            case R.id.blueRadioButtonEdit:
                return 0xff2196F3;
            case R.id.orangeRadioButtonEdit:
                return 0xffFF9800;
            case R.id.redRadioButtonEdit:
                return 0xffF44336;
            case R.id.yellowRadioButtonEdit:
                return 0xffFFEB3B;
            case R.id.greenRadioButtonEdit:
                return 0xff4CAF50;
            default:
                return 0xffffffff;
        }
    }
}