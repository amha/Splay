package com.amha.splay.model;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amha.splay.R;

public class SmileCursorAdapter extends CursorAdapter {

	private LayoutInflater inflater;
	
	public SmileCursorAdapter(Context context, Cursor cursor, int flags){
		super(context, cursor, flags);
		inflater = LayoutInflater.from(context);
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor){
		
		String smileName = (String)cursor.getString(1);
		
		//Bing Image
		ImageView img = (ImageView)view.findViewById(R.id.smiley);
		int imageId = context.getResources().getIdentifier(smileName,
                "drawable", context.getPackageName());
		img.setImageResource(imageId);
		
		//Bing Text
		TextView text = (TextView)view.findViewById(R.id.smile_text);
		text.setText(smileName);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent){	
		View v = inflater.inflate(R.layout.smile_row, parent, false);
		return v;
	}
}
