package com.dm.ui;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dm.R;
import com.dm.content.tourapi.data.unionData;
import com.dm.content.tourapi.data.xmlData;

public class CustomAdapter extends ArrayAdapter<Object> {
	private ArrayList<xmlData> data;

	View v;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustomAdapter(Context context, int textViewResourceId,
			ArrayList items){
		super(context, textViewResourceId,items);
		this.data = items;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		v=convertView;

		if(v == null)
		{
			LayoutInflater vi=(LayoutInflater)getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v= vi.inflate(R.layout.listitemm, null);
		}

		unionData item = (unionData)data.get(position);
		if(item != null)
		{
			TextView v_title=(TextView)v.findViewById(R.id.title);
			TextView v_price=(TextView)v.findViewById(R.id.lprice);
			TextView v_mall=(TextView)v.findViewById(R.id.mallname);

			v_title.setText("厘家疙 : "+item.getTitle());
			v_price.setText("牧刨明ID : "+item.getContentId());
			v_mall.setText("林家 : "+item.getAddress().getAddress());

		}
		return v;
	}
}
