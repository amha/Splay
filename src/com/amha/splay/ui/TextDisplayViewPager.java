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

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import com.amha.splay.EditActivity;
import com.amha.splay.R;
import com.amha.splay.model.CursorPageAdapter;
import com.amha.splay.model.SplayDBManager;

/**
 * Activity that displays a Splay collection within a view pager.
 *
 * @author amogus
 */
public class TextDisplayViewPager extends FragmentActivity {

	private CursorPageAdapter mPageAdapter;
	private SplayDBManager dbManager;
    private ViewPager mPager;
    private Cursor mCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display_view_pager);

        Intent mIntent = getIntent();
        int selectedItem = mIntent.getIntExtra("SELECTED_LIST_ITEM", -1);

        dbManager = new SplayDBManager(getApplicationContext());
        dbManager.open();

        mCursor = dbManager.getAllMessagesAsCursor();
        mPageAdapter = new CursorPageAdapter(
                getSupportFragmentManager(),
                TextPagerFragment.class,
                mCursor);

		mPager = (ViewPager)findViewById(R.id.pager);
      	mPager.setAdapter(mPageAdapter);
        mPager.setCurrentItem(selectedItem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_display_view_pager, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final int currentCursorRecordId = mCursor.getCount() - mPager.getCurrentItem();

        switch(item.getItemId()){
            case R.id.action_edit:
                //Normalize number of DB records with number of pages

                Intent mIntent = new Intent(getApplicationContext(), EditActivity.class);
                mIntent.putExtra("CURRENT_RECORD_NUMBER", currentCursorRecordId);
                mIntent.putExtra("CURRENT_PAGE", mPager.getCurrentItem());
                startActivity(mIntent);
                return true;

           /** case R.id.action_remove:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton(R.string.postive_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        //Toast.makeText(getApplicationContext(), "Record = " + mCursor.getInt(0), Toast.LENGTH_SHORT).show();
                        //mPageAdapter.notifyDataSetChanged();

                        Log.d("AMHA", "Page Adapter Count = " + mPageAdapter.getCount());
                        Log.d("AMHA", "Current Pager Item = " + mPager.getCurrentItem());
                        Log.d("AMHA", "Cursor Count = " + mCursor.getCount());
                        Log.d("AMHA", "Cursor Column ID = " + mCursor.getInt(0));
                        Log.d("AMHA", "----");

                        boolean result = false;

                        if(mPager.getCurrentItem() == 0){
                            Log.d("AMHA", "Removing the first view");
                            result = dbManager.deleteMessage(mCursor.getInt(0));
                            mPager.removeView(mPager.getChildAt(0));

                        }
                        else{
                            result = dbManager.deleteMessage(mCursor.getInt(0));
                            mPager.removeView(mPager.getChildAt(mPager.getCurrentItem()));
                        }

                        if(result == true){
                            Log.d("AMHA", "Success! I deleted a record. ID = " + mCursor.getInt(0));

                            mPageAdapter.notifyDataSetChanged();
                            Intent mIntent = new Intent(getApplicationContext(), SplayMainActivity.class);
                            startActivity(mIntent);
                         }
                        else{
                            Log.d("AMHA", "Sad panda. I didn't delete database record.");
                        }

                    }
                }).setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.setTitle("Wanna Delete?");
                AlertDialog dialog = builder.create();
                dialog.show();
            */
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}