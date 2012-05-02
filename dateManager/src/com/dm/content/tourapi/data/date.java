package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class date {
	/**
	 * @uml.property  name="createdTime"
	 */
	private String mCreatedTime;
	/**
	 * @uml.property  name="modifiedTime"
	 */
	private String mModifiedTime;
	
	
	public date(String _createdTime, String _modifiedTime){
		setCreatedTime(_createdTime);
		setModifiedTime(_modifiedTime);
	}

	/**
	 * @return
	 * @uml.property  name="modifiedTime"
	 */
	public String getModifiedTime() {
		return mModifiedTime;
	}

	/**
	 * @param modifiedTime
	 * @uml.property  name="modifiedTime"
	 */
	public void setModifiedTime(String mModifiedTime) {
		this.mModifiedTime = mModifiedTime;
	}

	/**
	 * @return
	 * @uml.property  name="createdTime"
	 */
	public String getCreatedTime() {
		return mCreatedTime;
	}
	
	/**
	 * @param modifiedTime
	 * @uml.property  name="createdTime"
	 */
	public void setCreatedTime(String createdTime) {
		this.mCreatedTime = createdTime;
	}	
}
