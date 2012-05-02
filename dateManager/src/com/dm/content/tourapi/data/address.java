package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class address {
	/**
	 * @uml.property  name="address"
	 */
	private String address;
	/**
	 * @uml.property  name="zipcode"
	 */
	private String zipcode;
	/**
	 * @uml.property  name="detail"
	 */
	private String detail;
	
	public address(){
		address = null;
		zipcode = null;
		detail = null;
	}
	
	public address(String _address, String _zipcode, String _detail){
		address = _address;
		zipcode = _zipcode;
		detail = _detail;
	}
	
	/**
	 * @param _address
	 * @uml.property  name="address"
	 */
	public void setAddress(String _address){
		address = _address;
	}
	
	/**
	 * @param _zipcode
	 * @uml.property  name="zipcode"
	 */
	public void setZipcode(String _zipcode){
		zipcode = _zipcode;
	}
	
	/**
	 * @param _detail
	 * @uml.property  name="detail"
	 */
	public void setDetail(String _detail){
		detail = _detail;
	}
	
	/**
	 * @return
	 * @uml.property  name="address"
	 */
	public String getAddress(){
		return address;
	}
	
	/**
	 * @return
	 * @uml.property  name="zipcode"
	 */
	public String getZipcode(){
		return zipcode;
	}
	
	/**
	 * @return
	 * @uml.property  name="detail"
	 */
	public String getDetail(){
		return detail;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(zipcode + " " + address + " " + detail);
	}

}
