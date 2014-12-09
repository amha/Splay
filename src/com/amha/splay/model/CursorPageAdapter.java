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

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.amha.splay.ui.TextPagerFragment;

/**
 *
 * @param <F>
 */
public class CursorPageAdapter<F extends Fragment> extends FragmentPagerAdapter {

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
	private final Class<TextPagerFragment> mFragment;

    /**
     * This field should be made private, so it is hidden from the SDK.
     *
     */
    private final Cursor mCursor;

	public CursorPageAdapter(FragmentManager fm,
                             Class<TextPagerFragment> fragment,
                             Cursor cursor){
		super(fm);
		this.mFragment = fragment;
		this.mCursor = cursor;
	}

	@Override
	public Fragment getItem(int position) {
		
		if(mCursor == null){
			return null;
		}

		F currentFragment;
        mCursor.moveToPosition(position);

       	try{
			currentFragment = (F)mFragment.newInstance();
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}

		Bundle args = new Bundle();
		args.putString("splay_message", mCursor.getString(1));
        args.putInt("splay_color", mCursor.getInt(2));
		currentFragment.setArguments(args);

		return currentFragment;
	}

	@Override
	public int getCount() {
		if(mCursor == null){
			return 0;
		}
		else{
			return mCursor.getCount();
		}
	}

    @Override
    public int getItemPosition(Object obj){
        return 0;
    }
}
