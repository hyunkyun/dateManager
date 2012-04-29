package com.dm.service.db;

import com.dm.service.db.DMDB.CategoryTable;
import com.dm.service.db.DMDB.NextLocationTable;
import com.dm.service.db.DMDB.ScheduleTable;

/***
 * �ۿ��� �����ͺ��̽��� ���ʷ� �����Ͽ��� �ÿ� �����ϰԵǴ� �������鿡 ���� Ʋ�� ���� �������̽�
 * @author HyunKyun
 * 
 */
public class DMDBCreator implements DBCreator{
	
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
			+ ") VALUES(1, '�ٺ�');";
	private final String INIT_CREATE_CATEGORY2 = "INSERT INTO " 	
			+ CategoryTable.TABLE_NAME + "("
			+ CategoryTable.ID + ","
			+ CategoryTable.NAME	
			+ ") VALUES(2, 'ȣȣ');";
	private final String INIT_CREATE_CATEGORY3 = "INSERT INTO " 
			+ CategoryTable.TABLE_NAME + "("
			+ CategoryTable.ID + ","
			+ CategoryTable.NAME 
			+ ") VALUES(3, '����');";
		
	private final String TABLE_CREATE_LOCATION = ""
			;
	private final String INDEX_CREATE_LOCATION = ""
			;
	
	private final String TABLE_CREATE_SCHEDULE = "CREATE TABLE "
			+ ScheduleTable.TABLE_NAME + " ("
			+ ScheduleTable.ID + " INTEGER NOT NULL PRIMARY KEY, "
			+ ScheduleTable.TITLE + " VARCHAR(20) DEFAULT '' NOT NULL, "
			+ ScheduleTable.START_ID + " INTEGER NOT NULL"
			+ ScheduleTable.START_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL"
			;
	
	private final String INDEX_CREATE_SCHEDULE = "CREATE UNIQUE INDEX "
			+ ScheduleTable.TABLE_NAME + "_pk ON "
			+ ScheduleTable.TABLE_NAME + "( " + ScheduleTable.ID + " );"
			;
	private final String INIT_CREATE_SCHEDULE = "INSERT INTO"
			+ ScheduleTable.TABLE_NAME + "(" 
			+ ScheduleTable.ID + ","
			+ ScheduleTable.TITLE + ","
			+ ScheduleTable.START_ID
			+ ") VALUES(1, '������', 1);";

	private final String TABLE_CREATE_NEXTLOCATION = "CREATE TABLE "
			+ NextLocationTable.TABLE_NAME + " ( "
			+ NextLocationTable.SCHEDULE_ID + " INTEGER NOT NULL PRIMARY KEY, "
			+ NextLocationTable.LOCATION_ID + " INTEGER NOT NULL FORIEGN KEY, "
			+ NextLocationTable.ORDER + "INTEGER NOT NULL UNIQUE, "
			;
	private final String INDEX_CREATE_NEXTLOCATION = "CREATE UNIQUE INDEX"
			+ NextLocationTable.TABLE_NAME + "_pk ON "
			+ NextLocationTable.TABLE_NAME + " (" +  NextLocationTable.SCHEDULE_ID + " );"
			;
	
	@Override
	public String[] getCreateTablesStmt() {
		String[] tableStmt = {TABLE_CREATE_CATEGORY, TABLE_CREATE_SCHEDULE, TABLE_CREATE_NEXTLOCATION};
		return tableStmt;
	}

	@Override
	public String[] getCreateIndexStmt() {
		String[] indexStmt = {INDEX_CREATE_NEXTLOCATION, INDEX_CREATE_SCHEDULE, INDEX_CREATE_CATEGORY};
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
		// �ܷ�Ű ���� �ȵǴ� sqlite��ſ� ����� ��ȹ
		return null;
	}

	@Override
	public String[] getInitDataInsertStmt() {
		String[] initStmt = {INIT_CREATE_CATEGORY, INIT_CREATE_CATEGORY2, INIT_CREATE_CATEGORY3, INIT_CREATE_SCHEDULE};
		return initStmt;
	}
}