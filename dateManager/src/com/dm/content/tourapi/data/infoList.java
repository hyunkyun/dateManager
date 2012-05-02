package com.dm.content.tourapi.data;

/**
 * @author  Gwak seok jong
 */
public class infoList {
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="content"
	 */
	private String content;
	
	public infoList(String _name, String _content){
		name = _name;
		content = _content;
	}
	
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return
	 * @uml.property  name="content"
	 */
	public String getContent(){
		return content;
	}
	
	public void print(){
		System.out.println(name + " " + content);
	}
}
