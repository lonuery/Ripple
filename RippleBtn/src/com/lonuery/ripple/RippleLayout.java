package com.lonuery.ripple;

import com.lonuery.btnripple.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class RippleLayout extends RelativeLayout{
	
	Paint paint;
	private int rippleColor;
	private Handler canvasHandler;
	private boolean animationRunning = false;
	private int FRAME_RATE = 10;
	private int mBitmapWidth;
	private int mBitmapHeight;
	private View childView;
	private int timer=0;
	
	float innerRadius;
	
	private Runnable runnable = new Runnable(){
        @Override
        public void run(){	
        	invalidate();	        	
        }
	};
	
	public RippleLayout(Context context) {
		super(context);
	}	
	public RippleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init( context, attrs);
	}	
	public RippleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}
	
	public void init(Context context, AttributeSet attrs){
		
		final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RippleView);
        rippleColor = typedArray.getColor(R.styleable.RippleView_rv_color, 
        		getResources().getColor(R.color.green_normal));	
        innerRadius = typedArray.getDimension(R.styleable.RippleView_rv_inner_radius,30.0f);
        
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(rippleColor);
        paint.setStrokeWidth(20);
        
        this.setWillNotDraw(false);
        this.setDrawingCacheEnabled(true);
        
        canvasHandler = new Handler();
        typedArray.recycle();
	}
	
	public void resetPaintColor(int color){
		if(paint!=null){
			paint.setColor(color);
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		ripple(canvas);
	}
	
	public void ripple(Canvas canvas){
		if (animationRunning) {			
			if(timer<=20){
				play(canvas,timer);
				canvasHandler.postDelayed(runnable, FRAME_RATE);
				timer++;
			}else{
				animationRunning=false;
				canvas.restore();
				invalidate();			
				timer=0;
				
				if(rippleFinishListener!=null){
					rippleFinishListener.rippleFinish(this.getId());
				}
			}	
			if (timer == 0){
                canvas.save();
            } 
		}
	}
	
	public void play(Canvas canvas,int timer){
		int alpha = 5;//透明度从5开始
		for (int s = 0; s <= timer; s++) {
			alpha +=2;//每绘制一个外圆，透明度加2
			paint.setAlpha(alpha);
			paint.setStrokeWidth(2);
			canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight/2, innerRadius+s, paint);
		}
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mBitmapWidth = w;
		mBitmapHeight = h;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
			if(animationRunning==false){
				invalidate();
		        this.performClick();        
		        animationRunning=true;
			}
		}
        childView.onTouchEvent(event);
		//return super.onTouchEvent(event);
        return true;
	}
	
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event){
       return true;
    }
	
	@Override
	public void addView(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		childView = child;
		
		super.addView(child, index, params);
	}
	
	public interface RippleFinishListener{
		void rippleFinish(int id);
	}
	public RippleFinishListener rippleFinishListener;
	public void setRippleFinishListener(RippleFinishListener rippleFinishListener){
		this.rippleFinishListener = rippleFinishListener;
	}
}
