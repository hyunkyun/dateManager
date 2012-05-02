package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class areaCode {
	/**
	 * @uml.property  name="code"
	 */
	private String mCode;
	/**
	 * @uml.property  name="codeName"
	 */
	private String mCodeName;
	
	public areaCode(String _code, String _codeName){
		setCode(_code);
		setCodeName(_codeName);
	}

	/**
	 * @return
	 * @uml.property  name="code"
	 */
	public String getCode() {
		return mCode;
	}

	/**
	 * @param code
	 * @uml.property  name="code"
	 */
	public void setCode(String mCode) {
		this.mCode = mCode;
	}

	/**
	 * @return
	 * @uml.property  name="codeName"
	 */
	public String getCodeName() {
		return mCodeName;
	}

	/**
	 * @param codeName
	 * @uml.property  name="codeName"
	 */
	public void setCodeName(String mCodeName) {
		this.mCodeName = mCodeName;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println("Code : " + mCode + ", CodeName : " + mCodeName);
	}
}
