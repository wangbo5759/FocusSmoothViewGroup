package com.example.demo;

import com.example.celllayout.SmoothHorizontalScrollView;
import com.example.celllayout.SmoothScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SmoothScrollActivity extends Activity {
	SmoothHorizontalScrollView mScrollView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mScrollView = new SmoothHorizontalScrollView(getApplicationContext());
		LinearLayout linear = new LinearLayout(getApplicationContext());
		for (int i = 0; i < 30; i++ ){
			MyImageView image = new MyImageView(getApplicationContext());
			image.setFocusable(true);
			ViewGroup.LayoutParams vl = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			image.setLayoutParams(vl);
			linear.addView(image);
		}
		ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		linear.setLayoutParams(ll);
		mScrollView.addView(linear);
		
		((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).addView(mScrollView);
	}
}
