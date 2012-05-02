package com.dm.content.tourapi.data;

import java.util.ArrayList;

/**
 * @author  Gwak seok jong
 * @uml.dependency  supplier="org.test.data.infoList" stereotypes="Standard::Responsibility"
 */
public class relateGuide {
	/**
	 * @uml.property  name="tourGuide"
	 */
	private String tourGuide;
	/**
	 * @uml.property  name="infoList"
	 */
	private ArrayList<infoList> infoList;
	
	public relateGuide(String _tourGuide, ArrayList<infoList> _infoList){
		tourGuide = _tourGuide;
		infoList = _infoList;
	}
	
	public void addInfoList(infoList _infolist){
		infoList.add(_infolist);
	}
	
	/**
	 * @return
	 * @uml.property  name="tourGuide"
	 */
	public String getTourGuide(){
		return tourGuide;
	}
	
	/**
	 * @return
	 * @uml.property  name="infoList"
	 */
	public ArrayList<infoList> getInfoList(){
		return infoList;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(tourGuide);
		if(infoList != null)
			for(infoList info : infoList){
				info.print();
			}
	}
}
