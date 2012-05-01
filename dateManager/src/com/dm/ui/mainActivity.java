package com.dm.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dm.R;

/***
 * 
 * @author HyunKyun
 *
 */
public class mainActivity extends Activity{
	/** Called when the activity is first created. */
	public Bundle temp;
	final int DATE_WIDGET = 3;
	final String TAG = "mainActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		final ActionBar bar = getActionBar();

		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		bar.addTab(bar.newTab()
				.setIcon(R.drawable.home_icon)
				.setTabListener(new TabListener<homeFragment>(
						this, "Home", homeFragment.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.schedule_icon)
				.setTabListener(new TabListener<testActivity>(
						this, "NewSchedule", testActivity.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.search_icon)
				.setTabListener(new TabListener<testActivity>(
						this, "weather", testActivity.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.recommend_icon)
				.setTabListener(new TabListener<testActivity>(
						this, "SNS", testActivity.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.setting_icon)
				.setTabListener(new TabListener<facebookfraftest>(
						this, "Setting", facebookfraftest.class)));

		if (savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
		}
		//test test
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.action_provider, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// If this callback does not handle the item click, onPerformDefaultAction
		// of the ActionProvider is invoked. Hence, the provider encapsulates the
		// complete functionality of the menu item.

		Toast.makeText(this, R.string.action_bar_handling,
				Toast.LENGTH_SHORT).show();
		return false;
	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
	}
}

