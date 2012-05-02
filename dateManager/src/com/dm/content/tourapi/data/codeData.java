package com.dm.content.tourapi.data;


import android.os.Parcel;

/**
 * @author  Gwak seok jong
 */
public class codeData implements xmlData{
	private String mType;
	/**
	 * @uml.property  name="mAreaCode"
	 * @uml.associationEnd  
	 */
	private areaCode mAreaCode;
	
	public codeData(String _type, areaCode _areaCode){
		setType(_type);
		setAreaCode(_areaCode);
	}
	
	public void print() {
		// TODO Auto-generated method stub
		System.out.println("type : " + mType);
		mAreaCode.print();
	}

	public xmlData getObject() {
		// TODO Auto-generated method stub
		return this;
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
	}

	
	/**
	 * @return
	 * @uml.property  name="areaCode"
	 */
	public areaCode getAreaCode() {
		return mAreaCode;
	}
	
	/**
	 * @param areaCode
	 * @uml.property  name="areaCode"
	 */
	public void setAreaCode(areaCode mAreaCode) {
		this.mAreaCode = mAreaCode;
	}
	
	/**
	 * @return
	 * @uml.property  name="type"
	 */
	public String getType() {
		return mType;
	}
	
	/**
	 * @param type
	 * @uml.property  name="type"
	 */
	public void setType(String mType) {
		this.mType = mType;
	}
}
