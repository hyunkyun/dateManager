package com.dm.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.R;


public class EditDateCounterDialog extends DialogFragment {
	public static EditDateCounterDialog newInstance() {
		EditDateCounterDialog frag = new EditDateCounterDialog();
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		//EditDateCounter
		LayoutInflater factory = LayoutInflater.from(getActivity());
        final View textEntryView = factory.inflate(R.layout.edit_day_counter, null);
        return new AlertDialog.Builder(getActivity())
            .setIconAttribute(android.R.attr.alertDialogIcon)
            .setTitle(R.string.edit_dayCounter_title)
            .setView(textEntryView)
            .setPositiveButton(R.string.id_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	TextView memo = (TextView)textEntryView.findViewById(R.id.edit_dayCounter_memoData);
                	CharSequence temp = memo.getText();
                	Toast.makeText(getActivity(), temp, 1000).show();
                }
            })
            .setNegativeButton(R.string.id_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	;
                }
            })
            .create();
	}
}