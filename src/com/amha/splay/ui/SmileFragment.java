package com.amha.splay.ui;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.amha.splay.R;
import com.amha.splay.model.SmileCursorAdapter;
import com.amha.splay.model.SplayDBManager;

public class SmileFragment extends Fragment{
	
	//private String[] smile_names = {"happy"};
	private SplayDBManager dbManager;
	
	@Override
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
		
		//Instantiate database manager and open connection for read/write actions.
        dbManager = new SplayDBManager(getActivity());
        dbManager.open();     
        
        //Temp: Review this.
        /**
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(!pref.getBoolean("firstTime", false)) {
            // run your one time code
        		dbManager.populateSmileTable(getActivity());           
        		SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }*/
        
        //Retrieve all records from the database.
        Cursor cursor = dbManager.getAllSmilesAsCursor();
        SmileCursorAdapter adapter = new SmileCursorAdapter(getActivity().getApplicationContext(), cursor, 0);
		
		//Inflate the layout for this fragment
        GridView grid = (GridView)inflator.inflate(R.layout.fragment_smile, container, false);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new OnItemClickListener(){
        		public void onItemClick(AdapterView<?> parent, View v, int position, long flags){
        			
        			TextView selectedView = (TextView)v.findViewById(R.id.smile_text);
        			String selectedImage = selectedView.getText().toString();
        			
        			Intent intent = new Intent(getActivity(), ImageDisplayActivity.class);
        			intent.putExtra("smileName", selectedImage);
        			startActivity(intent);
        		}
        });
        return grid;
	}
}