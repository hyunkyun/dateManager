package com.dm.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.R;
import com.dm.service.dayCounter;
import com.dm.ui.list.DataListFrag;
import com.dm.ui.list.IconTextItem;
import com.dm.ui.list.IconTextListAdapter;
import com.dm.ui.list.doitListener;
/***
 * 
 * @author HyunKyun
 * 인텐트 안의 값을 프래그먼트가 갯액티비티값
 */

public class homeFragment extends Fragment {
	private dayCounter day = null;
	
	private LinearLayout hasday = null;
	private TextView noday = null;
	private TextView memo = null;
	private TextView date = null;
	
	public final static String TAG = "homeFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.home, container, false);
		
		day = new dayCounter(getActivity());
		Log.i(TAG, "memo : " + day.getMemo() + "diff " + day.getDiffDays());
		
		LinearLayout hasday = (LinearLayout)v.findViewById(R.id.hasday_layout);
		hasday.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				DateCounterDialog newFragment = new DateCounterDialog(1);
				newFragment.show(getFragmentManager(), "dialog");
				return false;
			}
		});
		
		TextView noday = (TextView)v.findViewById(R.id.no_day);
		noday.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				DateCounterDialog newFragment = new DateCounterDialog(0);
				newFragment.show(getFragmentManager(), "dialog");
				return false;
			}
		});
		
		memo = (TextView)hasday.findViewById(R.id.hasday_memo);
		date = (TextView)hasday.findViewById(R.id.hasday_date);
		
		setDayCounter();
		
		testListfragment();
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}
	
	private void setDayCounter(){
		Log.i(TAG, "setDayCounter()");
		if(hasday == null || noday == null){
			Log.e(TAG, "hasday or noday is null. in setDayCounter()");
			return;
		}
		if(day.hasData() == true){
			hasday.setVisibility(View.VISIBLE);
			noday.setVisibility(View.INVISIBLE);
			memo.setText(day.getMemo());
			date.setText("" + day.getDiffDays() + "일째");
		}else{
			hasday.setVisibility(View.INVISIBLE);
			noday.setVisibility(View.VISIBLE);
		}
	}
	
	public void testListfragment(){
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
	}

	private class DateCounterDialog extends DialogFragment {
		private static final String TAG = "DateCounterDialog";
		private int check;
		private DialogInterface.OnClickListener add;
		private DialogInterface.OnClickListener edit;
		
		public DateCounterDialog(int has) {
			Log.i(TAG,"Constructor, argument has: " + has);
			check = has;
			add = new DialogInterface.OnClickListener() { 
				public void onClick(DialogInterface dialog, int which) {
					switch(which){
					case 0: // add
						EditDateCounterDialog edit = new EditDateCounterDialog(check);
						edit.show(getFragmentManager(), "editDateCounter");
						break;
					case 1: // cancel
					default:
						break;
					}
				}
			};
			edit = new DialogInterface.OnClickListener() { 
				public void onClick(DialogInterface dialog, int which) {
					switch(which){
					case 0: // edit
						EditDateCounterDialog edit = new EditDateCounterDialog(check);
						edit.show(getFragmentManager(), "editDateCounter");
						break;
					case 1: // delete
						day.remove();
						break;
					case 2: // cancel
					default:
						break;
					}
				}
			};
		}
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Log.i(TAG,"onCreateDialog");
			
			String[] edit_options = {"수정", "삭제", "취소"};
			String[] add_options = {"추가", "취소"};

			Builder b = new AlertDialog.Builder(getActivity());
			
			if(check==0){
				b.setTitle("날짜")
				.setItems(add_options, add);
			}
			else{
				b.setTitle("날짜2")
				.setItems(edit_options, edit);
			}
			return b.create();
		}
	}
	
	private class EditDateCounterDialog extends DialogFragment {
		private int check=0;
		
		public EditDateCounterDialog(int has){
			super();
			check = has;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			//EditDateCounter
			LayoutInflater factory = LayoutInflater.from(getActivity());
			final View textEntryView = factory.inflate(R.layout.edit_day_counter, null);

			Dialog d =  new AlertDialog.Builder(getActivity())
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
			
			return d;
		}
	}
}