package com.amha.splay.ui;

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.amha.splay.R;
import com.amha.splay.TextDisplayViewPager;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.model.TextCursorAdapter;

public class TextListFragment extends ListFragment {

	private SplayDBManager dbManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){

		dbManager = new SplayDBManager(getActivity());
        dbManager.open();

		//Bundle args = getArguments();
		Cursor cursor = dbManager.getAllMessagesAsCursor();
		TextCursorAdapter adapter = null;

        /*
		if(args != null){
			String userInput1 = getArguments().getString("TABLE");
						
			if(userInput1.equals("internet")){
				cursor = dbManager.getAllNetAsCursor();
			}
			else{
				cursor = dbManager.getAllPositiveAsCursor();
			}
		} else{
			cursor = dbManager.getAllMessagesAsCursor();
		}*/
        
		ListView listUI = (ListView)inflater.inflate(R.layout.fragment_carta_list, container, false);	
	    adapter = new TextCursorAdapter(getActivity().getApplicationContext(), cursor, 0);
    	listUI.setAdapter(adapter);        
        
        return listUI;
	}
	@Override
	public void onListItemClick (ListView l, View v, int position, long id){

        Intent mIntent = new Intent(getActivity().getApplicationContext(), TextDisplayViewPager.class);
        mIntent.putExtra("SELECTED_LIST_ITEM", position);

        startActivity(mIntent);
		
		
	}
}
