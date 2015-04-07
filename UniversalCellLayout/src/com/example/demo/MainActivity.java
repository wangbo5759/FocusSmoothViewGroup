package com.example.demo;

import com.example.celllayout.UniversalCellLayout;
import com.example.celllayout.UniversalCellLayout.PageItemLayoutParams;
import com.example.listgallery.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;


public class MainActivity extends Activity{
	private RelativeLayout mContainer;
	private UniversalCellLayout mCellLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContainer = (RelativeLayout)findViewById(R.id.container);
		
		mCellLayout = new UniversalCellLayout(this);
		ImageView view_first = new ImageView(this);
		view_first.setScaleType(ScaleType.FIT_XY);
		view_first.setImageResource(R.drawable.launcher_default_poster_hubo);
		mCellLayout.addViewToPage(view_first, 0, 0, 1, 1);
		
		ImageView view_second = new ImageView(this);
		view_second.setScaleType(ScaleType.FIT_XY);
		view_second.setImageResource(R.drawable.launcher_default_poster_hubo);
		mCellLayout.addViewToPage(view_second, 0, 1, 1, 1);
		
		ImageView view_third = new ImageView(this);
		view_third.setScaleType(ScaleType.FIT_XY);
		view_third.setImageResource(R.drawable.launcher_default_poster_hubo);
		mCellLayout.addViewToPage(view_third, 1, 0, 1, 2);
		
		ImageView view_forth = new ImageView(this);
		view_forth.setScaleType(ScaleType.FIT_XY);
		view_forth.setImageResource(R.drawable.launcher_default_poster_hubo);
		mCellLayout.addViewToPage(view_forth, 2, 0, 1, 1);
		
		mContainer.addView(mCellLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
