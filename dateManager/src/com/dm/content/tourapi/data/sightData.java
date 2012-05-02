package com.dm.content.tourapi.data;

import java.util.ArrayList;


/** 
 * @author    Gwak seok jong
 * @uml.dependency supplier="org.test.data.exData"
 */
public class sightData implements exData{
	/**
	 * @uml.property  name="mSightIntro"
	 * @uml.associationEnd  
	 */
	private sightIntro mSightIntro;
	/**
	 * @uml.property  name="mSightDetail"
	 * @uml.associationEnd  
	 */
	private sightDetail mSightDetail;
	
	/**
	 * @author  Gwak seok jong
	 */
	public class sightIntro implements intro{
		/**
		 * @uml.property  name="heri_1"
		 */
		private boolean heri_1;
		/**
		 * @uml.property  name="heri_2"
		 */
		private boolean heri_2;
		/**
		 * @uml.property  name="heri_3"
		 */
		private boolean heri_3;
		/**
		 * @uml.property  name="checkTextBook"
		 */
		private boolean checkTextBook;
		/**
		 * @uml.property  name="infoCenter"
		 */
		private String infoCenter;
		/**
		 * @uml.property  name="openDate"
		 */
		private String openDate;
		/**
		 * @uml.property  name="restDate"
		 */
		private String restDate;
		/**
		 * @uml.property  name="expGuide"
		 */
		private String expGuide;
		/**
		 * @uml.property  name="useSeason"
		 */
		private String useSeason;
		/**
		 * @uml.property  name="checkCreditCard"
		 */
		private String checkCreditCard;
		/**
		 * @uml.property  name="useTime"
		 */
		private String useTime;
		/**
		 * @uml.property  name="parking"
		 */
		private String parking;
		
		public sightIntro(boolean _heri_1, boolean _heri_2, boolean _heri_3,
				String _infoCenter, String _openDate, String _restDate,
				String _expGuide, String _useSeason, String _checkCreditCard,
				String _useTime, String _parking, boolean _checkTextBook){
			heri_1 = _heri_1;
			heri_2 = _heri_2;
			heri_3 = _heri_3;
			infoCenter = _infoCenter;
			openDate = _openDate;
			restDate = _restDate;
			expGuide = _expGuide;
			useSeason = _useSeason;
			checkCreditCard = _checkCreditCard;
			useTime = _useTime;
			parking = _parking;
			checkTextBook = _checkTextBook;
		}
		
		/**
		 * @return
		 * @uml.property  name="isHeri1"
		 */
		public boolean isHeri1(){
			return heri_1;
		}
		
		/**
		 * @return
		 * @uml.property  name="isHeri2"
		 */
		public boolean isHeri2(){
			return heri_2;
		}
		
		/**
		 * @return
		 * @uml.property  name="isHeri3"
		 */
		public boolean isHeri3(){
			return heri_3;
		}
		
		/**
		 * @return
		 * @uml.property  name="checkTextBook"
		 */
		public boolean isCheckTextBook(){
			return checkTextBook;
		}
		
		public boolean[] isHeri(){
			boolean[] ret = { false, false, false };
			ret[0] = heri_1;
			ret[1] = heri_2;
			ret[2] = heri_3;
			return ret;
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
		 * @uml.property  name="openDate"
		 */
		public String getOpenDate(){
			return openDate;
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
		 * @uml.property  name="expGuide"
		 */
		public String getExpGuide(){
			return expGuide;
		}
		
		/**
		 * @return
		 * @uml.property  name="useSeason"
		 */
		public String getUseSeason(){
			return useSeason;
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
		 * @uml.property  name="useTime"
		 */
		public String getUseTime(){
			return useTime;
		}
		
		/**
		 * @return
		 * @uml.property  name="parking"
		 */
		public String getParking(){
			return parking;
		}

		public void print() {
			// TODO Auto-generated method stub
			System.out.println(heri_1 + " " + heri_2 + " " + heri_3 + " " + infoCenter + " " + openDate + " " +
					restDate + " " + expGuide + " " + useSeason + " " + checkCreditCard + " " + useTime + " " +
					parking + " " + checkTextBook);
		}
	}
	
	/**
	 * @uml.dependency  supplier="org.test.data.infoList"
	 */
	public class sightDetail implements detail{
		/**
		 * @uml.property  name="introList_1"
		 */
		private ArrayList<infoList> introList_1;
		/**
		 * @uml.property  name="introList_2"
		 */
		private ArrayList<infoList> introList_2;
		/**
		 * @uml.property  name="useList_1"
		 */
		private ArrayList<infoList> useList_1;
		/**
		 * @uml.property  name="useList_2"
		 */
		private ArrayList<infoList> useList_2;
		
		public sightDetail(ArrayList<infoList> _introList_1,
				ArrayList<infoList> _introList_2, 
				ArrayList<infoList> _useList_1,
				ArrayList<infoList> _useList_2){
			setIntroList_1(_introList_1);
			setIntroList_2(_introList_2);
			setUseList_1(_useList_1);
			setUseList_2(_useList_2);
		}

		/**
		 * @return
		 * @uml.property  name="introList_1"
		 */
		public ArrayList<infoList> getIntroList_1() {
			return introList_1;
		}

		/**
		 * @param introList_1
		 * @uml.property  name="introList_1"
		 */
		public void setIntroList_1(ArrayList<infoList> introList_1) {
			this.introList_1 = introList_1;
		}

		/**
		 * @return
		 * @uml.property  name="introList_2"
		 */
		public ArrayList<infoList> getIntroList_2() {
			return introList_2;
		}

		/**
		 * @param introList_2
		 * @uml.property  name="introList_2"
		 */
		public void setIntroList_2(ArrayList<infoList> introList_2) {
			this.introList_2 = introList_2;
		}

		/**
		 * @return
		 * @uml.property  name="useList_1"
		 */
		public ArrayList<infoList> getUseList_1() {
			return useList_1;
		}

		/**
		 * @param useList_1
		 * @uml.property  name="useList_1"
		 */
		public void setUseList_1(ArrayList<infoList> useList_1) {
			this.useList_1 = useList_1;
		}

		/**
		 * @return
		 * @uml.property  name="useList_2"
		 */
		public ArrayList<infoList> getUseList_2() {
			return useList_2;
		}
		
		/**
		 * @param useList_2
		 * @uml.property  name="useList_2"
		 */
		public void setUseList_2(ArrayList<infoList> useList_2) {
			this.useList_2 = useList_2;
		}
		
		public void print() {
			// TODO Auto-generated method stub
			if(introList_1 != null)
				for(infoList intro : introList_1){
					intro.print();
				}
			if(introList_2 != null)
				for(infoList intro : introList_2){
					intro.print();
				}
			if(useList_1 != null)
				for(infoList use : useList_1){
					use.print();
				}
			if(useList_2 != null)
				for(infoList use : useList_1){
					use.print();
				}
		}
		
	}
	
	public sightData(boolean _heri_1, boolean _heri_2, boolean _heri_3,
			String _infoCenter, String _openDate, String _restDate,
			String _expGuide, String _useSeason, String _checkCreditCard,
			String _useTime, String _parking, boolean _checkTextBook, 
			ArrayList<infoList> _introList_1,
			ArrayList<infoList> _introList_2, 
			ArrayList<infoList> _useList_1,
			ArrayList<infoList> _useList_2){
		setmSightIntro(new sightIntro(_heri_1, _heri_2, _heri_3,
				_infoCenter, _openDate, _restDate,
				_expGuide, _useSeason, _checkCreditCard,
				_useTime, _parking, _checkTextBook));
		setmSightDetail(new sightDetail(_introList_1, _introList_2, _useList_1, _useList_2));
	}

	/**
	 * @param mSightIntro
	 * @uml.property  name="mSightIntro"
	 */
	public void setmSightIntro(sightIntro mSightIntro) {
		this.mSightIntro = mSightIntro;
	}

	/**
	 * @param mSightDetail
	 * @uml.property  name="mSightDetail"
	 */
	public void setmSightDetail(sightDetail mSightDetail) {
		this.mSightDetail = mSightDetail;
	}
	
	/**
	 * @param sightIntro
	 * @uml.property  name="sightIntro"
	 */
	public sightIntro getSightIntro(){
		return mSightIntro;
	}
	
	/**
	 * @param sightDetail
	 * @uml.property  name="sightDetail"
	 */
	public sightDetail getSightDetail(){
		return mSightDetail;
	}
	
	public void print(){
		if(mSightIntro != null) mSightIntro.print();
		if(mSightDetail != null) mSightDetail.print();
	}
}
