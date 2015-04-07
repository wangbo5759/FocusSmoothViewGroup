package com.example.demo;

import com.example.celllayout.SmoothGridView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class SmoothGridActivity extends Activity {
	SmoothGridView mSmoothGridView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSmoothGridView = new SmoothGridView(getApplicationContext());
		mSmoothGridView.setHorizontalSpacing(20);
		mSmoothGridView.setVerticalSpacing(30);
		mSmoothGridView.setNumColumns(4);
		
		mSmoothGridView.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null){
					convertView = new MyImageView(getApplicationContext());
				}
				
				
				return convertView;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return Integer.MAX_VALUE;
			}
		});
		((ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content)).addView(mSmoothGridView);
	}
}
