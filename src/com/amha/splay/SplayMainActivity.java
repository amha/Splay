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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.ui.AboutActivity;
import com.amha.splay.ui.CustomTextActivity;
import com.amha.splay.ui.TempFragment;
import com.amha.splay.ui.TextListFragment;

/**
 * Main activity that renders a list view of 'Splay messages.
 *
 */
public class SplayMainActivity extends FragmentActivity{
    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private TempFragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplayDBManager dbManager;
        dbManager = new SplayDBManager(this);
        dbManager.open();

        CreateDatabases c =  new CreateDatabases(getApplicationContext(), dbManager);
        c.execute();

        //On first run, create the list fragment
        if (savedInstanceState == null) {
            selectItem(99);
        }
        setContentView(R.layout.activity_splay_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messages, menu);
        return true;
    }

    /**
     * Respond to user selections from the overflow menu.
     *
     * @param item The Menu Item selected.
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        Intent mIntent;

    	switch (item.getItemId()){

            case R.id.action_new:
    			mIntent = new Intent(this, CustomTextActivity.class);
    			startActivity(mIntent);
    			return true;

            case R.id.action_about:
                mIntent = new Intent(this, AboutActivity.class);
                startActivity(mIntent);
                return true;

            case R.id.action_feedback:

                Intent intent = new Intent(Intent.ACTION_SENDTO);

                intent.setData(Uri.parse("mailto:amha.mogus@gmail.com"));
                intent.putExtra(Intent.EXTRA_EMAIL, "amha.mogus@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "'Splay It v0.5 - Feedback");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;

            default:
    			//TODO: Add other application actions.
    			//return true;
    		}
    		return super.onOptionsItemSelected(item);
    	        
    }

    /**
     * Loads fragment based on navigation panel selection.
     *
     * @param position
     */
    private void selectItem(int position) {

        TextListFragment cartaFragment;

    	// update selected item and title, then close the drawer
    	if(position == 1){
            Bundle args = new Bundle();
        	args.putString("TABLE", "positive");
        	cartaFragment = new TextListFragment();
        	cartaFragment.setArguments(args);
           	getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, cartaFragment)
                    .commit();
        }
        else if(position == 0){
        		Bundle args = new Bundle();
        		args.putString("TABLE", "internet");
        		cartaFragment = new TextListFragment();
        		cartaFragment.setArguments(args);
        		getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, cartaFragment)
                        .commit();
        }
        else if((position == 2)){
        		cartaFragment = new TextListFragment();
        		getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, cartaFragment)
                        .commit();
        }
        else if(position == 99){
        		cartaFragment = new TextListFragment();
        		getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, cartaFragment)
                        .commit();
        }
    }

    /**
     * Responsible for creating database tables using an AsyncTask.
     *
     */
    private class CreateDatabases extends AsyncTask<Void, Void, Void> {
    		
    	protected Context context;
    	private SplayDBManager dbManager;
    			
		public CreateDatabases(Context c, SplayDBManager db){
			this.context = c;
			this.dbManager = db;
		}
    			
    	@Override
		protected Void doInBackground(Void... arg0) {

            SharedPreferences pref = PreferenceManager
                    .getDefaultSharedPreferences(context);

            //Check shared preferences if this is the first time running the app.
            if(!pref.getBoolean("firstTime", false)) {
		        dbManager.populateDBTables();
                dbManager.close();
                //Database created, never run this code again.
		        SharedPreferences.Editor editor = pref.edit();
		        editor.putBoolean("firstTime", true);
		        editor.commit();
            }
            return null;
	    }
    }
}