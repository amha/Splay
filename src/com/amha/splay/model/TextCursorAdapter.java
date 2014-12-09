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
package com.amha.splay.model;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.amha.splay.R;

import java.util.Locale;

/*
 *  Maps records from the Carta database to the Carta Row file.
 */
public class TextCursorAdapter extends CursorAdapter {

	private final LayoutInflater inflater;
	
	public TextCursorAdapter(Context context, Cursor cursor, int flags){
		super(context, cursor, flags);		
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor){

        //Get Color
        String colorAsString = "#" + Integer.toHexString(
                cursor.getInt(2))
                .toUpperCase(Locale.US)
                .substring(2);

        //Get Row Number
        TextView rowNumber = (TextView)view.findViewById(R.id.row_number);
        GradientDrawable mBackground =  (GradientDrawable)rowNumber.getBackground();

        mBackground.setColor(Color.parseColor(colorAsString));

		//Get Text
		TextView message = (TextView)view.findViewById(R.id.row_text);
		message.setText(cursor.getString(1));

	}	
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent){	
		return inflater.inflate(R.layout.splay_row, parent, false);
		//return v;
	}
	
}
