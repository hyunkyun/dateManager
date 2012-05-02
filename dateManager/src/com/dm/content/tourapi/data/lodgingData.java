package com.dm.content.tourapi.data;

import java.util.ArrayList;

/**
 * @author  Gwak seok jong
 */
public class lodgingData implements exData{
	/**
	 * @uml.property  name="mLodgingIntro"
	 * @uml.associationEnd  
	 */
	private lodgingIntro mLodgingIntro;
	/**
	 * @uml.property  name="mLodgingDetail"
	 * @uml.associationEnd  
	 */
	private lodgingDetail mLodgingDetail;
	
	/**
	 * @author  Gwak seok jong
	 */
	public class lodgingIntro implements intro{
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
		 * @uml.property  name="roomCount"
		 */
		private String roomCount;
		/**
		 * @uml.property  name="subFacility"
		 */
		private subFacility subFacility;
		/**
		 * @uml.property  name="parking"
		 */
		private String parking;
		/**
		 * @uml.property  name="reservation"
		 */
		private String reservation;
		/**
		 * @uml.property  name="resHomepage"
		 */
		private String resHomepage;
		
		public lodgingIntro(
				String _infoCenter, String _scale, String _accomCount,
				String _roomCount, subFacility _subFacility,
				String _parking, String _reservation, String _resHomepage){
			infoCenter = _infoCenter;
			scale = _scale;
			accomCount = _accomCount;
			roomCount = _roomCount;
			subFacility = _subFacility;
			parking = _parking;
			reservation = _reservation;
			resHomepage = _resHomepage;
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
		 * @uml.property  name="roomCount"
		 */
		public String getRoomCount(){
			return roomCount;
		}
		
		/**
		 * @return
		 * @uml.property  name="subFacility"
		 */
		public subFacility getSubFacility(){
			return subFacility;
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
		 * @uml.property  name="resHomepage"
		 */
		public String getResHomepage(){
			return resHomepage;
		}

		public void print() {
			// TODO Auto-generated method stub
			System.out.println(infoCenter + " " + scale + " " + accomCount + " " + roomCount 
					+  " " + reservation + " " + parking + " " + resHomepage);
			subFacility.print();
		}
	}
	
	/**
	 * @uml.dependency  supplier="org.test.data.infoList"
	 */
	public class lodgingDetail implements detail, intro{
		private ArrayList<roomIntroList> roomIntroList;
		/**
		 * @uml.property  name="subImageList"
		 */
		private ArrayList<imageList> subImageList;
		
		public lodgingDetail(ArrayList<roomIntroList> _roomIntroList, 
				ArrayList<imageList> _subImageList){
			setRoomIntroList(_roomIntroList);
			setSubImageList(_subImageList);
		}

		/**
		 * @return
		 * @uml.property  name="introList"
		 */
		public ArrayList<roomIntroList> getRoomIntroList() {
			return roomIntroList;
		}

		/**
		 * @param _roomIntroList
		 * @uml.property  name="roomIntroList"
		 */
		public void setRoomIntroList(ArrayList<roomIntroList> _roomIntroList) {
			this.roomIntroList = _roomIntroList;
		}

		/**
		 * @return
		 * @uml.property  name="subImageList"
		 */
		public ArrayList<imageList> getSubImageList() {
			return subImageList;
		}
		
		/**
		 * @param _subImageList
		 * @uml.property  name="subImageList"
		 */
		public void setSubImageList(ArrayList<imageList> _subImageList) {
			this.subImageList = _subImageList;
		}

		public void print() {
			// TODO Auto-generated method stub
			if(roomIntroList != null)
				for(roomIntroList roomIntro : roomIntroList){
					roomIntro.print();
				}
			if(subImageList != null)
				for(imageList subImage : subImageList){
					subImage.print();
				}
		}
		
	}
	
	public lodgingData(
			String _infoCenter, String _scale, String _accomCount,
			String _roomCount, subFacility _subFacility,
			String _parking, String _reservation, String _resHomepage, 
			ArrayList<roomIntroList> _roomIntrointroList, 
			ArrayList<imageList> _subImageList){
		setmLodgingIntro(new lodgingIntro(
				_infoCenter, _scale, _accomCount,
				_roomCount, _subFacility, _parking,
				_reservation, _resHomepage));
		setmLodgingDetail(new lodgingDetail(_roomIntrointroList, _subImageList));
	}

	/**
	 * @param mLeportIntro
	 * @uml.property  name="mLeportIntro"
	 */
	public void setmLodgingIntro(lodgingIntro mLodgingIntro) {
		this.mLodgingIntro = mLodgingIntro;
	}

	/**
	 * @param mLeportDetail
	 * @uml.property  name="mLeportDetail"
	 */
	public void setmLodgingDetail(lodgingDetail mLodgingDetail) {
		this.mLodgingDetail = mLodgingDetail;
	}
	
	/**
	 * @param leportIntro
	 * @uml.property  name="leportIntro"
	 */
	public lodgingIntro getLodgingIntro(){
		return mLodgingIntro;
	}
	
	/**
	 * @param leportDetail
	 * @uml.property  name="leportDetail"
	 */
	public lodgingDetail getLodgingDetail(){
		return mLodgingDetail;
	}
	
	public void print(){
		if(mLodgingIntro != null) mLodgingIntro.print();
		if(mLodgingDetail != null) mLodgingDetail.print();
	}
}
