package com.dm.content.tourapi.data;

import android.os.Parcel;

/**
 * @author  Gwak seok jong
 */
public class unionData implements xmlData{
	private int mContentId;
	private int mType;
	private String mTitle;
	private String mTel;
	/**
	 * @uml.property  name="m"
	 * @uml.associationEnd  
	 */
	private gpsLocation mMap;
	/**
	 * @uml.property  name="mArea"
	 * @uml.associationEnd  
	 */
	private area mArea;
	/**
	 * @uml.property  name="mAddress"
	 * @uml.associationEnd  
	 */
	private address mAddress;
	/**
	 * @uml.property  name="mCategory"
	 * @uml.associationEnd  
	 */
	private category mCategory;
	/**
	 * @uml.property  name="mDate"
	 * @uml.associationEnd  
	 */
	private date mDate;
	
	public unionData(int _contentId, int _type, String _title,
			String _tel, gpsLocation _map, area _area,
			address _address, category _category, date _date){
		setContentId(_contentId);
		setType(_type);
		setTitle(_title);
		setTel(_tel);
		setMap(_map);
		setArea(_area);
		setAddress(_address);
		setCategory(_category);
		setDate(_date);
	}
	
	public xmlData getObject(){
		return this;
	}

	public void print() {
		// TODO Auto-generated method stub
		
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeValue(mContentId);
		dest.writeValue(mTitle);
		dest.writeValue(mType);
		dest.writeValue(mTel);
		dest.writeValue(mMap);
		dest.writeValue(mArea);
		dest.writeValue(mAddress);
		dest.writeValue(mCategory);
		dest.writeValue(mDate);
		/*  int _contentId, int _type, String _title,
			String _tel, gpsLocation _map, area _area,
			address _address, category _category, date _date
		 * 
		 */
	}
	
	public void readFromParcel(Parcel in){
		
	}

	/**
	 * @return
	 * @uml.property  name="contentId"
	 */
	public int getContentId() {
		return mContentId;
	}

	/**
	 * @param contentId
	 * @uml.property  name="contentId"
	 */
	public void setContentId(int mContentId) {
		this.mContentId = mContentId;
	}

	
	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public int getType() {
		return mType;
	}

	/**
	 * @param type
	 * @uml.property  name="type"
	 */
	public void setType(int mType) {
		this.mType = mType;
	}

	
	/**
	 * @return
	 * @uml.property  name="tel"
	 */
	public String getTel() {
		return mTel;
	}
	
	/**
	 * @param tel
	 * @uml.property  name="tel"
	 */
	public void setTel(String mTel) {
		this.mTel = mTel;
	}

	
	/**
	 * @return
	 * @uml.property  name="title"
	 */
	public String getTitle() {
		return mTitle;
	}
	
	/**
	 * @param title
	 * @uml.property  name="title"
	 */
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	
	/**
	 * @return
	 * @uml.property  name="map"
	 */
	public gpsLocation getMap() {
		return mMap;
	}

	/**
	 * @param map
	 * @uml.property  name="map"
	 */
	public void setMap(gpsLocation mMap) {
		this.mMap = mMap;
	}

	
	/**
	 * @return
	 * @uml.property  name="area"
	 */
	public area getArea() {
		return mArea;
	}

	/**
	 * @param area
	 * @uml.property  name="area"
	 */
	public void setArea(area mArea) {
		this.mArea = mArea;
	}

	/**
	 * @return
	 * @uml.property  name="address"
	 */
	public address getAddress() {
		return mAddress;
	}

	/**
	 * @param address
	 * @uml.property  name="address"
	 */
	public void setAddress(address mAddress) {
		this.mAddress = mAddress;
	}

	
	/**
	 * @return
	 * @uml.property  name="category"
	 */
	public category getCategory() {
		return mCategory;
	}
	
	/**
	 * @param category
	 * @uml.property  name="category"
	 */
	public void setCategory(category mCategory) {
		this.mCategory = mCategory;
	}

	
	/**
	 * @return
	 * @uml.property  name="date"
	 */
	public date getDate() {
		return mDate;
	}
	
	/**
	 * @param date
	 * @uml.property  name="date"
	 */
	public void setDate(date mDate) {
		this.mDate = mDate;
	}
}
