package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class area {
	/**
	 * @uml.property  name="code1"
	 */
	private String code1;
	/**
	 * @uml.property  name="code2"
	 */
	private String code2;
	/**
	 * @uml.property  name="code3"
	 */
	private String code3;
	
	public area(String _code1, String _code2, String _code3){
		setCode1(_code1);
		setCode2(_code2);
		setCode3(_code3);
	}

	/**
	 * @return
	 * @uml.property  name="code1"
	 */
	public String getCode1() {
		return code1;
	}

	/**
	 * @param code1
	 * @uml.property  name="code1"
	 */
	public void setCode1(String code1) {
		this.code1 = code1;
	}

	/**
	 * @return
	 * @uml.property  name="code2"
	 */
	public String getCode2() {
		return code2;
	}

	/**
	 * @param code2
	 * @uml.property  name="code2"
	 */
	public void setCode2(String code2) {
		this.code2 = code2;
	}

	/**
	 * @return
	 * @uml.property  name="code3"
	 */
	public String getCode3() {
		return code3;
	}

	/**
	 * @param code3
	 * @uml.property  name="code3"
	 */
	public void setCode3(String code3) {
		this.code3 = code3;
	}
}
