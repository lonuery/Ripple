package com.lonuery.newripple;

import com.lonuery.btnripple.R;
import android.os.Bundle;
import android.app.Activity;

public class NewMainActivity extends Activity {

	ImportMenuLayout menu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_main);
		
		menu = (ImportMenuLayout)findViewById(R.id.newripple);
		
	}
	
	
	
}
