package com.dm.ui.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dm.R;

public class IconTextView extends LinearLayout{

	private ImageView mIcon;
	private TextView mText01;
	private TextView mText02;

	public IconTextView(Context context, IconTextItem aItem) {
		super(context);

		LayoutInflater inflater = (LayoutInflater)
				context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		inflater.inflate(R.layout.listitem, this,true);

		// Set Icon
		mIcon = (ImageView) findViewById(R.id.iconItem);
		mIcon.setImageDrawable(aItem.getIcon());

		// Set Text 01
		mText01 = (TextView) findViewById(R.id.dataItem01);
		mText01.setText(aItem.getData(0));

		// Set Text 02
		mText02 = (TextView) findViewById(R.id.dataItem02);
		mText02.setText(aItem.getData(1));
	}

	public void setText(int index, String data) {
		if (index == 0) {
			mText01.setText(data);
		} else if (index == 1) {
			mText02.setText(data);
		} else {
			throw new IllegalArgumentException();
		}
	}

	public void setIcon(Drawable icon) {
		mIcon.setImageDrawable(icon);
	}

}
