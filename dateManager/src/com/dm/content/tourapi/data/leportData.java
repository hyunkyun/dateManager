package com.dm.content.tourapi.data;

import java.util.ArrayList;

/**
 * @author  Gwak seok jong
 */
public class leportData implements exData{
	/**
	 * @uml.property  name="mLeportIntro"
	 * @uml.associationEnd  
	 */
	private leportIntro mLeportIntro;
	/**
	 * @uml.property  name="mLeportDetail"
	 * @uml.associationEnd  
	 */
	private leportDetail mLeportDetail;
	
	/**
	 * @author  Gwak seok jong
	 */
	public class leportIntro implements intro{
		/**
		 * @uml.property  name="infoCenter"
		 */
		private String infoCenter;
		/**
		 * @uml.property  name="scale"
		 */
		private String scale;
		/**
		 * @uml.property  name="accomCount"
		 */
		private String accomCount;
		/**
		 * @uml.property  name="restDate"
		 */
		private String restDate;
		/**
		 * @uml.property  name="openPeriod"
		 */
		private String openPeriod;
		/**
		 * @uml.property  name="useTime"
		 */
		private String useTime;
		/**
		 * @uml.property  name="useFee"
		 */
		private String useFee;
		/**
		 * @uml.property  name="reservation"
		 */
		private String reservation;
		/**
		 * @uml.property  name="parking"
		 */
		private String parking;
		/**
		 * @uml.property  name="restRoom"
		 */
		private String restRoom;
		
		public leportIntro(
				String _infoCenter, String _scale, String _accomCount,
				String _restDate, String _openPeriod, String _useTime,
				String _useFee, String _reservation, String _parking, String _restRoom){
			infoCenter = _infoCenter;
			scale = _scale;
			accomCount = _accomCount;
			restDate = _restDate;
			openPeriod = _openPeriod;
			useTime = _useTime;
			useFee = _useFee;
			reservation = _reservation;
			parking = _parking;
			restRoom = _restRoom;
		}
		
		/**
		 * @return
		 * @uml.property  name="infoCenter"
		 */
		public String getInfoCenter(){
			return infoCenter;
		}
		
		/**
		 * @return
		 * @uml.property  name="scale"
		 */
		public String getScale(){
			return scale;
		}
		
		/**
		 * @return
		 * @uml.property  name="accomCount"
		 */
		public String getAccomCount(){
			return accomCount;
		}
		
		/**
		 * @return
		 * @uml.property  name="restDate"
		 */
		public String getRestDate(){
			return restDate;
		}
		
		/**
		 * @return
		 * @uml.property  name="openPeriod"
		 */
		public String getOpenPeriod(){
			return openPeriod;
		}
		
		/**
		 * @return
		 * @uml.property  name="useTime"
		 */
		public String getUseTime(){
			return useTime;
		}
		
		/**
		 * @return
		 * @uml.property  name="useFee"
		 */
		public String getUseFee(){
			return useFee;
		}
		
		/**
		 * @return
		 * @uml.property  name="reservation"
		 */
		public String getReservation(){
			return reservation;
		}
		
		/**
		 * @return
		 * @uml.property  name="parking"
		 */
		public String getParking(){
			return parking;
		}
		
		/**
		 * @return
		 * @uml.property  name="restRoom"
		 */
		public String getRestRoom(){
			return restRoom;
		}

		public void print() {
			// TODO Auto-generated method stub
			System.out.println(infoCenter + " " + scale + " " + accomCount + " " + restDate + " " + openPeriod + " " +
					useTime + " " + useFee + " " + reservation + " " + parking + " " + restRoom);
		}
	}
	
	/**
	 * @uml.dependency  supplier="org.test.data.infoList"
	 */
	public class leportDetail implements detail{
		/**
		 * @uml.property  name="introList"
		 */
		private ArrayList<infoList> introList;
		/**
		 * @uml.property  name="useList"
		 */
		private ArrayList<infoList> useList;
		/**
		 * @uml.property  name="facilList"
		 */
		private ArrayList<infoList> facilList;
		
		public leportDetail(ArrayList<infoList> _introList, 
				ArrayList<infoList> _useList,
				ArrayList<infoList> _facilList){
			setIntroList(_introList);
			setUseList(_useList);
			setUseList(_facilList);
		}

		/**
		 * @return
		 * @uml.property  name="introList"
		 */
		public ArrayList<infoList> getIntroList() {
			return introList;
		}

		/**
		 * @param _introList
		 * @uml.property  name="introList"
		 */
		public void setIntroList(ArrayList<infoList> introList) {
			this.introList = introList;
		}

		/**
		 * @return
		 * @uml.property  name="useList"
		 */
		public ArrayList<infoList> getUseList() {
			return useList;
		}
		
		/**
		 * @param _useList
		 * @uml.property  name="useList"
		 */
		public void setUseList(ArrayList<infoList> _useList) {
			this.useList = _useList;
		}

		/**
		 * @return
		 * @uml.property  name="facilList"
		 */
		public ArrayList<infoList> getFacilList() {
			return facilList;
		}
		
		/**
		 * @param _facilList
		 * @uml.property  name="facilList"
		 */
		public void setFacilList(ArrayList<infoList> _facilList) {
			this.facilList = _facilList;
		}

		public void print() {
			// TODO Auto-generated method stub
			if(introList != null)
				for(infoList intro : introList){
					intro.print();
				}
			if(useList != null)
				for(infoList intro : useList){
					intro.print();
				}
			if(facilList != null)
				for(infoList use : facilList){
					use.print();
				}
		}
		
	}
	
	public leportData(
			String _infoCenter, String _scale, String _accomCount,
			String _restDate, String _openPeriod, String _useTime,
			String _useFee, String _reservation, String _parking, String _restRoom, 
			ArrayList<infoList> _introList, 
			ArrayList<infoList> _useList,
			ArrayList<infoList> _facilList){
		setmLeportIntro(new leportIntro(
				_infoCenter, _scale, _accomCount,
				_restDate, _openPeriod, _useTime,
				_useFee, _reservation, _parking, _restRoom));
		setmLeportDetail(new leportDetail(_introList, _useList, _facilList));
	}

	/**
	 * @param mLeportIntro
	 * @uml.property  name="mLeportIntro"
	 */
	public void setmLeportIntro(leportIntro mLeportIntro) {
		this.mLeportIntro = mLeportIntro;
	}

	/**
	 * @param mLeportDetail
	 * @uml.property  name="mLeportDetail"
	 */
	public void setmLeportDetail(leportDetail mLeportDetail) {
		this.mLeportDetail = mLeportDetail;
	}
	
	/**
	 * @param leportIntro
	 * @uml.property  name="leportIntro"
	 */
	public leportIntro getLeportIntro(){
		return mLeportIntro;
	}
	
	/**
	 * @param leportDetail
	 * @uml.property  name="leportDetail"
	 */
	public leportDetail getLeportDetail(){
		return mLeportDetail;
	}
	
	public void print(){
		if(mLeportIntro != null) mLeportIntro.print();
		if(mLeportDetail != null) mLeportDetail.print();
	}
}
