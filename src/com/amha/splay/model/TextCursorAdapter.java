/*
 * Copyright (C) 2013 Amha Mogus
 * 
 * Licensed under my own imagination, for which monetary profit or self-aggrandizement
 * undermine the quality and intention of the authors purpose.
 * 
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

	private LayoutInflater inflater;
	
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
		return inflater.inflate(R.layout.carta_row, parent, false);
		//return v;
	}
	
}
