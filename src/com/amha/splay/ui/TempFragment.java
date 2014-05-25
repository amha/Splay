package com.amha.splay.ui;

import com.amha.splay.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TempFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
	
		View v = inflator.inflate(R.layout.temp, container, false);
		return v;
	}
}
