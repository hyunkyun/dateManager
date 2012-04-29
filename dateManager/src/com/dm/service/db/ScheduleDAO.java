package com.dm.service.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.dm.service.db.DMDB.ScheduleTable;

/***
 * DAO로, 헬퍼 클래스를 이용하여 데이터베이스에 접근하여 
 * 자료구조형 클래스인 DTO에 저장할 수 있음
 * @author HyunKyun
 */
public class ScheduleDAO{
		private String TAG = "ScheduleDao";
		private static final String CLASSNAME = ScheduleDAO.class.getSimpleName();
		private DBHelper db;
		
		public ScheduleDAO(Context context){
			db = DBHelper.getInstance(context);
		}
		public void close(){
			db.close();
		}

		public static class Schedule{
			private int id;
			private String title;
			private int start_id;
			private long start_time;
			public Schedule(){}
			public Schedule(int id, String title, int start_id, long start_time){
				this.setId(id); this.setTitle(title); this.setStart_id(start_id); this.setStart_time(start_time);
			}
			public int getId() {
				return id;
			}
			public void setId(int id) {
				this.id = id;
			}
			public String getTitle() {
				return title;
			}
			public void setTitle(String title) {
				this.title = title;
			}
			public int getStart_id() {
				return start_id;
			}
			public void setStart_id(int start_id) {
				this.start_id = start_id;
			}
			public long getStart_time() {
				return start_time;
			}
			public void setStart_time(long start_time) {
				this.start_time = start_time;
			}
		}
		
		public void insertSchedule(final Schedule to){
			ContentValues values = new ContentValues();
			values.put(ScheduleTable.ID, to.getId());
			values.put(ScheduleTable.TITLE, to.getTitle());
			values.put(ScheduleTable.START_TIME, to.getStart_time());
			values.put(ScheduleTable.START_ID, to.getStart_id());
			
			long rowId = db.insert(ScheduleTable.TABLE_NAME, values);
			if(rowId < 0){
				throw new SQLException("Fail insert from DMDao");
			}
		}
		
		public void updateSchedule(final Schedule to){
			ContentValues values = new ContentValues();
			values.put(ScheduleTable.ID, to.getId());
			values.put(ScheduleTable.TITLE, to.getTitle());
			values.put(ScheduleTable.START_TIME, to.getStart_time());
			values.put(ScheduleTable.START_ID, to.getStart_id());
			
			Log.v(TAG, ScheduleDAO.CLASSNAME + " update id:" + to.getId() + ", name:" +  to.getTitle());
			db.update(ScheduleTable.TABLE_NAME, values, to.getId());
		}
		
		public void deleteSchedule(final int id){
			Log.v(TAG, ScheduleDAO.CLASSNAME + " delete id:" + id );
			db.delete(ScheduleTable.TABLE_NAME, id);
		}
		public List<Schedule> getSchedule(){
			Cursor c = null;
			ArrayList<Schedule> ret = null;
			String sql = "SELECT * FROM " + ScheduleTable.TABLE_NAME + " ORDER BY 1";
			
			try{
				Log.v(TAG, ScheduleDAO.CLASSNAME + " getList ");
				c = db.get(sql);
				ret = setBindCursorSchedule(c);
				
			}catch(SQLException e){
				Log.e(TAG, ScheduleDAO.CLASSNAME + " getList sqlerror");
			}finally{
				if(c != null && !c.isClosed()){
					c.close();
				}
			}
			return ret;
		}
		
		public ArrayList<Schedule> setBindCursorSchedule(final Cursor c){
			ArrayList<Schedule> ret = new ArrayList<Schedule>();
			
			int numRows = c.getCount();
			c.moveToFirst();
			
			for(int i = 0 ; i < numRows; i++){
				Schedule to = new Schedule();
				to.setId(c.getInt(c.getColumnIndex(ScheduleTable.ID)));
				to.setTitle(c.getString(c.getColumnIndex(ScheduleTable.TITLE)));
				to.setStart_id(c.getInt(c.getColumnIndex(ScheduleTable.START_ID)));
				String time = c.getString(c.getColumnIndex(ScheduleTable.START_TIME));
				
				ret.add(to);
				c.moveToNext();
			}
			return ret;
		}
		
		public void logListInfoSchedule(List<Schedule> to){
			
		}
}