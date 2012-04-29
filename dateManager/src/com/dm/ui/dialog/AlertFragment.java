package com.dm.ui.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;

/***
 * 
 * @author HyunKyun
 *
 */
public class AlertFragment extends DialogFragment {
	Builder mB;
	Fragment parent;
	
	public AlertFragment(Fragment f, Builder b){
		parent = f;
		mB = b;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	return mB.create();
    }
}

