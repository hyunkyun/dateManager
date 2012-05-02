package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class roomIntroList {
	/**
	 * @uml.property  name="title"
	 */
	private String title;
	/**
	 * @uml.property  name="scale"
	 */
	private String scale;
	/**
	 * @uml.property  name="roomCount"
	 */
	private String roomCount;
	/**
	 * @uml.property  name="baseCount"
	 */
	private String baseCount;
	/**
	 * @uml.property  name="maxCount"
	 */
	private String maxCount;
	/**
	 * @uml.property  name="offSeasonFee1"
	 */
	private String offSeasonFee1;
	/**
	 * @uml.property  name="offSeasonFee2"
	 */
	private String offSeasonFee2;
	/**
	 * @uml.property  name="peakSeasonFee1"
	 */
	private String peakSeasonFee1;
	/**
	 * @uml.property  name="peakSeasonFee2"
	 */
	private String peakSeasonFee2;
	/**
	 * @uml.property  name="bathFacility"
	 */
	private boolean bathFacility;
	/**
	 * @uml.property  name="bath"
	 */
	private boolean bath;
	/**
	 * @uml.property  name="homeTheater"
	 */
	private boolean homeTheater;
	/**
	 * @uml.property  name="aircondition"
	 */
	private boolean aircondition;
	/**
	 * @uml.property  name="tv"
	 */
	private boolean tv;
	/**
	 * @uml.property  name="pc"
	 */
	private boolean pc;
	/**
	 * @uml.property  name="internet"
	 */
	private boolean internet;
	/**
	 * @uml.property  name="refrigerator"
	 */
	private boolean refrigerator;
	/**
	 * @uml.property  name="table"
	 */
	private boolean table;
	/**
	 * @uml.property  name="hairdryer"
	 */
	private boolean hairdryer;
	
	public roomIntroList(String _title, String _scale, String _roomCount, String _baseCount,
			String _maxCount, String _offSeasonFee1, String _offSeasonFee2, String _peakSeasonFee1,
			String _peakSeasonFee2, boolean _bathFacility, boolean _bath, boolean _homeTheater,
			boolean _aircondition, boolean _tv, boolean _pc, boolean _internet,
			boolean _refrigerator, boolean _table, boolean _hairdryer){
		setTitle(_title);
		setScale(_scale);
		setRoomCount(_roomCount);
		setBaseCount(_baseCount);
		setMaxCount(_maxCount);
		setOffSeasonFee1(_offSeasonFee1);
		setOffSeasonFee2(_offSeasonFee2);
		setPeakSeasonFee1(_peakSeasonFee1);
		setPeakSeasonFee2(_peakSeasonFee2);
		setBathFacility(_bathFacility);
		setBath(_bath);
		setHomeTheater(_homeTheater);
		setAircondition(_aircondition);
		setTv(_tv);
		setPc(_pc);
		setInternet(_internet);
		setRefrigerator(_refrigerator);
		setTable(_table);
		setHairdryer(_hairdryer);
	}
	
	public roomIntroList(String _title, String _scale, String _roomCount,
			String _baseCount, String _maxCount, String _offSeasonFee1,
			String _offSeasonFee2, String _peakSeasonFee1, String _peakSeasonFee2,
			boolean[] _roomIntro) {
		setTitle(_title);
		setScale(_scale);
		setRoomCount(_roomCount);
		setBaseCount(_baseCount);
		setMaxCount(_maxCount);
		setOffSeasonFee1(_offSeasonFee1);
		setOffSeasonFee2(_offSeasonFee2);
		setPeakSeasonFee1(_peakSeasonFee1);
		setPeakSeasonFee2(_peakSeasonFee2);
		setBathFacility(_roomIntro[0]);
		setBath(_roomIntro[1]);
		setHomeTheater(_roomIntro[2]);
		setAircondition(_roomIntro[3]);
		setTv(_roomIntro[4]);
		setPc(_roomIntro[5]);
		setInternet(_roomIntro[6]);
		setRefrigerator(_roomIntro[7]);
		setTable(_roomIntro[8]);
		setHairdryer(_roomIntro[9]);
	}

	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return
	 * @uml.property  name="hairdryer"
	 */
	public boolean isHairdryer() {
		return hairdryer;
	}
	
	/**
	 * @param hairdryer
	 * @uml.property  name="hairdryer"
	 */
	public void setHairdryer(boolean hairdryer) {
		this.hairdryer = hairdryer;
	}
	
	/**
	 * @return
	 * @uml.property  name="table"
	 */
	public boolean isTable() {
		return table;
	}
	
	/**
	 * @param table
	 * @uml.property  name="table"
	 */
	public void setTable(boolean table) {
		this.table = table;
	}
	
	/**
	 * @return
	 * @uml.property  name="refrigerator"
	 */
	public boolean isRefrigerator() {
		return refrigerator;
	}
	
	/**
	 * @param refrigerator
	 * @uml.property  name="refrigerator"
	 */
	public void setRefrigerator(boolean refrigerator) {
		this.refrigerator = refrigerator;
	}
	
	/**
	 * @return
	 * @uml.property  name="internet"
	 */
	public boolean isInternet() {
		return internet;
	}
	
	/**
	 * @param internet
	 * @uml.property  name="internet"
	 */
	public void setInternet(boolean internet) {
		this.internet = internet;
	}
	
	/**
	 * @return
	 * @uml.property  name="pc"
	 */
	public boolean isPc() {
		return pc;
	}
	
	/**
	 * @param pc
	 * @uml.property  name="pc"
	 */
	public void setPc(boolean pc) {
		this.pc = pc;
	}
	
	/**
	 * @return
	 * @uml.property  name="tv"
	 */
	public boolean isTv() {
		return tv;
	}
	
	/**
	 * @param tv
	 * @uml.property  name="tv"
	 */
	public void setTv(boolean tv) {
		this.tv = tv;
	}
	
	/**
	 * @return
	 * @uml.property  name="aircondition"
	 */
	public boolean isAircondition() {
		return aircondition;
	}
	
	/**
	 * @param aircondition
	 * @uml.property  name="aircondition"
	 */
	public void setAircondition(boolean aircondition) {
		this.aircondition = aircondition;
	}
	
	/**
	 * @return
	 * @uml.property  name="homeTheater"
	 */
	public boolean isHomeTheater() {
		return homeTheater;
	}
	
	/**
	 * @param homeTheater
	 * @uml.property  name="homeTheater"
	 */
	public void setHomeTheater(boolean homeTheater) {
		this.homeTheater = homeTheater;
	}
	
	/**
	 * @return
	 * @uml.property  name="bath"
	 */
	public boolean isBath() {
		return bath;
	}
	
	/**
	 * @param bath
	 * @uml.property  name="bath"
	 */
	public void setBath(boolean bath) {
		this.bath = bath;
	}
	
	/**
	 * @return
	 * @uml.property  name="bathFacility"
	 */
	public boolean isBathFacility() {
		return bathFacility;
	}
	
	/**
	 * @param bathFacility
	 * @uml.property  name="bathFacility"
	 */
	public void setBathFacility(boolean bathFacility) {
		this.bathFacility = bathFacility;
	}
	
	/**
	 * @return
	 * @uml.property  name="peakSeasonFee2"
	 */
	public String getPeakSeasonFee2() {
		return peakSeasonFee2;
	}
	
	/**
	 * @param peakSeasonFee2
	 * @uml.property  name="peakSeasonFee2"
	 */
	public void setPeakSeasonFee2(String peakSeasonFee2) {
		this.peakSeasonFee2 = peakSeasonFee2;
	}
	
	/**
	 * @return
	 * @uml.property  name="peakSeasonFee1"
	 */
	public String getPeakSeasonFee1() {
		return peakSeasonFee1;
	}
	
	/**
	 * @param peakSeasonFee1
	 * @uml.property  name="peakSeasonFee1"
	 */
	public void setPeakSeasonFee1(String peakSeasonFee1) {
		this.peakSeasonFee1 = peakSeasonFee1;
	}
	
	/**
	 * @return
	 * @uml.property  name="offSeasonFee2"
	 */
	public String getOffSeasonFee2() {
		return offSeasonFee2;
	}
	
	/**
	 * @param offSeasonFee2
	 * @uml.property  name="offSeasonFee2"
	 */
	public void setOffSeasonFee2(String offSeasonFee2) {
		this.offSeasonFee2 = offSeasonFee2;
	}
	
	/**
	 * @return
	 * @uml.property  name="offSeasonFee1"
	 */
	public String getOffSeasonFee1() {
		return offSeasonFee1;
	}
	
	/**
	 * @param offSeasonFee1
	 * @uml.property  name="offSeasonFee1"
	 */
	public void setOffSeasonFee1(String offSeasonFee1) {
		this.offSeasonFee1 = offSeasonFee1;
	}
	
	/**
	 * @return
	 * @uml.property  name="maxCount"
	 */
	public String getMaxCount() {
		return maxCount;
	}
	
	/**
	 * @param maxCount
	 * @uml.property  name="maxCount"
	 */
	public void setMaxCount(String maxCount) {
		this.maxCount = maxCount;
	}
	
	/**
	 * @return
	 * @uml.property  name="baseCount"
	 */
	public String getBaseCount() {
		return baseCount;
	}
	
	/**
	 * @param baseCount
	 * @uml.property  name="baseCount"
	 */
	public void setBaseCount(String baseCount) {
		this.baseCount = baseCount;
	}
	
	/**
	 * @return
	 * @uml.property  name="roomCount"
	 */
	public String getRoomCount() {
		return roomCount;
	}
	
	/**
	 * @param roomCount
	 * @uml.property  name="roomCount"
	 */
	public void setRoomCount(String roomCount) {
		this.roomCount = roomCount;
	}
	
	/**
	 * @return
	 * @uml.property  name="scale"
	 */
	public String getScale() {
		return scale;
	}
	
	/**
	 * @param scale
	 * @uml.property  name="scale"
	 */
	public void setScale(String scale) {
		this.scale = scale;
	}
	
	/**
	 * @return
	 * @uml.property  name="title"
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title
	 * @uml.property  name="title"
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}