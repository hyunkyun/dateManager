package com.dm.service;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.util.Log;

import com.dm.service.db.SettingManager;
import com.dm.service.db.SettingManager.Setting;

/***
 * 
 * @author HyunKyun
 * 
 */
public class dayCounter{
	public class Day{
		private String memo = null;
		private long date;
		public Day(){;}
		
		public Day(long d, String m){
			this.date = d;
			this.memo = m;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public long getDate() {
			return date;
		}
		public void setDate(long date) {
			this.date = date;
		}
		public String getStringDay(){
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(this.date);
			String str = "" + c.get(Calendar.YEAR)
					+ ". "
						+ c.get(Calendar.MONTH)
						+ ". "
						+ c.get(Calendar.DATE)
						+ ".";
			return str;
		}
	}	
	// daycounter가 arraylist로 date, memo를 사용해서 날짜 long과 몇일 memo로 넘기기
	
	private Day day;
	private SettingManager db;
	
	final public static String MEMO_KEY = "day_memo";
	final public static String DATE_KEY = "day_date";
	
	private static String TAG = "dayCounter";
	
	/**
	 * 생성자, db에서 저장된 날짜 값을 꺼내온다.
	 * @param context	: Context value
	 */
	
	public dayCounter(Context context){
		day = new Day();
		db = new SettingManager(context);
		load();
	}
	
	public Day getDay(){
		return day;
	}
	
	public void setDay(Day d){
		day = d;
	}
	
	public long getDate(){
		return day.date;
	}
	
	public void setDate(long d){
		day.date = d;
	}
	
	public String getMemo() {
		return day.memo;
	}
	
	public void setMemo(String memo) {
		day.memo = memo;
	}
	
	public boolean hasData(){
		Log.i(TAG, "hasData() ");
		if(day.memo != null){
			return true;
		}
		else
			return false;
	}
	
	private void load(){
		Log.i(TAG, "Load()");
		day.memo = db.get(MEMO_KEY);
		String d = db.get(DATE_KEY);
		
		if(day.memo != null && d != null){
			day.date = Long.parseLong(d);
			Log.i(TAG, "parse " + d);
			Log.i(TAG, "result " + day.date);
		}
		else if(day.memo == null){
			day.date = Calendar.getInstance().getTimeInMillis();
		}
		else
			Log.e(TAG, "Error in load()");
	}
	
	public void save(){
		if(day.memo == null || day.memo == null){
			Log.e(TAG, "No Memo Value");
			return;
		}
		deleteDateFromDB();
		
		Setting dateSetting = new Setting();
		dateSetting.setId(DATE_KEY);
		dateSetting.setValue(new Long(day.date).toString());
		db.insert(dateSetting);
		
		Setting memo_setting = new Setting();
		memo_setting.setId(MEMO_KEY);
		memo_setting.setValue(day.memo);
		db.insert(memo_setting);
	}

	public void remove(){
		day.memo = null;
		deleteDateFromDB();
	}

	private void deleteDateFromDB(){
		db.delete(DATE_KEY);
		db.delete(MEMO_KEY);
	}
	
	public void setDate(int y, int m, int d){
		Calendar temp = Calendar.getInstance();
		temp.set(y,  m, d, 0 ,0 ,0);
		day.date = temp.getTimeInMillis();
	}

	public long getDiffDays(){
		Calendar c = Calendar.getInstance();
		
		long resultTime = c.getTimeInMillis() - day.date;
		long resultDay = resultTime / (1000*60*60*24);
		return resultDay + 1;
	} 
	
	public long getDiffDays(int y, int m, int d){
		Calendar c = Calendar.getInstance();
		c.set(y, m, d, 0, 0, 0);
		
		long resultTime = c.getTimeInMillis() - day.date;
		long resultDay = resultTime / (1000*60*60*24);
		return resultDay;
	}
	
	public long getDiffDays(long d){
		long resultTime = d - day.date;
		long resultDay = resultTime / (1000*60*60*24);
		return resultDay;
	}
	
	public String getStringDay(){
		return day.getStringDay();
	}
	
	public ArrayList<Day> getSpecialDays(){
		Log.i("getSpecialDays", "null memo");
		if(day.memo == null)
			return null;
		
		ArrayList<Day> l = new ArrayList<Day>();
		
		Calendar c = Calendar.getInstance();
		//long now = c.getTimeInMillis();
		long start = day.date; 
		int k = 1;
		
		for(int i = 0 ; i < 4 ; i++){
			Day itr = new Day();
			itr.setDate(start + i * 50 * 24 * 3600 * 1000);
			itr.setMemo(""+ k*100 + "일 기념일");
			l.add(itr);
			k++;
		}
		
		for(int i = 0 ; i < l.size(); i++){
			
			Log.i("List", l.get(i).getMemo() + " d + " + l.get(i).getStringDay());
		}
		// 현재로부터 50일 단위로 10개를 뽑는다. 
		return l;
	}
}