package com.dm.content.tourapi.data;

import java.util.ArrayList;

/**
 * @author  Gwak seok jong
 */
public class cultureData implements exData{
	/**
	 * @uml.property  name="mCultureIntro"
	 * @uml.associationEnd  
	 */
	private cultureIntro mCultureIntro;
	/**
	 * @uml.property  name="mCultureDetail"
	 * @uml.associationEnd  
	 */
	private cultureDetail mCultureDetail;
	
	/**
	 * @author  Gwak seok jong
	 */
	public class cultureIntro implements intro{
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
		 * @uml.property  name="useFee"
		 */
		private String useFee;
		/**
		 * @uml.property  name="useTime"
		 */
		private String useTime;
		/**
		 * @uml.property  name="restDate"
		 */
		private String restDate;
		/**
		 * @uml.property  name="parking"
		 */
		private String parking;
		/**
		 * @uml.property  name="checkCreditCard"
		 */
		private String checkCreditCard;
		/**
		 * @uml.property  name="checkTextBook"
		 */
		private boolean checkTextBook;
		
		public cultureIntro(
				String _infoCenter, String _scale, String _accomCount,
				String _useFee, String _useTime, String _restDate,
				String _parking, String _checkCreditCard, boolean _checkTextBook){
			infoCenter = _infoCenter;
			scale = _scale;
			accomCount = _accomCount;
			useTime = _useTime;
			useFee = _useFee;
			restDate = _restDate;
			parking = _parking;
			checkCreditCard = _checkCreditCard;
			checkTextBook = _checkTextBook;
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
		 * @uml.property  name="checkCreditCard"
		 */
		public String getCheckCreditCard(){
			return checkCreditCard;
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
		 * @uml.property  name="checkTextBook"
		 */
		public boolean getCheckTextBook(){
			return checkTextBook;
		}

		public void print() {
			// TODO Auto-generated method stub
			System.out.println(infoCenter + " " + scale + " " + accomCount + " " + useFee + " " + useTime + " " +
					restDate + " " + parking + " " + checkCreditCard + " " + checkTextBook);
		}
	}
	
	/**
	 * @uml.dependency  supplier="org.test.data.infoList"
	 */
	public class cultureDetail implements detail{
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
		
		public cultureDetail(ArrayList<infoList> _introList, 
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
	
	public cultureData(
			String _infoCenter, String _scale, String _accomCount,
			String _useFee, String _useTime, String _restDate, 
			String _parking, String _checkCreditCard, boolean _checkTextBook, 
			ArrayList<infoList> _introList, 
			ArrayList<infoList> _useList,
			ArrayList<infoList> _facilList){
		setmCultureIntro(new cultureIntro(
				_infoCenter, _scale, _accomCount,
				_useFee, _useTime, _restDate,
				_parking, _checkCreditCard, _checkTextBook));
		setmCultureDetail(new cultureDetail(_introList, _useList, _facilList));
	}

	/**
	 * @param mCultureIntro
	 * @uml.property  name="mCultureIntro"
	 */
	public void setmCultureIntro(cultureIntro mCultureIntro) {
		this.mCultureIntro = mCultureIntro;
	}

	/**
	 * @param mCultureDetail
	 * @uml.property  name="mCultureDetail"
	 */
	public void setmCultureDetail(cultureDetail mCultureDetail) {
		this.mCultureDetail = mCultureDetail;
	}
	
	/**
	 * @return
	 * @uml.property  name="cultureIntro"
	 */
	public cultureIntro getLeportIntro(){
		return mCultureIntro;
	}
	
	/**
	 * @return
	 * @uml.property  name="cultureDetail"
	 */
	public cultureDetail getCultureDetail(){
		return mCultureDetail;
	}
	
	public void print(){
		if(mCultureIntro != null) mCultureIntro.print();
		if(mCultureDetail != null) mCultureDetail.print();
	}
}