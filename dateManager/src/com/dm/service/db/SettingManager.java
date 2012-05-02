package com.dm.service.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.dm.service.db.DMDB.ScheduleTable;
import com.dm.service.db.DMDB.SettingTable;
import com.dm.service.db.ScheduleManager.Schedule;

/***
 * DAO로, 헬퍼 클래스를 이용하여 데이터베이스에 접근하여 
 * 자료구조형 클래스인 DTO에 저장할 수 있음
 * @author HyunKyun
 */
public class SettingManager{
		private String TAG = "SettingManager";
		private static final String CLASSNAME = SettingManager.class.getSimpleName();
		private DBHelper db;
		
		public SettingManager(Context context){
			db = DBHelper.getInstance(context);
		}
		public void close(){
			db.close();
		}

		public static class Setting{
			private String id;
			private String value;
			
			public Setting(){}
			public Setting(String id, String value){
				this.setId(id); this.setValue(value);
			}
			
			public String getValue() {
				return value;
			}
			public void setValue(String value) {
				this.value = value;
			}
			public String getId() {
				return id;
			}
			public void setId(String id) {
				this.id = id;
			}
		}
		
		public void insert(final Setting to){
			ContentValues values = new ContentValues();
			values.put(SettingTable.ID, to.getId());
			values.put(SettingTable.VALUE, to.getValue());
			
			long rowId = db.insert(SettingTable.TABLE_NAME, values);
			if(rowId < 0){
				throw new SQLException("Fail insert from DMDao");
			}
		}
		
		public void update(final Setting to){
			ContentValues values = new ContentValues();
			values.put(SettingTable.ID, to.getId());
			values.put(SettingTable.VALUE, to.getValue());
			
			Log.v(TAG, SettingManager.CLASSNAME + " update id:" + to.getId() + ", name:" +  to.getValue());
			db.update(SettingTable.TABLE_NAME, values, to.getId());
		}
		
		public void delete(final String id){
			Log.v(TAG, SettingManager.CLASSNAME + " delete id:" + id);
			db.delete(SettingTable.TABLE_NAME, id);
		}
		
		public List<Setting> get(){
			String sql = "SELECT * FROM " + SettingTable.TABLE_NAME + " ORDER BY 1";
			
			ArrayList<Setting> ret = getList(sql);
			return ret;
		}
		
		public String get(String id){
			String sql = "SELECT * FROM " + SettingTable.TABLE_NAME
					+ " WHERE " + SettingTable.ID + "='" + id + "' ORDER BY 1";
			
			Log.i(TAG, "sql:" + sql);
			ArrayList<Setting> ret = getList(sql);
			Log.i(TAG, "ret size:" + ret.size());
			
			if(ret == null || ret.size() == 0){
				return null;
			}
			else if(ret.size() > 1){
				Log.e(TAG, "duplicated PK?");
				delete(id);
				return null;
			}
			Setting to = ret.get(0);
			return to.getValue();
		}
		
		private ArrayList<Setting> getList(String sql){
			ArrayList<Setting> ret = null;
			Cursor c = null;
			try{
				c = db.get(sql);
				ret = setBindCursor(c);
			}catch(SQLException e){
				Log.e(TAG, SettingManager.CLASSNAME + " getList sqlerror");
			}finally{
				if(c != null && !c.isClosed()){
					c.close();
				}
			}
			return ret;
		}
		
		private ArrayList<Setting> setBindCursor(final Cursor c){
			ArrayList<Setting> ret = new ArrayList<Setting>();
			int numRows = c.getCount();
			c.moveToFirst();
			
			for(int i = 0 ; i < numRows; i++){
				Setting to = new Setting();
				to.setId(c.getString(c.getColumnIndex(SettingTable.ID)));
				to.setValue(c.getString(c.getColumnIndex(SettingTable.VALUE)));
				
				ret.add(to);
				c.moveToNext();
			}
			return ret;
		}
		
		public void logListInfo(List<Setting> to){
			
		}
}