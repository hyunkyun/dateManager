package com.dm.ui.list;

import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
 
public class doitListener implements OnDataSelectionListener{
	Fragment parent;
	public doitListener(Fragment frag){
		Log.i("doitListener", "»ý¼ºÀÚ");
		parent = frag;
	}
	
    public void onDataSelected(View v, int position, long id){
    	Log.i("doitListener", "setListener" + parent.getClass().toString());
    	Toast.makeText(parent.getActivity(), "selected " + position + " in doit ", 500).show();
    }
}