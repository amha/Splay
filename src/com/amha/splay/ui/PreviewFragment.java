package com.amha.splay.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amha.splay.R;

public class PreviewFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState){
	
		View v = inflater.inflate(R.layout.activity_preview, container, false);
		
		//TODO: Get form details and update UI
		
		return v;
	}

}
