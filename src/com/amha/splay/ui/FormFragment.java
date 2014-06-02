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

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.amha.splay.R;

public class FormFragment extends Fragment {
	
	private OnFormSubmittedListener formListner;
	private EditText mEditText;
    private String selectedRadio = "Red";
    private RadioGroup mGroup;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState){
		
		//Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.form_view, container, false);
		mEditText = (EditText)v.findViewById(R.id.form_view_edit_text);

        //Initialize radio group
        mGroup = (RadioGroup)v.findViewById(R.id.radioGroup);
		return v;
	}

	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
            OnFormSubmittedListener formListner = (OnFormSubmittedListener)activity;

        }catch (ClassCastException e){
            throw new ClassCastException(
                    activity.toString() + "needs to implement OnFormListener interface.");
		}
	}

	public String[] getFormData(){
		String[] formValues = new String[2];
		formValues[0] = mEditText.getText().toString();

        //Reading color value from selected radio button
        switch(mGroup.getCheckedRadioButtonId()){
            case R.id.redRadioButton:
                selectedRadio = "Red";
                break;
            case R.id.blueRadioButton:
                selectedRadio = "Blue";
                break;
            case R.id.yellowRadioButton:
                selectedRadio = "Yellow";
                break;
            case R.id.orangeRadioButton:
                selectedRadio = "Orange";
                break;
            case R.id.greenRadioButton:
                selectedRadio = "Green";
                break;
        }
        formValues[1] = selectedRadio;
		return formValues;
	}

    /**
     * This interface is implemented in CustomTextActivity.class
     */
    public interface OnFormSubmittedListener {
        public void onFormSubmit(View v);
        public void onFormCancel(View v);
    }
}