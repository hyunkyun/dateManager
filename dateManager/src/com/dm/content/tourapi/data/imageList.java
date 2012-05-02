package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class imageList {
	/**
	 * @uml.property  name="imageURL"
	 */
	private String imageURL;
	/**
	 * @uml.property  name="imageName"
	 */
	private String imageName;
	
	public imageList(String _imgURL, String _imgName){
		imageURL = _imgURL;
		imageName = _imgName;
	}
	
	/**
	 * @return
	 * @uml.property  name="imageURL"
	 */
	public String getImgURL(){
		return imageURL;
	}
	
	/**
	 * @return
	 * @uml.property  name="imageName"
	 */
	public String getImgName(){
		return imageName;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(imageURL + " " + imageName);
	}
}
