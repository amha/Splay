package com.amha.splay;

import android.app.ActionBar;
//import android.graphics.Color;
//import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
//import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * Fragment that displays the contents of 'Splay Text objects.
 * 
 * @author amogus
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

        //TODO: Define theme for application and remove formatting.
	    //mText.setPadding(10, 5, 10, 5);
        mText.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);


        //TODO: Add click handler, which will trigger a callback in "TextDisplayViewPager."
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: ...
                if(actionBar.isShowing()){
                    actionBar.hide();
                }
                else{
                    actionBar.show();
                }
            }
        });

        return v;
	}


	public TextPagerFragment newFragment(){
		return new TextPagerFragment();
	}
	
}