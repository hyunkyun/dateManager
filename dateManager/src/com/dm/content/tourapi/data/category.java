package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class category {
	/**
	 * @uml.property  name="cat1"
	 */
	private String cat1;
	/**
	 * @uml.property  name="cat2"
	 */
	private String cat2;
	/**
	 * @uml.property  name="cat3"
	 */
	private String cat3;

	public category(String _cat1, String _cat2, String _cat3){
		setCat1(_cat1);
		setCat2(_cat2);
		setCat3(_cat3);
	}
	
	/**
	 * @return
	 * @uml.property  name="cat1"
	 */
	public String getCat1() {
		return cat1;
	}

	/**
	 * @param cat1
	 * @uml.property  name="cat1"
	 */
	public void setCat1(String cat1) {
		this.cat1 = cat1;
	}

	/**
	 * @return
	 * @uml.property  name="cat2"
	 */
	public String getCat2() {
		return cat2;
	}

	/**
	 * @param cat2
	 * @uml.property  name="cat2"
	 */
	public void setCat2(String cat2) {
		this.cat2 = cat2;
	}

	/**
	 * @return
	 * @uml.property  name="cat3"
	 */
	public String getCat3() {
		return cat3;
	}

	/**
	 * @param cat3
	 * @uml.property  name="cat3"
	 */
	public void setCat3(String cat3) {
		this.cat3 = cat3;
	}
}
