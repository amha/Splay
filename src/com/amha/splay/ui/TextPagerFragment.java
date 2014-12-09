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

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amha.splay.R;

/**
 * Fragment that displays the contents of 'Splay Text objects.
 * 
 * @author Amha Mogus
 *
 */
public class TextPagerFragment extends Fragment {

    private ActionBar actionBar;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        actionBar = getActivity().getActionBar();

		View v = inflater.inflate(R.layout.fragment_text_pager, container, false);
	
		//Applying Styles to fragment text
		TextView mText = (TextView)v.findViewById(R.id.text_fragment_output);
		
		Bundle args = getArguments();
		if(args != null){
            mText.setText(getArguments().getString("splay_message"));
            v.setBackgroundColor(getArguments().getInt("splay_color"));
		}
		else{
			mText.setText("Not passing args to TextPagerFragment.");
		}

        switch (Build.VERSION.SDK_INT){
            case 19:
                //Enable Immersive Mode for KitKat
                getActivity().getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
            break;

            case 16:case 17:case 18:
                //Hide action bar for Jelly Bean
                mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(actionBar.isShowing()){
                        actionBar.hide();
                    }
                    else{
                        actionBar.show();
                    }
                    }
            });
            break;
        }

        return v;
	}
}