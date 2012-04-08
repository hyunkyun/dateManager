package com.dm.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dm.R;

public class mainActivity extends Activity{
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		final ActionBar bar = getActionBar();

		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

		bar.addTab(bar.newTab()
				.setIcon(R.drawable.home)
				.setTabListener(new TabListener<testActivity>(
						this, "Home", testActivity.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.newschedule)
				.setTabListener(new TabListener<testActivity>(
						this, "NewSchedule", testActivity.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.search)
				.setTabListener(new TabListener<testActivity>(
						this, "SNS", testActivity.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.sns)
				.setTabListener(new TabListener<testActivity>(
						this, "SNS", testActivity.class)));
		bar.addTab(bar.newTab()
				.setIcon(R.drawable.ic_menu_settings_holo_light)
				.setTabListener(new TabListener<facebookfraftest>(
						this, "Setting", facebookfraftest.class)));

		if (savedInstanceState != null) {
			bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
		}

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
	
	public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        private final Bundle mArgs;
        private Fragment mFragment;

        public TabListener(Activity activity, String tag, Class<T> clz) {
            this(activity, tag, clz, null);
        }
        public TabListener(Activity activity, String tag, Class<T> clz, Bundle args) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;
            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            
            mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
            
            if (mFragment != null && !mFragment.isDetached()) {
                FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
                ft.detach(mFragment);
                ft.commit();
            }
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            if (mFragment == null) {
                mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
                ft.add(R.id.root, mFragment, mTag);
            } else {
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                ft.detach(mFragment);
            }
        }
        
        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
        }
    }
}