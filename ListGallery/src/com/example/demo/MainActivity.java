package com.example.demo;

import com.example.listgallery.ListGallery;
import com.example.listgallery.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.listgallery.AdapterView;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener{
	private ListGallery mListGallery;
	
	private static final int GALLERY_ITEM_NUMBER = Integer.MAX_VALUE;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListGallery = (ListGallery)findViewById(R.id.listgallery);
		mListGallery.setOnItemClickListener(this);
		mListGallery.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Context context = parent.getContext();
				if (convertView == null){
					convertView = new ImageView(context);
					((ImageView)convertView).setImageResource(R.drawable.launcher_default_poster_hubo);
					convertView.setFocusable(true);
					convertView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
						
						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							if (hasFocus){
								v.setAlpha(0.6f);
							} else {
								v.setAlpha(1.0f);
							}
						}
					});
				} else {
					((ImageView)convertView).setImageResource(R.drawable.launcher_default_poster_hubo);
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
				return GALLERY_ITEM_NUMBER;
			}
		});
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "Click position " + position, 1000).show();
	}
}
