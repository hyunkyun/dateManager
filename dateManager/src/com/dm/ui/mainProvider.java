package com.dm.ui;

import android.content.Context;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.dm.R;

/*���α׷� �ֻ�ܿ� ��ġ�� �޴���ư ActionProvider�� ������
 * ���̽�ũ�� ������ġ���� �����Ǵ� ���*/
/***
 * 
 * @author HyunKyun
 *
 */
public class mainProvider extends ActionProvider {
	/** An intent for launching the system settings. */
	//private static final Intent sSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
	/** Context for accessing resources. */
	private final Context mContext;

	public mainProvider(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public View onCreateActionView() {
		// Inflate the action view to be shown on the action bar.
		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
		View view = layoutInflater.inflate(R.layout.action_provider, null);
		ImageButton button = (ImageButton) view.findViewById(R.id.menuButton);
		// Attach a click listener for launching the system settings.
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PopupMenu popup = new PopupMenu(mContext, v);
				popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
				popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(mContext, "Clicked popup menu item " + item.getTitle(),
								Toast.LENGTH_SHORT).show();
						return true;
					}
				});
				popup.show();
				Toast.makeText(mContext, "works", Toast.LENGTH_SHORT).show();
				//mContext.startActivity(sSettingsIntent);
			}
		});
		return view;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onPerformDefaultAction() {
		// This is called if the host menu item placed in the overflow menu of the
		// action bar is clicked and the host activity did not handle the click.
		//mContext.startActivity(sSettingsIntent);
		return true;
	}
}