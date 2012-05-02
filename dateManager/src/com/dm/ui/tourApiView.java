package com.dm.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.dm.R;

/**
 * @author  Gwak seok jong
 */
public class tourApiView extends Activity {
	public String[] title;
	public String[] imgUrl;
	public int size;
	public int[] contentID;
	public double[] mapX;
	public double[] mapY;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("viewActivity", "onCreate in apiView");
		setContentView(R.layout.search_main);
		Intent intent = getIntent();
		
		size = intent.getIntExtra("size", 0);
		title = intent.getStringArrayExtra("title");
		imgUrl = intent.getStringArrayExtra("imgUrl");
		contentID = intent.getIntArrayExtra("contentID");
		mapX = intent.getDoubleArrayExtra("mapX");
		mapY = intent.getDoubleArrayExtra("mapY");
		
		final ActionBar bar = getActionBar();
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		
		searchFrag newFragment = searchFrag.newInstance();
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.add(R.id.newlayout, newFragment).commit();
	}
}
