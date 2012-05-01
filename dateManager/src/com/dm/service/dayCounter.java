package com.dm.service;

import java.util.Calendar;
import java.util.Date;

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
	
	public static class Day{
		private String memo = null;
		private long date;
		
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
	}
	
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
	public boolean hasData(){
		Log.i(TAG, "hasData()");
		if(day.memo != null){
			return true;
		}
		else
			return false;
	}
	
	public Day getDay(){
		return day;
	}
	public void setDay(Day d){
		day = d;
	}
	public void load(){
		Log.i(TAG, "Load()");
		day.memo = db.get(MEMO_KEY);
		String d = db.get(DATE_KEY);
		
		Log.i(TAG, "" + day.memo + "d" + d);
		
		if(day.memo != null && d != null){
			day.date = Long.parseLong(d);
			Log.i(TAG, "parse " + d);
			Log.i(TAG, "result " + day.date);
		}
		else if(day.memo == null)
			day.date = 0;
		else
			Log.e(TAG, "Error in load()");
	}
	public void save(){
		if(day.memo == null || day.memo == null){
			Log.e(TAG, "No Memo Value");
			return;
		}
		remove();
		
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
		db.delete(DATE_KEY);
		db.delete(MEMO_KEY);
	}

	public void setDate(int y, int m, int d){
		Calendar temp = Calendar.getInstance();
		temp.set(y,  m, d, 0 ,0 ,0);
		day.date = temp.getTimeInMillis();
	}
	
	public String getMemo() {
		return day.memo;
	}
	
	public void setMemo(String memo) {
		day.memo = memo;
	}
	
//Calendar 객체를 쓸때 m만 1 빼줌
	public long getDiffDays(){
		Calendar c = Calendar.getInstance();
		
		long resultTime = c.getTimeInMillis() - day.date;
		long resultDay = resultTime / (1000*60*60*24);
		return resultDay + 1;
	} 
	
	public long getDiffDays(int y, int m, int d){
		Calendar c = Calendar.getInstance();
		c.set(y, m-1, d, 0, 0, 0);
		
		long resultTime = c.getTimeInMillis() - day.date;
		long resultDay = resultTime / (1000*60*60*24);
		return resultDay;
	}
	
	public long getDiffDays(long d){
		long resultTime = d - day.date;
		long resultDay = resultTime / (1000*60*60*24);
		return resultDay;
	}
}