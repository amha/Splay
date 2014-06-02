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

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.amha.splay.R;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.model.TextCursorAdapter;

public class TextListFragment extends ListFragment {

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private SplayDBManager dbManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){

		dbManager = new SplayDBManager(getActivity());
        dbManager.open();

		Cursor cursor = dbManager.getAllMessagesAsCursor();
		TextCursorAdapter adapter = null;

		ListView listUI = (ListView)inflater.inflate(
                R.layout.fragment_carta_list,
                container,
                false);

	    adapter = new TextCursorAdapter(
                getActivity().getApplicationContext(),
                cursor,
                0);

    	listUI.setAdapter(adapter);
        return listUI;
	}
	@Override
	public void onListItemClick (ListView l, View v, int position, long id){

        Intent mIntent = new Intent(
                getActivity().getApplicationContext(),
                TextDisplayViewPager.class);

        mIntent.putExtra("SELECTED_LIST_ITEM", position);
        startActivity(mIntent);
	}
}
