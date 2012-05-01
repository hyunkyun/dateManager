package com.dm.service.db;

import com.dm.service.dayCounter;
import com.dm.service.db.DMDB.CategoryTable;
import com.dm.service.db.DMDB.NextLocationTable;
import com.dm.service.db.DMDB.ScheduleTable;
import com.dm.service.db.DMDB.SettingTable;

/***
 * 앱에서 데이터베이스에 최초로 접근하였을 시에 실행하게되는 쿼리문들에 대한 틀을 가진 인터페이스
 * @author HyunKyun
 * 
 */
public class DMDBCreator implements DBCreator{
	//******************Category****************************
	private final String TABLE_CREATE_CATEGORY = "CREATE TABLE "
			+ CategoryTable.TABLE_NAME + " ("
			+ CategoryTable.ID + " INTEGER NOT NULL PRIMARY KEY, "
			+ CategoryTable.NAME + " VARCHAR(20) NOT NULL);"
			;
	private final String INDEX_CREATE_CATEGORY = "CREATE UNIQUE INDEX "
			+ CategoryTable.TABLE_NAME + "_pk ON "
			+ CategoryTable.TABLE_NAME + " (" + CategoryTable.ID + " );"
			;
	private final String INIT_CREATE_CATEGORY = "INSERT INTO "
			+ CategoryTable.TABLE_NAME + "("
			+ CategoryTable.ID + ","
			+ CategoryTable.NAME
			+ ") VALUES(1, '바보');";
	private final String INIT_CREATE_CATEGORY2 = "INSERT INTO " 	
			+ CategoryTable.TABLE_NAME + "("
			+ CategoryTable.ID + ","
			+ CategoryTable.NAME	
			+ ") VALUES(2, '호호');";
	private final String INIT_CREATE_CATEGORY3 = "INSERT INTO " 
			+ CategoryTable.TABLE_NAME + "("
			+ CategoryTable.ID + ","
			+ CategoryTable.NAME 
			+ ") VALUES(3, '하하');";
	private final String TABLE_DROP_CATEGORY = "DROP TABLE IF EXISTS " + CategoryTable.TABLE_NAME;
	
	//******************Location***********************
	private final String TABLE_CREATE_LOCATION = ""
			;
	private final String INDEX_CREATE_LOCATION = ""
			;
	//private final String TABLE_DROP_LOCATION = "DROP TABLE IF EXISTS" + LocationTable.TABLE_NAME;
	
	//********************Schedule************************
	private final String TABLE_CREATE_SCHEDULE = "CREATE TABLE "
			+ ScheduleTable.TABLE_NAME + " ("
			+ ScheduleTable.ID + " INTEGER NOT NULL PRIMARY KEY, "
			+ ScheduleTable.TITLE + " VARCHAR(20) DEFAULT '' NOT NULL, "
			+ ScheduleTable.START_ID + " INTEGER NOT NULL "
			+ ScheduleTable.START_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL);"
			;
	
	private final String INDEX_CREATE_SCHEDULE = "CREATE UNIQUE INDEX "
			+ ScheduleTable.TABLE_NAME + "_pk ON "
			+ ScheduleTable.TABLE_NAME + " (" + ScheduleTable.ID + ");"
			;
	private final String INIT_CREATE_SCHEDULE = "INSERT INTO "
			+ ScheduleTable.TABLE_NAME + " (" 
			+ ScheduleTable.ID + ","
			+ ScheduleTable.TITLE + ","
			+ ScheduleTable.START_ID
			+ ") VALUES(1, '스케쥴', 1);";
	
	private final String TABLE_DROP_SCHEDULE = "DROP TABLE IF EXISTS " + ScheduleTable.TABLE_NAME;
	
	//********************Next Location************************
	private final String TABLE_CREATE_NEXTLOCATION = "CREATE TABLE "
			+ NextLocationTable.TABLE_NAME + " ("
			+ NextLocationTable.SCHEDULE_ID + " INTEGER NOT NULL PRIMARY KEY, "
			+ NextLocationTable.LOCATION_ID + " INTEGER NOT NULL FORIEGN KEY, "
			+ NextLocationTable.ORDER + "INTEGER NOT NULL UNIQUE);"
			;
	private final String INDEX_CREATE_NEXTLOCATION = "CREATE UNIQUE INDEX "
			+ NextLocationTable.TABLE_NAME + "_pk ON "
			+ NextLocationTable.TABLE_NAME + " (" +  NextLocationTable.SCHEDULE_ID + ");"
			;
	private final String TABLE_DROP_NEXTLOCATION = "DROP TABLE IF EXISTS " + NextLocationTable.TABLE_NAME;
	
	//********************Setting************************
	private final String TABLE_CREATE_SETTING = "CREATE TABLE "
			+ SettingTable.TABLE_NAME + " ( "
			+ SettingTable.ID + " VARCHAR(10) NOT NULL PRIMARY KEY, "
			+ SettingTable.VALUE + " VARCHAR(60) NOT NULL );"
			;
			
	private final String INDEX_CREATE_SETTING = "CREATE UNIQUE INDEX "
			+ SettingTable.TABLE_NAME + "_pk ON "
			+ SettingTable.TABLE_NAME + " ( " + SettingTable.ID + ");"
			;
	private final String TABLE_DROP_SETTING = "DROP TABLE IF EXISTS " + SettingTable.TABLE_NAME;
	
	private final String INSERT_CREATE_SETTING_MEMO = "INSERT INTO " + SettingTable.TABLE_NAME
			+ " (" + SettingTable.ID + ", " + SettingTable.VALUE + ")" + 
			"VALUES('" + dayCounter.MEMO_KEY + "', '으히히 메모')";
	
	private final String INSERT_CREATE_SETTING_DATE = "INSERT INTO " + SettingTable.TABLE_NAME
			+ " (" + SettingTable.ID + ", " + SettingTable.VALUE + ")" + 
			"VALUES('" + dayCounter.DATE_KEY + "', '1322997918192')";
	

	//******************************************************
	@Override
	public String[] getCreateTablesStmt() {
		String[] tableStmt = {TABLE_CREATE_CATEGORY, TABLE_CREATE_SETTING};
		return tableStmt;
	}

	@Override
	public String[] getCreateIndexStmt() {
		String[] indexStmt = {INDEX_CREATE_SETTING, INDEX_CREATE_CATEGORY};
		return indexStmt;
	}

	@Override
	public String[] getCreateViewStmt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getCreateTriggerStmt() {
		// TODO Auto-generated method stub
		// 외래키 지원 안되는 sqlite대신에 사용할 계획
		return null;
	}

	@Override
	public String[] getInitDataInsertStmt() {
		String[] initStmt = {INIT_CREATE_CATEGORY, INIT_CREATE_CATEGORY2, INIT_CREATE_CATEGORY3
				, INSERT_CREATE_SETTING_DATE, INSERT_CREATE_SETTING_MEMO};
		return initStmt;
	}

	@Override
	public String[] getDropTableStmt() {
		String[] dropStmt = {TABLE_DROP_CATEGORY, TABLE_DROP_SCHEDULE, TABLE_DROP_NEXTLOCATION, TABLE_DROP_SETTING};
		return dropStmt;
	}
}