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
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.amha.splay.R;

public class AboutActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.about, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    /**
     * Method that responds to clicks on the about activity.
     *
     * @param v TextView from activity_about.xml
     */
    public void clickHandler(View v){
        if(v == null){
            return;
        }
        switch(v.getId()){

            case R.id.review_link:
                Intent mIntent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.amha.splay"));
                startActivity(mIntent);
                break;

            case R.id.feedback_link:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:amha.mogus@gmail.com"));

                intent.putExtra(Intent.EXTRA_EMAIL, "amha.mogus@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "'Splay It v0.2 - Feedback");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
        }
    }
}
