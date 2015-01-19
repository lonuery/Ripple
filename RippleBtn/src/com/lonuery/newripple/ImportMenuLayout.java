package com.lonuery.newripple;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.lonuery.btnripple.R;
import com.lonuery.ripple.RippleLayout;
import com.lonuery.ripple.RippleLayout.RippleFinishListener;

public class ImportMenuLayout extends RelativeLayout implements RippleFinishListener,View.OnClickListener{
	
	RippleLayout rl_close,first_ball,rl_pc,rl_sdcard;
	View view_shadow;
	Context context=null;
	ImageButton iv_close;
	
	LinearLayout new_import_container;
	
	boolean isMenuOpen = false;
	
	public ImportMenuLayout(Context context) {
		super(context);
		init(context);
	}
	public ImportMenuLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public ImportMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}
	
	public void init(Context context){
		LayoutInflater.from(context).inflate(R.layout.widget_import_layout, this, true);
		rl_close = (RippleLayout)findViewById(R.id.new_rl_close);
		first_ball = (RippleLayout)findViewById(R.id.new_first_ball);
		rl_pc = (RippleLayout)findViewById(R.id.new_rl_pc);
		rl_sdcard = (RippleLayout)findViewById(R.id.new_rl_sdcard);	
		view_shadow = (View)findViewById(R.id.new_v_shadow);
		
		rl_close.setRippleFinishListener(this);
		first_ball.setRippleFinishListener(this);
		rl_pc.setRippleFinishListener(this);
		rl_sdcard.setRippleFinishListener(this);
		view_shadow.setOnClickListener(this);
		
		iv_close = (ImageButton)rl_close.findViewById(R.id.new_iv_close);
		this.context = context;
		
		new_import_container = (LinearLayout)findViewById(R.id.new_import_container);
		//new_import_container.setEnabled(false);
	}
	
	@Override
	public void onClick(View v) {
		//点击背景时的事件响应
		if(v.getId() == R.id.new_v_shadow){
			rl_closeCloseAnimation();
			menu_animationClose();
			isMenuOpen = false;
		}
	}

	@Override
	public void rippleFinish(int id) {
		switch(id){
		case R.id.new_rl_close:
			
			if(!isMenuOpen){
				
				rl_closeOpenAnimation();
				menu_animationOpen();
				isMenuOpen = true;
			}else{
				new_import_container.setAlpha(10);
				
				rl_closeCloseAnimation();
				menu_animationClose();
				isMenuOpen = false;
			}
			
			break;
		case R.id.new_first_ball:
			new_import_container.setAlpha(10);
			
			rl_closeCloseAnimation();
			menu_animationClose();
			isMenuOpen = false;
			break;
		case R.id.new_rl_pc:
			new_import_container.setAlpha(10);
			
			rl_closeCloseAnimation();
			menu_animationClose();
			isMenuOpen = false;
			break;
		case R.id.new_rl_sdcard:
			new_import_container.setAlpha(10);
			
			rl_closeCloseAnimation();
			menu_animationClose();
			isMenuOpen = false;
			break;
		}
	}
	
	public void rl_closeOpenAnimation(){		
		ObjectAnimator object = ObjectAnimator.ofFloat(rl_close, "rotation", 0, -45);
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
				iv_close.setBackgroundResource(R.drawable.selector_grey_ball);
				rl_close.resetPaintColor(getResources().getColor(R.color.grey_normal));
				
				view_shadow.setVisibility(View.VISIBLE);
			}
			@Override
			public void onAnimationCancel(Animator animation) {
				
			}
		});
		
		object.start();
		
	}
	
	public void rl_closeCloseAnimation(){
		
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
				iv_close.setBackgroundResource(R.drawable.selector_green_ball);
				rl_close.resetPaintColor(getResources().getColor(R.color.green_normal));
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				
			}
		});
		object.start();
	}
	
	public void menu_animationOpen(){
		
		new_import_container.setVisibility(View.VISIBLE);
		new_import_container.setEnabled(true);
		new_import_container.setFocusable(true);
		
		Animation first_ball_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_qrcode_button);
		first_ball.startAnimation(first_ball_anim);
		
		Animation rl_pc_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_pc_button);
		rl_pc.startAnimation(rl_pc_anim);
		
		Animation rl_sdcard_anim = AnimationUtils.loadAnimation(context, R.anim.collistion_import_sdcard_button);
		rl_sdcard.startAnimation(rl_sdcard_anim);	
		
		Animation view_fade_in_anim = AnimationUtils.loadAnimation(context, R.anim.fade_in);
		view_shadow.startAnimation(view_fade_in_anim);
	}
	
	public void menu_animationClose(){
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
				
				new_import_container.setVisibility(View.GONE);
				new_import_container.setAlpha(255);
				view_shadow.setVisibility(View.GONE);
			}
		});
	}
	
}
