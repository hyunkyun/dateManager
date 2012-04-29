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
 * 사용법 예시 코드 (List Fragment를 호출하는 Fragment나 액티비티에서 )
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
	Fragment와 Adapter를 선언하고 adapter에 add한후 그 값을 fragment transaction에 붙여준 후 Commit하면 된다.
	값을 선택했을때 호출되는 함수는 OnDataSelectionListener 인터페이스를 구현하면 된다.
	액티비티 값이 필요한 경우는 리스너 클래스에 생성자를 만들고 멤버 변수로 Fragment나 Activity를 가지게 한다음에
	리스너를 생성하면서 다음과 같이 그 값을 넣어주면된다.
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