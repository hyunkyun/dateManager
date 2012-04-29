package com.dm.ui;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dm.R;
import com.dm.ui.dialog.DateCounterDialog;
import com.dm.ui.list.DataListFrag;
import com.dm.ui.list.IconTextItem;
import com.dm.ui.list.IconTextListAdapter;
import com.dm.ui.list.doitListener;
/***
 * 
 * @author HyunKyun
 *
 */
public class homeFragment extends Fragment {
	public static String TAG = "homeFragment";
	 
    static homeFragment newInstance(int num) {
    	homeFragment f = new homeFragment();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }//hi
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home, container, false);
                
        final TextView days = (TextView)v.findViewById(R.id.label);
        
        days.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				DialogFragment newFragment = DateCounterDialog.newInstance();
				newFragment.show(getFragmentManager(), "dialog");
				return false;
			}
        });
        
        DataListFrag frag = new DataListFrag();
        IconTextListAdapter adapter;
        adapter = new IconTextListAdapter(getActivity());
        
        Resources res = getResources();
        
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.bear), "CGV", "test"));
        adapter.addItem(new IconTextItem(res.getDrawable(R.drawable.bear), "CGV22", "test22"));
        
        frag.setAdapter(adapter);
        frag.setListener(new doitListener(this));
        
        FragmentTransaction ft = getFragmentManager().beginTransaction(); 
        ft.add(R.id.test_list, frag, "ListFragment").commit();
        
        Log.i(TAG, "Fragment");
        
        /*
        Date day = new Date(2011,11,05);
        Date day2 = new Date(2011, 11, 04);
        long diff = ((day.getTime()-day2.getTime())/(1000*60*60*24))+1;
        
        days.setText("ªÁ±œ¡ˆ " + diff + "¿œ");
        days.setBackgroundColor(R.color.actionbar_background_light);*/
        return v;
    }
}