package com.amha.splay;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.ui.CursorPageAdapter;
//import com.amha.splay.EditActivity;

/**
 * Activity that displays a Splay collection within a view pager.
 *
 * @author amogus
 */
public class TextDisplayViewPager extends FragmentActivity {

	private CursorPageAdapter mPageAdapter;
	//private TextPagerFragment frag;
	private SplayDBManager dbManager;
    private ViewPager mPager;
    private Cursor mCursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_display_view_pager);

        Intent mIntent = getIntent();
        int selectedItem = mIntent.getIntExtra("SELECTED_LIST_ITEM", -1);

        dbManager = new SplayDBManager(getApplicationContext());
        dbManager.open();

        //TODO: Retreiving records from a single table. Extend this for other collections.
        mCursor = dbManager.getAllMessagesAsCursor();

        mPageAdapter = new CursorPageAdapter(
                getSupportFragmentManager(),
                TextPagerFragment.class,
                mCursor);

		mPager = (ViewPager)findViewById(R.id.pager);
      	mPager.setAdapter(mPageAdapter);
        mPager.setCurrentItem(selectedItem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text_display_view_pager, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_edit:
                //Normalize number of DB records with number of pages
                int currentCursorRecordId =  mCursor.getCount() - mPager.getCurrentItem();

                Intent mIntent = new Intent(getApplicationContext(), EditActivity.class);
                mIntent.putExtra("CURRENT_RECORD_NUMBER", currentCursorRecordId);
                mIntent.putExtra("CURRENT_PAGE", mPager.getCurrentItem());
                startActivity(mIntent);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}