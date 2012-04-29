package com.dm.service.db;

/***
 * Table ±¸Á¶¿¡ 
 * @author HyunKyun
 *
 */
public final class DMDB{
	DMDB(){}
	
	abstract public class table{
		public String TABLE_NAME;
		abstract public String[] getColomnNames();
	}
	
	public final class ScheduleTable extends table{
		private ScheduleTable(){}
		
		public static final String TABLE_NAME = "schedule";
		public static final String ID = "_id";
		public static final String TITLE = "title";
		public static final String START_ID = "start_loc_id";
		public static final String START_TIME = "start_time";
		
		@Override
		public String[] getColomnNames(){
			String [] colomnNames = {ID, TITLE, START_ID, START_TIME};
			return colomnNames;
		}
	}
	
	public final class NextLocationTable extends table{
		private NextLocationTable(){}
		
		public static final String TABLE_NAME = "next_location";
		public static final String SCHEDULE_ID = "schedule_id";
		public static final String LOCATION_ID = "location_id";
		public static final String ORDER = "order";
		
		@Override
		public String[] getColomnNames(){
			String [] colomnNames = {SCHEDULE_ID, LOCATION_ID, ORDER};
			return colomnNames;
		}
	}
	
	public final class LocationTable extends table{
		private LocationTable(){}
		
		public static final String TABLE_NAME = "location";
		public static final String ID = "_id";
		public static final String ADDRESS = "address";
		public static final String DETAILED_ADDRESS = "detailed_address";
		public static final String IMG_URL = "img_url";
		public static final String TITLE = "title";
		public static final String GPSX = "gps_x";
		public static final String GPSY = "gps_y";
		public static final String CATEGORY_ID = "category_id";
		
		@Override
		public String[] getColomnNames(){
			String [] colomnNames = {ID, ADDRESS, DETAILED_ADDRESS, IMG_URL,
					TITLE, GPSX, GPSY, CATEGORY_ID};
			return colomnNames;
		}
	}
	
	public final class CategoryTable extends table{
		private CategoryTable(){}
		
		public static final String TABLE_NAME = "category";
		public static final String ID = "_id";
		public static final String NAME = "name";

		@Override
		public String[] getColomnNames() {
			String[] colomnNames = {ID, NAME};
			return colomnNames;
		}
	}
}