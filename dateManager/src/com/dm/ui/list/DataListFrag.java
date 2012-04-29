package com.dm.ui.list;

import com.dm.R;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
/**
 * 
 * @author HyunKyun
 * ���� ���� �ڵ� (List Fragment�� ȣ���ϴ� Fragment�� ��Ƽ��Ƽ���� )
 * ListFragment
 * 
 *	DataListFrag frag = new DataListFrag();
	IconTextListAdapter adapter;
	adapter = new IconTextListAdapter(getActivity());

	Resources res = getResources();

	adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.bear), "CGV", "test"));
	adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.bear), "CGV22", "test22"));

	frag.setAdapter(adapter);
	frag.setListener(new doitListener(this));

	FragmentTransaction ft = getFragmentManager().beginTransaction(); 
	ft.add(R.id.test_list, frag, "ListFragment").commit();
    
Usage:
	Fragment�� Adapter�� �����ϰ� adapter�� add���� �� ���� fragment transaction�� �ٿ��� �� Commit�ϸ� �ȴ�.
	���� ���������� ȣ��Ǵ� �Լ��� OnDataSelectionListener �������̽��� �����ϸ� �ȴ�.
	��Ƽ��Ƽ ���� �ʿ��� ���� ������ Ŭ������ �����ڸ� ����� ��� ������ Fragment�� Activity�� ������ �Ѵ�����
	�����ʸ� �����ϸ鼭 ������ ���� �� ���� �־��ָ�ȴ�.
	frag.setListener(new doitListener(this));
	
 */
public class DataListFrag extends ListFragment {
	private static final String TAG = "DataListFrag";
	private IconTextListAdapter adapter;
	private OnDataSelectionListener listener;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		try{
			listener.onDataSelected(v, position, id);
			Log.i("FragmentList", "Item clicked: " + id);
		}
		catch(NullPointerException e){
			Log.e(TAG, "must set OnDataSelectionListener");
		}
	}

	public void setAdapter(IconTextListAdapter adapter){
		this.adapter = adapter;
	}

	public IconTextListAdapter getAdapter(){
		return adapter;
	}

	public void setListener(OnDataSelectionListener listener){
		this.listener = listener;
		Log.i(TAG, "setListener");
	}
	public OnDataSelectionListener getListener(){
		return listener;
	}
}