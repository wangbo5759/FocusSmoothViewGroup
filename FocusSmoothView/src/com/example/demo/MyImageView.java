package com.example.demo;

import com.example.listgallery.R;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class MyImageView extends ImageView {

	public MyImageView(Context context) {
		super(context);
		init();
	}

	public MyImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	private void init(){
//		setScaleType(ScaleType.FIT_XY);
		setImageResource(R.drawable.launcher_default_poster_hubo);
	}
	
	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		if (selected){
			setAlpha(0.6f);
		} else {
			setAlpha(1.0f);
		}
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		if (gainFocus){
			setAlpha(0.6f);
		} else {
			setAlpha(1.0f);
		}
	}
}
