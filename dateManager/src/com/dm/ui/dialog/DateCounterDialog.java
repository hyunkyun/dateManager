package com.dm.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


public class DateCounterDialog extends DialogFragment {
	public static DateCounterDialog newInstance() {
		DateCounterDialog frag = new DateCounterDialog();
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Log.e("here","here");
		String[] options={"수정", "삭제"};
		return new AlertDialog.Builder(getActivity())
		.setTitle("날짜")
		.setItems(options, new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialog, int which) {
				switch(which){
					case 0:
						EditDateCounterDialog edit = EditDateCounterDialog.newInstance();
						edit.show(getFragmentManager(), "editDateCounter");
						break;
					case 1:
						Toast.makeText(getActivity(),"cancel", 1000).show();
						break;
					default:
						break;
				}
			}
		})
		.create();
	}
}