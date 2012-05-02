package com.dm.content.tourapi.data;

import java.util.ArrayList;

import android.os.Parcel;

/**
 * @author      Gwak seok jong
 * @uml.dependency  supplier="org.test.data.imageList"
 */
public class detailData implements xmlData{
	private String mTitle;
	private String mModifiedTime;
	private String mMapImg;
	private String mFirstImg;
	private String mHomepage;
	private String mManager;
	private String mTel;
	private String mOverview;
	private String mTransGuide;
	private String mVideoURL;
	/**
	 * @uml.property  name="m"
	 * @uml.associationEnd  
	 */
	private gpsLocation mMap;
	/**
	 * @uml.property  name="mRelateGuide"
	 * @uml.associationEnd  
	 */
	private relateGuide mRelateGuide;
	/**
	 * @uml.property  name="mImageList"
	 */
	private ArrayList<imageList> mImageList;
	/**
	 * @uml.property  name="mAddress"
	 * @uml.associationEnd  
	 */
	private address mAddress;
	/**
	 * @uml.property  name="mExData"
	 * @uml.associationEnd  
	 */
	public exData mExData;

	public detailData(String _title, String _modified,
			String _mapImg, String _firstImg, address _address,
			gpsLocation _map, String _homepage, String _manager,
			String _tel, String _overview, String _trans,
			relateGuide _relate, ArrayList<imageList> _imageList,
			String _videoURL, exData _exData) {
		// TODO Auto-generated constructor stub
		setTitle(_title);
		setModifiedTime(_modified);
		setMapImg(_mapImg);
		setFirstImg(_firstImg);
		setAddress(_address);
		setMap(_map);
		setHomepage(_homepage);
		setManager(_manager);
		setTel(_tel);
		setOverview(_overview);
		setTransGuide(_trans);
		setRelateGuide(_relate);
		setImageList(_imageList);
		setVideoURL(_videoURL);
		mExData = _exData;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub

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
	 * @uml.property  name="modifiedTime"
	 */
	public String getModifiedTime() {
		return mModifiedTime;
	}

	/**
	 * @param date
	 * @uml.property  name="modifiedTime"
	 */
	public void setModifiedTime(String mModifiedTime) {
		this.mModifiedTime = mModifiedTime;
	}

	/**
	 * @return
	 * @uml.property  name="mapImg"
	 */
	public String getMapImg() {
		return mMapImg;
	}

	/**
	 * @param mapImg
	 * @uml.property  name="mapImg"
	 */
	public void setMapImg(String mMapImg) {
		this.mMapImg = mMapImg;
	}

	/**
	 * @return
	 * @uml.property  name="firstImg"
	 */
	public String getFirstImg() {
		return mFirstImg;
	}

	/**
	 * @param firstImg
	 * @uml.property  name="firstImg"
	 */
	public void setFirstImg(String mFirstImg) {
		this.mFirstImg = mFirstImg;
	}

	/**
	 * @return
	 * @uml.property  name="homepage"
	 */
	public String getHomepage() {
		return mHomepage;
	}

	/**
	 * @param homepage
	 * @uml.property  name="homepage"
	 */
	public void setHomepage(String mHomepage) {
		this.mHomepage = mHomepage;
	}

	/**
	 * @return
	 * @uml.property  name="manager"
	 */
	public String getManager() {
		return mManager;
	}
	
	/**
	 * @param manager
	 * @uml.property  name="manager"
	 */
	public void setManager(String mManager) {
		this.mManager = mManager;
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
	 * @uml.property  name="overview"
	 */
	public String getOverview() {
		return mOverview;
	}

	/**
	 * @param overview
	 * @uml.property  name="overview"
	 */
	public void setOverview(String mOverview) {
		this.mOverview = mOverview;
	}

	/**
	 * @return
	 * @uml.property  name="transGuide"
	 */
	public String getTransGuide() {
		return mTransGuide;
	}

	/**
	 * @param transGuide
	 * @uml.property  name="transGuide"
	 */
	public void setTransGuide(String mTransGuide) {
		this.mTransGuide = mTransGuide;
	}

	/**
	 * @return
	 * @uml.property  name="videoURL"
	 */
	public String getVideoURL() {
		return mVideoURL;
	}

	/**
	 * @param videoURL
	 * @uml.property  name="videoURL"
	 */
	public void setVideoURL(String mVideoURL) {
		this.mVideoURL = mVideoURL;
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
	 * @uml.property  name="relateGuide"
	 */
	public relateGuide getRelateGuide() {
		return mRelateGuide;
	}

	/**
	 * @param relateGuide
	 * @uml.property  name="relateGuide"
	 */
	public void setRelateGuide(relateGuide mRelateGuide) {
		this.mRelateGuide = mRelateGuide;
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
	 * @uml.property  name="imageList"
	 */
	public ArrayList<imageList> getImageList() {
		return mImageList;
	}

	/**
	 * @param imageList
	 * @uml.property  name="imageList"
	 */
	public void setImageList(ArrayList<imageList> mImageList) {
		this.mImageList = mImageList;
	}
	
	public xmlData getObject() {
		// TODO Auto-generated method stub
		return this;
	}	

	public void print(){
		// TODO Auto-generated method stub
		System.out.println( mTitle + " " + mModifiedTime + " " + mMapImg + " " +
				mFirstImg + " " + mHomepage + " " + mManager + " " + mTel + " " +
				mOverview + " " + mTransGuide + " " + mVideoURL);

		if(mAddress != null) mAddress.print();
		if(mMap != null) mMap.print();
		if(mRelateGuide != null) mRelateGuide.print();
		if(mImageList != null)
			for(imageList image : mImageList){
				image.print();
			}

		if(mExData != null)	mExData.print();
	}
}
