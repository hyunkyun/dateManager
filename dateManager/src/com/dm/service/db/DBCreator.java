package com.dm.service.db;

/***
 * 
 * @author HyunKyun
 *
 */
public interface DBCreator{
	public static final String DB_NAME = "DMDB.db";
	public static final int DB_VERSION = 1;
	
	/***
	 * Table Definition Statement
	 * @return				: String[]
	 */
	public String[] getCreateTablesStmt();
	/***
	 * Index Definition Statement
	 * @return				: String[]
	 */
	public String[] getCreateIndexStmt();
	/***
	 * View Definition Statement
	 * @return				: String[]
	 */
	public String[] getCreateViewStmt();
	/***
	 * Trigger Definition Statement
	 * @return				: String[]
	 */
	public String[] getCreateTriggerStmt();
	/***
	 * Initial Data Insert Statement
	 * @return				: String[]
	 */
	public String[] getInitDataInsertStmt();
}