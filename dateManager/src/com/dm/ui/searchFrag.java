package com.dm.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dm.R;
import com.dm.ui.list.DataListFrag;
import com.dm.ui.list.IconTextItem;
import com.dm.ui.list.IconTextListAdapter;
import com.dm.ui.list.doitListener;
/***
 * 
 * @author Gwak seok jong
 *
 */
public class searchFrag extends Fragment {
	public static String TAG = "searchFragment";
	
	private String[] title;
	private String[] imgUrl;
	private int size;
	private int[] contentID;
	private double[] mapX;
	private double[] mapY;
	 
    static searchFrag newInstance() {
    	searchFrag f = new searchFrag();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tourApiView act = (tourApiView)getActivity();
		
		title = act.title;
		imgUrl = act.imgUrl;
		contentID = act.contentID;
		size = act.size;
		mapX = act.mapX;
		mapY = act.mapY;
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View v = inflater.inflate(R.layout.search_list_frag, container, false);
        
        DataListFrag frag = new DataListFrag();
        IconTextListAdapter adapter;
        adapter = new IconTextListAdapter(getActivity());
        
        Resources res = getResources();
        
        for(int i = 0; i < size; i++)
        	adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.bear), title[i], "X : " + mapX[i] + " Y :" + mapY[i]));
        
        frag.setAdapter(adapter);
        frag.setListener(new doitListener(this));
        
        FragmentTransaction ft = getFragmentManager().beginTransaction(); 
        ft.add(R.id.searchList, frag, "ListFragment").commit();
        
        Log.i(TAG, "Fragment");
        
        return v;
    }
}