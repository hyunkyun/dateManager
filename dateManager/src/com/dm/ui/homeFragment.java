package com.dm.ui;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
	private TextView count_day = null;
	private TextView start_day = null;

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

		hasday = (LinearLayout)v.findViewById(R.id.hasday_layout);
		hasday.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				DateCounterDialog newFragment = new DateCounterDialog(1);
				newFragment.show(getFragmentManager(), "dialog");
				return false;
			}
		});

		noday = (TextView)v.findViewById(R.id.no_day);
		noday.setOnLongClickListener(new OnLongClickListener(){
			@Override
			public boolean onLongClick(View v) {
				DateCounterDialog newFragment = new DateCounterDialog(0);
				newFragment.show(getFragmentManager(), "dialog");
				return false;
			}
		});

		memo = (TextView)hasday.findViewById(R.id.hasday_memo);
		count_day = (TextView)hasday.findViewById(R.id.hasday_date);
		start_day = (TextView)hasday.findViewById(R.id.hasday_startdate);

		setDisplay();

		testListfragment();
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}

	private void setDisplay(){
		Log.i(TAG, "setDisplay()");
		if(hasday == null || noday == null){
			Log.e(TAG, "hasday or noday is null. in setDisplay()");
			return;
		}
		if(day.hasData() == true){
			hasday.setVisibility(View.VISIBLE);
			noday.setVisibility(View.INVISIBLE);
			memo.setText(day.getMemo());
			count_day.setText(day.getDiffDays() + "일째");
			start_day.setText("(" + day.getStringDay() + ")");
		}else{
			hasday.setVisibility(View.INVISIBLE);
			noday.setVisibility(View.VISIBLE);
			noday.setText(R.string.dayCounterNoday);
		}
		day.getSpecialDays();
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
		private final String TAG = "DateCounterDialog";
		private int check;
		private DialogInterface.OnClickListener add;
		private DialogInterface.OnClickListener edit;

		public DateCounterDialog(int has) {
			Log.i(TAG,"Constructor, argument :" + has);
			check = has;
			add = new DialogInterface.OnClickListener() { 
				public void onClick(DialogInterface dialog, int which) {
					switch(which){
					case 0: // add
						EditDateCounterDialog edit = new EditDateCounterDialog(day);
						edit.show(getFragmentManager(), "DateCounterDialog");
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
						EditDateCounterDialog edit = new EditDateCounterDialog(day);
						edit.show(getFragmentManager(), "DateCounterDialog");
						break;
					case 1: // delete
						day.remove();
						setDisplay();
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
				b.setTitle("날짜 추가")
				.setItems(add_options, add);
			}
			else{
				b.setTitle("날짜 수정 (" + day.getStringDay() + ")")
				.setItems(edit_options, edit);
			}
			return b.create();
		}
	}

	private class EditDateCounterDialog extends DialogFragment 
	implements DatePickerDialog.OnDateSetListener{

		private dayCounter tempDayCounter;
		private datePickerFragment f;

		private View textEntryView; // 에디트 다이얼로그의 뷰 객체
		private EditText memoText;  // 에디트박스의 메모 상자
		private TextView dateText;  // 에디트박스의 날짜 상자

		public EditDateCounterDialog(dayCounter dc){
			super();
			tempDayCounter = dc;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			//EditDateCounter
			LayoutInflater factory = LayoutInflater.from(getActivity());

			textEntryView = factory.inflate(R.layout.edit_day_counter, null);
			dateText = (TextView)textEntryView.findViewById(R.id.edit_dayCounter_date);
			dateText.setText(day.getStringDay());

			f = new datePickerFragment(tempDayCounter);
			f.setOnDateSetListener(this);

			dateText.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					f.show(getFragmentManager(), "datePickerFragment");
				}
			});

			memoText = (EditText)textEntryView.findViewById(R.id.edit_dayCounter_memoData);
			memoText.setText(day.getMemo());

			return new AlertDialog.Builder(getActivity())
			.setIconAttribute(android.R.attr.alertDialogIcon)
			.setTitle(R.string.edit_dayCounter_title)
			.setView(textEntryView)
			.setPositiveButton(R.string.id_ok, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					Log.i(TAG, memoText.getText().toString());

					Editable str = memoText.getText();
					memo.setText(dateText.getText());
					// 메모값을 불러옴
					day.setMemo(str.toString());
					day.setDate(tempDayCounter.getDate());
					day.save();
					// 임
					setDisplay(); // homeFragment의 setdisplay 호출하여 적용
				}
			})
			.setNegativeButton(R.string.id_cancel, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					;
				}
			})
			.create();
		}

		public void setDisplayInEditBox(){
			dateText.setText(tempDayCounter.getStringDay());
		}

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			tempDayCounter.setDate(year, monthOfYear+1, dayOfMonth);
			setDisplayInEditBox();
		}
	}

	private class datePickerFragment extends DialogFragment{
		OnDateSetListener listener;
		dayCounter dc;

		public datePickerFragment(dayCounter dc){
			super();
			this.dc = dc;
		}

		public void setOnDateSetListener(OnDateSetListener l){
			listener = l;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(dc.getDate());

			return new DatePickerDialog(getActivity(), 
					listener, 
					c.get(Calendar.YEAR), 
					c.get(Calendar.MONTH)-1,
					c.get(Calendar.DATE));
		}
	}
}