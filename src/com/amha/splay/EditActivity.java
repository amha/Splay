package com.amha.splay;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.amha.splay.model.SplayDBManager;
import com.amha.splay.ui.PreviewActivity;


public class EditActivity extends Activity {

    private SplayDBManager dbManager;
    private int recordID;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent args = getIntent();
        recordID = args.getIntExtra("CURRENT_RECORD_NUMBER", -1);
        currentPage = args.getIntExtra("CURRENT_PAGE", -1);

        dbManager = new SplayDBManager(this);
        dbManager.open();

        Cursor c = dbManager.getMessageRecord(recordID);
        c.moveToFirst();

        EditText editText = (EditText)findViewById(R.id.editMessageValue);
        editText.setText(c.getString(1));
        editText.setSelection(c.getString(1).length());

        //Populate spinner values
        ArrayAdapter<CharSequence> bgColorAdapter =
                ArrayAdapter.createFromResource(this, R.array.color_array, android.R.layout.simple_spinner_item);
        bgColorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Set spinner value to DB color value
        Spinner mSpinner = (Spinner)findViewById(R.id.editColorSpinner);
        mSpinner.setAdapter(bgColorAdapter);
        mSpinner.setSelection(bgColorAsString(c.getInt(2)));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.edit, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEditRecord(View view){
        //Get Form Values
        Spinner mSpinner = (Spinner)findViewById(R.id.editColorSpinner);
        int bgColor = PreviewActivity.convertBGColor(mSpinner.getSelectedItem().toString());
        EditText mEdit = (EditText)findViewById(R.id.editMessageValue);

        //Update Database
        int result = dbManager.updateMessageRecord(recordID, mEdit.getText().toString(), bgColor);

        Intent intent = new Intent(getApplicationContext(), TextDisplayViewPager.class);
        intent.putExtra("SELECTED_LIST_ITEM", currentPage);
        startActivity(intent);
    }

    public void cancelEdit(View v){
        finish();
    }

    /**
     * Helper method that maps a color value to its Array Adapter position.
     *
     * @param color integer value that is stored in the database.
     * @return Array Adapter position.
     */
    private int bgColorAsString(int color) {
        int id = 1;
        switch (color){
            case 0xff75a3ff:    //Blue
                id = 0;
                break;
            case 0xff23d36d:    //Green
                id = 1;
                break;
            case 0xffeda321:    //Orange
                id = 2;
                break;
            case 0xffd73232:    //Red
                id = 3;
                break;
            case 0xffd2ea32:    //Yellow
                id = 4;
                break;
        }
        return id;
    }

}