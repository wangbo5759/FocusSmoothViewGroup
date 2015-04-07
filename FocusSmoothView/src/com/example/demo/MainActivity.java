package com.example.demo;

import com.example.celllayout.UniversalCellLayout;
import com.example.celllayout.UniversalCellLayout.PageItemLayoutParams;
import com.example.listgallery.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity{
	private RelativeLayout mContainer;
	private ListView mEntranceListView;
	private static final int COUNT = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContainer = (RelativeLayout)findViewById(R.id.container);
		mEntranceListView = new ListView(this);
		mEntranceListView.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				convertView = new TextView(MainActivity.this);
				((TextView)convertView).setTextSize(30);
				switch (position){
				case 0 :
					((TextView)convertView).setText("SmoothListView");
					break;
				case 1:
					((TextView)convertView).setText("SmoothGridView");
					break;
				case 2:
					((TextView)convertView).setText("SmoothScrollView");
					break;
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
				return COUNT;
			}
		});
		mEntranceListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				switch(position){
					case 0:
						intent.setClass(getApplicationContext(), SmoothListActivity.class);
						break;
					case 1:
						intent.setClass(getApplicationContext(), SmoothGridActivity.class);
						break;
					case 2:
						intent.setClass(getApplicationContext(), SmoothScrollActivity.class);
						break;
				}
				
				mContainer.getContext().startActivity(intent);
			}
		});
		
		mContainer.addView(mEntranceListView);
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
