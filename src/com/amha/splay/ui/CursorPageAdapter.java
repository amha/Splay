package com.amha.splay.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.amha.splay.TextPagerFragment;

/**
 *
 * @param <F>
 */
public class CursorPageAdapter<F extends Fragment> extends FragmentPagerAdapter {
	
	private final Class<TextPagerFragment> mFragment;
	private Cursor mCursor;

	public CursorPageAdapter(FragmentManager fm, Class<TextPagerFragment> fragment, Cursor cursor){
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

	public Cursor swapCursor(Cursor c){
		this.mCursor = c;
        return this.mCursor;
	}

    @Override
    public int getItemPosition(Object obj){
        return 0;
    }
}
