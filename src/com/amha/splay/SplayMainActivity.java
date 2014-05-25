/*
 * Copyright (C) 2013 Amha Mogus
 * 
 * Licensed under my own imagination, for which monetary profit or self-aggrandizement
 * undermine the quality and intention of the authors purpose.
 * 
 */
package com.amha.splay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.ui.CustomTextActivity;
import com.amha.splay.ui.TempFragment;
import com.amha.splay.ui.TextListFragment;

/**
 * The main activity, which represents a list of carta objects.
 */
public class SplayMainActivity extends FragmentActivity{

	private SplayDBManager dbManager;
	
	//OnFormSubmittedListener formListner;
	
	//Temp: Move this to a string resource.
	private final String[] navigationListValues = {"Internet Speak", "Praise", "Custom 'Splays" };
	
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle aBarToggle;
	private ListView navigationList;
	
	//The two fragments being created.
	private TextListFragment cartaFragment;
	//private SmileFragment smile;
	private TempFragment tempFragment;
	
	//private CartaDBManager dbManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splay_list);
        
        dbManager = new SplayDBManager(this);
        dbManager.open();
    
        CreateDatabases c =  new CreateDatabases(getApplicationContext(), dbManager);
        c.execute();
    
        //Get Drawer layout and set its background
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);   
        navigationList = (ListView)findViewById(R.id.navigation_drawer);


        View v = getLayoutInflater().inflate(R.layout.drawer_footer, null);
        navigationList.addFooterView(v);

        navigationList.setAdapter(new ArrayAdapter<String>(this, 
        		R.layout.drawer_list_item, navigationListValues));


        navigationList.setOnItemClickListener(new DrawerItemClickListener());
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);      
        
        aBarToggle = new ActionBarDrawerToggle(
        				this, 
        				drawerLayout, 
        				R.drawable.ic_drawer,
        				R.string.drawer_opened, 
        				R.string.drawer_closed) {

        				/** Called when a drawer has settled in a completely closed state. */
            			public void onDrawerClosed(View view) {
            				getActionBar().setTitle("Splay");
            				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            			}

            			/** Called when a drawer has settled in a completely open state. */
            			public void onDrawerOpened(View drawerView) {
            				getActionBar().setTitle("Splay Categories:");
            				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            			}
        			};
        	// Set the drawer toggle as the DrawerListener
        drawerLayout.setDrawerListener(aBarToggle);   
        
        //Temp: Update this with a more suitable default value.
        if (savedInstanceState == null) {
            selectItem(99);
        }                     
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.messages, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (aBarToggle.onOptionsItemSelected(item)) {
            return true;
        }
 
    	 //Click handler that responds to user action.
    	switch (item.getItemId()){
    		case R.id.action_new:
    			//Create new Carta by starting CreateCartaActivity
    			Intent mIntent = new Intent(this, CustomTextActivity.class);
    			startActivity(mIntent);
    			return true;

            default:
    			//TODO: Add other application actions.
    			//return true;
    		}
    		return super.onOptionsItemSelected(item);
    	        
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        aBarToggle.syncState();
    }
    
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = drawerLayout.isDrawerOpen(navigationList);
        menu.findItem(R.id.action_new).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    //May have to remove this method because no config changes.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        aBarToggle.onConfigurationChanged(newConfig);
    }

    /* The click listener for navigation drawer items. */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(
                AdapterView<?> parent,
                View view,
                int position,
                long id) {
        		    selectItem(position);
                }
    }
  
    //Responsible for loading fragment associated with user selection
    private void selectItem(int position) {
    	
    	//TODO: Add conditionals for other navigation items.
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
        
        //setTitle(navigationListValues[position]);
        navigationList.setItemChecked(position, true);
        drawerLayout.closeDrawer(navigationList);
    }

    private class CreateDatabases extends AsyncTask<Void, Void, Void> {
    		
    	Context context;
    	SplayDBManager dbManager;
    			
		public CreateDatabases(Context c, SplayDBManager db){
			this.context = c;
			this.dbManager = db;
		}
    			
    	@Override
		protected Void doInBackground(Void... arg0) {

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

            if(!pref.getBoolean("firstTime", false)) {
		        // run your one time code
		        dbManager.populateDBTables();

		        SharedPreferences.Editor editor = pref.edit();
		        editor.putBoolean("firstTime", true);
		        editor.commit();
		    }

            return null;
	    }
    }

}