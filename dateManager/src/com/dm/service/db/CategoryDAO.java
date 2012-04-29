package com.dm.service.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.dm.service.db.DMDB.CategoryTable;
import com.dm.service.db.DMDB.ScheduleTable;

/***
 * DAO로, 헬퍼 클래스를 이용하여 데이터베이스에 접근하여 
 * 자료구조형 클래스인 DTO에 저장할 수 있음
 * @author HyunKyun
 */
public class CategoryDAO{
		private String TAG = "CategoryDao";
		private static final String CLASSNAME = CategoryDAO.class.getSimpleName();
		private DBHelper db;
		
		public CategoryDAO(Context context){
			db = DBHelper.getInstance(context);
		}
		public void close(){
			db.close();
		}
		/***
		 * inner class for category Data transfer object 
		 * @author HyunKyun
		 */
		public static class Category{
			private int id;
			private String name;
			public Category(){}
			public Category(int id, String name){
				this.id = id;
				this.name = name;
			}
			@Override
			public String toString(){return "Category [id=" + id + "name=" + name;}
			public int getId(){return this.id;}
			public void setId(int id){this.id = id;}
			public String getName(){return this.name;}
			public void setName(String name){this.name = name;}
		}
		/***	
		 * method to insert data
		 * @param to	: CategoryTO 
		 */
		public long insertCategory(final Category to){
			ContentValues values = new ContentValues();
			
			values.put(CategoryTable.ID, to.getId());
			values.put(CategoryTable.NAME, to.getName());
			
			Log.v(TAG, CategoryDAO.CLASSNAME + " insert id:" + to.getId() + ", name:" +  to.getName());
			long rowId = db.insert(CategoryTable.TABLE_NAME, values);
			return rowId;
			/*if(rowId < 0){
				return false;
			}
			return true;*/
		}
		/***
		 * method to update data
		 * @param to	: CategoryTO 
		 */
		public void updateCategory(final Category to){
			ContentValues values = new ContentValues();
			
			values.put(CategoryTable.ID, to.getId());
			values.put(CategoryTable.NAME, to.getName());
			
			Log.v(TAG, CategoryDAO.CLASSNAME + " update id:" + to.getId() + ", name:" +  to.getName());
			db.update(CategoryTable.TABLE_NAME, values, to.getId());
		}	
		/***
		 * method to delete data
		 * @param to	: CategoryTO 
		 */
		public void deleteCategory(final int id){
			Log.v(TAG, CategoryDAO.CLASSNAME + " delete id:" + id );
			db.delete(CategoryTable.TABLE_NAME, id);
		}	
	
		public List<Category> getCategory(int id){
			Cursor c = null;
			ArrayList<Category> ret = null;
			String sql = "SELECT * FROM " + CategoryTable.TABLE_NAME
					+ " WHERE id=" + id 
					+ " ORDER BY 1";
			try{
				Log.v(TAG, CategoryDAO.CLASSNAME + " getList ");
				c = db.get(sql);
				ret = setBindCursorCategory(c);
				
			}catch(SQLException e){
				Log.e(TAG, CategoryDAO.CLASSNAME + " getList sqlerror");
			}finally{
				if(c != null && !c.isClosed()){
					c.close();
				}
			}
			
			return ret;
		}
		/***
		 * method to select *
		 * @return 		: List<CategoryTO> 
		 */
		public List<Category> getCategory(){
			Cursor c = null;
			ArrayList<Category> ret = null;
			String sql = "SELECT * FROM " + CategoryTable.TABLE_NAME + " ORDER BY 1";
			
			try{
				Log.v(TAG, CategoryDAO.CLASSNAME + " getList ");
				c = db.get(sql);
				ret = setBindCursorCategory(c);
				
			}catch(SQLException e){
				Log.e(TAG, CategoryDAO.CLASSNAME + " getList sqlerror");
			}finally{
				if(c != null && !c.isClosed()){
					c.close();
				}
			}
			return ret;
		}
		/***
		 * method to bind Cusor to ArrayList
		 * @param to	: CategoryTO 
		 * @return		: List<CategoryTO >
		 */
		private ArrayList<Category> setBindCursorCategory(final Cursor c){
			ArrayList<Category> ret = new ArrayList<Category>();
			
			int numRows = c.getCount();
			c.moveToFirst();
			
			for(int i = 0 ; i < numRows; i++){
				Category to = new Category();
				to.setId(c.getInt(c.getColumnIndex(CategoryTable.ID)));
				to.setName(c.getString(c.getColumnIndex(CategoryTable.NAME)));
				ret.add(to);
				c.moveToNext();
			}
			return ret;
		}
		/***
		 * method to insert data
		 * @param to	: CategoryTO 
		 * @return		: ArrayList<CategoryTO>
		 */
		public void logListInfo(List<Category> to){
			Log.i(TAG, "*** List Begin ***");
			Iterator<Category> itr = to.iterator();
			while(itr.hasNext()){
				String msg = ((Category)itr.next()).toString();
				Log.i(TAG, "DATA :" + msg);
			}
			Log.i(TAG, "*** List End ***");
		}
}