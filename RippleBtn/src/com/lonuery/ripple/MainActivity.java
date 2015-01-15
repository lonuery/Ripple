package com.lonuery.ripple;

import android.media.SoundPool;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.app.Activity;
import com.lonuery.btnripple.R;
import com.lonuery.ripple.RippleLayout.RippleFinishListener;

public class MainActivity extends Activity{

	public static ImageButton add;
	SoundPool soundPool;
	private int soundId;
	private boolean flag = false; 
	
	int width,height;	
	public static RippleLayout ripple;
	float density;
	ViewStub viewStub;
	
	ImportMenuView menuView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		add = (ImageButton)findViewById(R.id.add);
        //soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        
        ripple = (RippleLayout)findViewById(R.id.more2);
        
        DisplayMetrics metircs = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics( metircs);
        width = metircs. widthPixels;
        height = metircs. heightPixels;        
		density  = metircs.density;
		
		menuView = (ImportMenuView)findViewById(R.id.main_activity_import_menu);
		menuView.setEnabled(false);
		
		ripple.setRippleFinishListener(new RippleFinishListener() {	
			@Override
			public void rippleFinish(int id) {
				
				if(id == R.id.more2){
					menuView.setVisibility(View.VISIBLE);
					menuView.setEnabled(true);
					menuView.setFocusable(true);				
					menuView.rl_closeVisiableAnimation();
					menuView.animation(MainActivity.this);
					menuView.bringToFront();
					ripple.setVisibility(View.GONE);
				}			
			}
		});	
	}
	
	public float dpiConvertToPixel(float dpi){		
		float pixel = dpi * density;	
		return pixel;
	}
	
	@Override
	protected void onDestroy() {
		if(soundPool!=null){
			soundPool.release();  
	        soundPool = null;  
		}
		super.onDestroy();
	}
}
