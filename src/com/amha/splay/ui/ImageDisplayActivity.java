package com.amha.splay.ui;

import com.amha.splay.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.ImageView;


public class ImageDisplayActivity extends Activity {

	LayoutInflater inflater;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_view);

		Intent intent = getIntent();
		String imageName = intent.getStringExtra("smileName");
		
		int imageId = getApplicationContext().getResources().getIdentifier(imageName,
                "drawable", getApplicationContext().getPackageName());
		
		ImageView mImage = (ImageView)findViewById(R.id.image_viewer_container);
		mImage.setImageResource(imageId);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_view, menu);
		return true;
	}

}
