package com.dm.service.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/***
 * �̱��� �������� DB�� �����ϴ� ���� Ŭ����
 * @author HyunKyun
 *
 */
public class DBHelper extends SQLiteOpenHelper{

	private static final String CLASSNAME = DBHelper.class.getSimpleName();
	private static final String KEY_COLUMN = "_id";
	
	private volatile static DBHelper mInstance;
	private static SQLiteDatabase db;
	
	private static String TAG = "DBHelper";
	
	/***
	 * Basic Constructor
	 * @param context		: application context
	 * @param name			: db name
	 * @param factory		: cursor factory but null
	 * @param version		: db version to make easy to control
	 */
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		Log.v(TAG, DBHelper.CLASSNAME + "Create or Open : " + name + " in DBHelper.java");
	}
	/***
	 * Simple Constructor
	 * @param context	: application context
	 */
	public DBHelper(Context context){
		super(context, DBCreator.DB_NAME, null, DBCreator.DB_VERSION);
	}
	
	/***
	 * Singleton ������ �����ؼ� ������ �ϳ��� �ν��Ͻ��� �����ǵ��� �Ѵ�.
	 * Synchronized Ű���带 ����Ͽ� ��Ƽ������ ȯ�濡���� �ߺ� ������ �����Ѵ�.
	 * ���� ����Ʈ�������� �ʿ������, ���ĸ� ����ؼ� �Ѵ�.
	 * make connection to db
	 *  @param context		: application context
	 */
	private static void initialize(Context context){
		if(mInstance == null){
			synchronized(DBHelper.class){
				if(mInstance == null){
					Log.v(TAG, DBHelper.CLASSNAME + " create");
					mInstance = new DBHelper(context);
					try{
						db = mInstance.getWritableDatabase();
					}catch(SQLiteException se){
						Log.e(TAG, "Could not open db in DBHelper.java");
					}
					Log.i(TAG, DBHelper.CLASSNAME + " db instance " + DBCreator.DB_NAME + " created!");
				}
			}
		}
	}
	
	/***
	 * to get singleton instance
	 * @param context	:application context
	 * @return			:singletone instance
	 */
	public static final DBHelper getInstance(Context context){
		initialize(context);
		return mInstance;
	}
	
	/***
	 * Close connection to database, instance null
	 */
	public void close(){
		if(mInstance != null){
			Log.i(TAG, DBHelper.CLASSNAME + " db close : " + DBCreator.DB_NAME);
			db.close();
			mInstance = null;
		}
	}
	
	/***
	 * Method for select table
	 * @param table		: table name
	 * @param columns	: column name array
	 * @return			: cursor
	 */
	public Cursor get(String table, String[] columns){
		return db.query(table, columns, null, null, null, null, null);
	}
	
	/***
	 * Method for select table , select with id
	 * @param table		: table name
	 * @param columns	: column name array
	 * @param id		: record id (pk�÷��� _id�� ����)
	 * @return			: cursor
	 */
	public Cursor get(String table, String[] columns, long id){
		Cursor cursor = db.query(true, table, columns, KEY_COLUMN + "=" + id, null, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	/***
	 * Method for select statements
	 * @param sql	: sql statements
	 * @return		: cursor
	 */
	public Cursor get(String sql){
		return db.rawQuery(sql, null);
	}
	
	/***
	 * Method to insert record
	 * @param table		: table name
	 * @param values	: Content Values instance
	 * @return			: long(rowid)
	 */
	public long insert(String table, ContentValues values){
		return db.insert(table,  null, values);
	}
	
	/**
	 * Method to update record
	 * @param table		: table name
	 * @param values	: ContentValues instance
	 * @param id		: record id
	 * @return			: int
	 */
	public int update(String table, ContentValues values, long id){
		return db.update(table, values, KEY_COLUMN + "=" + id, null);
	}
	
	/**
	 * Method to update record with where clause
	 * @param table			: table name
	 * @param values		: ContentValues instance
	 * @param whereClause	: Where Clause
	 * @return				: int
	 */
	public int update(String table, ContentValues values, String whereClause){
		return db.update(table, values, whereClause, null);
	}
	
	/***
	 * Method to delete record with whereClause
	 * @param table			: table name
	 * @param whereClause	: Where Clause
	 * @return				: int
	 */
	public int delete(String table, String whereClause){
		return db.delete(table, whereClause, null);
	}
	
	/***
	 * Method to delete record with id
	 * @param table		: table name
	 * @param id		: record id
	 * @return			: id
	 */
	public int delete(String table, long id){
		return db.delete(table, KEY_COLUMN + "=" + id, null);
	}
	
	/***
	 * Method to execute query
	 * ������ �״�� �����ϴ� �޼ҵ�
	 * @param sql		: sql query
	 */
	public void exec(String sql){
		db.execSQL(sql);
	}
	/***
	 * logCursorInfo : Method to log Results from Cursor
	 * Ŀ���� �ִ� ������ �α�Ĺ���� ����� �޼ҵ�
	 * @param c		: Cursor
	 */
	public void logCursorInfo(Cursor c){
		Log.i(TAG, "***Cursor Begin *** " + "Results:" + 
				c.getCount() + " Columns: " + c.getColumnCount());
		
		String rowHeaders = "|| ";
		for(int i = 0 ; i < c.getColumnCount(); i++){
			rowHeaders = rowHeaders.concat(c.getColumnName(i) + " || ");
		}
		Log.i(TAG, "COLUMNS " + rowHeaders);
		c.moveToFirst();
		while(c.isAfterLast() == false){
			String rowResults = "|| ";
			for(int i = 0 ; i < c.getColumnCount(); i++){
				rowResults = rowResults.concat(c.getString(i) + " || ");
			}
			Log.i(TAG, "Row " + c.getPosition() + " : " + rowResults);
			c.moveToNext();
		}
		Log.i(TAG, "*** Cursor End ***");
	}
	
	/***
	 * Method to create database
	 * �����ͺ��̽� ����. ���� �ѹ��� �����
	 * @param db		:SQLiteDatabase instance
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.v(TAG, DBHelper.CLASSNAME + " onCreate() is called.");
		DBCreator mCreator = new DMDBCreator();
		String[] tableCreateStmt = mCreator.getCreateTablesStmt();
		String[] indexCreateStmt = mCreator.getCreateIndexStmt();
		String[] initDataDml = mCreator.getInitDataInsertStmt();
		
		try{
			if(tableCreateStmt != null && tableCreateStmt.length > 0)
			{
				Log.v(TAG, DBHelper.CLASSNAME + " - onCreate() : Table Creation");
				for(int i = 0 ; i < tableCreateStmt.length; i++){
					db.execSQL(tableCreateStmt[i]);
				}
			}
			if(indexCreateStmt != null && indexCreateStmt.length > 0){
				Log.v(TAG, DBHelper.CLASSNAME + " - onCreate() : Index Creation");
				for(int i = 0 ; i < indexCreateStmt.length; i++){
					db.execSQL(indexCreateStmt[i]);
				}
			}
			if(initDataDml != null && initDataDml.length > 0){
				Log.v(TAG, DBHelper.CLASSNAME + " - onCreate() : initData Creation");
				for(int i = 0 ; i < initDataDml.length; i++){
					db.execSQL(initDataDml[i]);
				}
			}
		} catch(SQLException e){
			Log.e(TAG, DBHelper.CLASSNAME + " - onCreate() : Creation Error");
		}
	}
	/***
	 * onUpgrade DB�� �����Ǿ��� �ÿ� ȣ��Ǵ� �޼ҵ�
	 * @param db		:SQLiteDatabase instance
	 * @param from		: from this version
	 * @param to		: to this version
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int from, int to) {
		Log.v(TAG, DBHelper.CLASSNAME + " - onUpgrade() from" + from + " to " + to);
	}
}