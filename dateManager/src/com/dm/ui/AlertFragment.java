package com.dm.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class AlertFragment extends DialogFragment {
	Builder mB;
	
	public AlertFragment(Builder b){
		mB = b;
	}
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	return mB.create();
    }
}
