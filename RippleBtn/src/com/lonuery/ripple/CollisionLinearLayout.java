package com.lonuery.ripple;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class CollisionLinearLayout extends LinearLayout{
	
	public CollisionLinearLayout(Context context) {
		super(context);
	}
	public CollisionLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public CollisionLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}
	
	@Override
	public void addView(View child, int index,
			android.view.ViewGroup.LayoutParams params) {
		
		Log.i("addView", "addView:"+child.getContentDescription());
		
		super.addView(child, index, params);		
	}
	
}
