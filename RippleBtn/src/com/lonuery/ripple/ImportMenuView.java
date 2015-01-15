package com.lonuery.ripple;

import com.lonuery.btnripple.R;
import com.lonuery.ripple.RippleLayout.RippleFinishListener;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class ImportMenuView extends RelativeLayout implements RippleFinishListener,View.OnClickListener{
	
	Context context=null;
	
	RippleLayout rl_close,first_ball,rl_pc,rl_sdcard;
	View view_shadow;
	
	public ImportMenuView(Context context) {
		super(context);
	}
	public ImportMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context,attrs);
	}
	public ImportMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context,attrs);
	}
	
	public void init(Context context, AttributeSet attrs){
		LayoutInflater.from(context).inflate(R.layout.widget_import_menu, this, true);
		
		rl_close = (RippleLayout)findViewById(R.id.rl_close);
		first_ball = (RippleLayout)findViewById(R.id.first_ball);
		rl_pc = (RippleLayout)findViewById(R.id.rl_pc);
		rl_sdcard = (RippleLayout)findViewById(R.id.rl_sdcard);
		
		rl_close.setRippleFinishListener(this);
		first_ball.setRippleFinishListener(this);
		rl_pc.setRippleFinishListener(this);
		rl_sdcard.setRippleFinishListener(this);
		
		view_shadow = (View)findViewById(R.id.v_shadow);
		view_shadow.setOnClickListener(this);
		
		setVisibility(View.GONE);
		
		this.context = context;
	}
	
	public void animation(Context context){
		
		Animation first_ball_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_qrcode_button);
		first_ball.startAnimation(first_ball_anim);
		
		Animation rl_pc_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_pc_button);
		rl_pc.startAnimation(rl_pc_anim);
		
		Animation rl_sdcard_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_sdcard_button);
		rl_sdcard.startAnimation(rl_sdcard_anim);	
		
		Animation view_fade_in_anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);
		view_shadow.startAnimation(view_fade_in_anim);
	}
	
	public void animationOut(){
		Animation first_ball_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_qrcorde_button_out);
		first_ball.startAnimation(first_ball_anim);
		
		Animation rl_pc_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_pc_button_out);
		rl_pc.startAnimation(rl_pc_anim);
		
		Animation rl_sdcard_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_sdcard_button_out);
		rl_sdcard.startAnimation(rl_sdcard_anim);
		
		first_ball_anim.setAnimationListener(new AnimationListener() {		
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				Animation view_fade_in_anim = AnimationUtils.loadAnimation(context, R.anim.fade_out);			
				rl_closeUnVisiableAnimation();
				view_shadow.startAnimation(view_fade_in_anim);
			}
		});
	}
	
	public void rl_closeVisiableAnimation(){		
		ObjectAnimator object = ObjectAnimator.ofFloat(rl_close, "rotation", 0, -45);
		object.setDuration(250);
		object.setRepeatCount(0);
		object.start();
	}
	
	public void rl_closeUnVisiableAnimation(){
		
		ObjectAnimator object = ObjectAnimator.ofFloat(rl_close, "rotation", -45, 0);
		object.setDuration(200);
		object.setRepeatCount(0);
		object.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {				
				MainActivity.ripple.setVisibility(View.VISIBLE);
				setVisibility(View.GONE);
				setEnabled(false);
				setFocusable(false);
				MainActivity.ripple.bringToFront();
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {		
			}
		});
		object.start();
	}
	
	
	@Override
	public void rippleFinish(int id) {		
		switch(id){
		case R.id.rl_close:	
			animationOut();
			break;
		case R.id.first_ball:
			animationOut();
			break;
		case R.id.rl_pc:
			animationOut();
			break;
		case R.id.rl_sdcard:
			animationOut();
			break;
		}
	}
	
	@Override
	public void onClick(View v) {
		animationOut();
	}
}
