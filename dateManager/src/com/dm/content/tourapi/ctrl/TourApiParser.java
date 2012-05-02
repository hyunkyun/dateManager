package com.dm.content.tourapi.ctrl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Handler;
import android.util.Log;

import com.dm.content.tourapi.data.address;
import com.dm.content.tourapi.data.area;
import com.dm.content.tourapi.data.areaCode;
import com.dm.content.tourapi.data.category;
import com.dm.content.tourapi.data.chopHouseData;
import com.dm.content.tourapi.data.codeData;
import com.dm.content.tourapi.data.courseData;
import com.dm.content.tourapi.data.cultureData;
import com.dm.content.tourapi.data.date;
import com.dm.content.tourapi.data.detailData;
import com.dm.content.tourapi.data.eventData;
import com.dm.content.tourapi.data.exData;
import com.dm.content.tourapi.data.gpsLocation;
import com.dm.content.tourapi.data.imageList;
import com.dm.content.tourapi.data.infoList;
import com.dm.content.tourapi.data.leportData;
import com.dm.content.tourapi.data.lodgingData;
import com.dm.content.tourapi.data.relateGuide;
import com.dm.content.tourapi.data.roomIntroList;
import com.dm.content.tourapi.data.shoppingData;
import com.dm.content.tourapi.data.sightData;
import com.dm.content.tourapi.data.subFacility;
import com.dm.content.tourapi.data.unionData;
import com.dm.content.tourapi.data.xmlData;

/**
 * @author  Gwak seok jong
 */
public class TourApiParser extends xmlParser implements Runnable{
	private Handler mHandler;
	/**
	 * @uml.property  name="tourApi"
	 * @uml.associationEnd  
	 */
	private TourApi tourApi;
	private String url;
	private ArrayList<xmlData> mXmlDataList;
	private String resXml;
	private int type;
	private int totalCnt = 0;
	private XmlPullParser parser;

	/**
	 * @author     Gwak seok jong
	 */
	public enum TAGNUM{
		NULL,

		contentId, type, title, tel, map, 
		area, address, category, date,
		zipCode, detail, cat1, cat2, cat3,
		/* Parse 1. list(area) field -
		 * contentId, type, title, tel, map,
		 * area, address, category, date
		 * 
		 * 		- address field
		 * 		zipCode, address, detail
		 * 
		 * Parse 1. list(location) field -
		 * contentId, title, map, category
		 */

		modifiedTime, mapImage, firstImage, homepage, manager,
		overview, transGuide, relateGuide, imageList, videoURL,
		/* Parse 2. default field - 
		 * title, modifiedTime, mapImage, firstImage, address,
		 * map, homepage, manager, tel, overview
		 * transGuide, relateGuide, imageList, videoURL
		 */

		heritage1, heritage2, heritage3, infoCenter, openDate,
		restDate, expGuide, useSeason, useTime, checkCreditCard,
		parking, checkTextBook, 
		/* Parse 2. exData(intro) field - 
		 * heritage1, heritage2, heritage3, infoCenter, openDate,
		 * restDate, expGuide, useSeason, useTime, checkCreditCard,
		 * parking, checkTextBook
		 */

		introList1, introList2, useList1, useList2,
		/* Parse 2. exData(detail - sights) field -
		 * introList1, introList2, useList1, useList2
		 */

		scale, accomCount, openPeriod, useFee, reservation,
		restRoom, openTime, treatMenu,
		/* Parse 2. exData(intro - leports) field -
		 * infoCenter, scale, accomCount, restDate, openPeriod,
		 * useTime, useFee, reservation, parking, restRoom
		 * 
		 * Parse 2. exData(intro - culture) field -
		 * infoCenter, scale, accomCount, useFee, useTime,
		 * restDate, parking, checkCreditCard, checkTextBook
		 * 
		 * Parse 2. exData(intro - chopHouse) field -
		 * infoCenter, scale, accomCount, openTime, restDate,
		 * treatMenu, checkCreditCard, parking, reservation
		 */

		subFacility, roomCount, resHomepage, roomIntroList, subImageList,
		/* Parse 2. exData(intro - lodging) field -
		 * infoCenter, scale, accomCount, roomCount, subFacility,
		 * parking, reservation, homepage
		 */

		saleItem, fairday, curltureCenter, shoppingList,
		/* Parse 2. exData(intro - shopping) field -
		 * infoCenter, scale, saleItem, fairday, openTime,
		 * restDate, curltureCenter, checkCreditCard, parking, restRoom
		 */

		schedule, theme, recommendCourse, courseInfo,
		/* Parse 2. exData(intro - course) field -
		 * schedule, theme, recommendCourse, courseInfo
		 */

		heritage, standing, sponsor, callNumber, arrange,
		closeDate, uncertain, playTime, eventPlace, spendTime,
		ageRange, bookingPlace, bookingInfo, subEvent,
		/* Parse 2. exDate(intro - events)
		 * heritage, standing, sponsor, callNumber, arrange,
		 * tel, openDate, closeDate, unsertain, playTime,
		 * eventPlace, homepage, spendTime, ageRange, bookingPlace,
		 * bookingInfo, subEvent
		 */

		introList, useList, facilList,
		/* Parse 2. exData(detail - leports, culture, chopHouse) field -
		 * introList, useList, facilList
		 */

		shopping,
		/* Parse 2. exData(detail - shopping) field -
		 * facilList, shoppingList
		 */

		subList,
		/* Parse 2. exData(detail - events) field -
		 * introList, useList, subList
		 */

		baseCount, maxCount, offSeasonFee1, offSeasonFee2, peakSeasonFee1,
		peakSeasonFee2, bathFacility, bath, homeTheater, aircondition,
		tv,	pc, internet, refrigerator, table,
		hairdryer,
		/* Parse 2. exData(detail - lodging) field -
		 * roomIntroList (	title, scale, roomCount, baseCount, maxCount,
		 * 					offSeasonFee1, offSeasonFee2, peakSeasonFee1, peakSeasonFee2, bathFacility,
		 * 					bath, homeTheater, aircondition, tv, pc,
		 * 					internet, refrigerator, table, hairdryer)
		 * subImageList
		 */

		/* Parse 5. code field -
		 * area, sigungu, dong
		 */
		;
	}

	public TourApiParser(Handler handler, String key) {
		mHandler = handler;

		tourApi = new TourApi(key);
		try{
			XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
			parser = parserCreator.newPullParser();
		} catch(Exception e){
			Log.e("TourApiXmlParser init", e.getMessage());
		}
	}

	public void setUrl(String[] quarryStr){
		url = tourApi.getUrl(quarryStr);
		type = Integer.parseInt(quarryStr[0]);
	}

	@Override
	public XmlPullParser getXmlParser(String url, String type){
		try{
			resXml = tourApi.getXmlTourAPI(url); // save xml file
			if(!resXml.equals("")){
				//XmlResourceParser parser = act.getResources().getXml(R.xml.res);
				InputStream is = new ByteArrayInputStream(resXml.getBytes());

				parser.setInput(is,null);			
				//System.out.println("resXml : " + resXml);
				return parser;
			}
			else{
				throw new Exception("internet access fail!");
			}
		} catch(Exception e){
			Log.e("TourApiXmlParser", e.getMessage());
			return null;
		}
	}

	public void strtParsing() throws Exception{
		parser = getXmlParser(url, "utf-8");

		if(parser == null){
			Log.e("TourApiXmlParser","Parser is null");
			throw new Exception("Parser is null");
		} else{
			try{
				//parser.next();
				mXmlDataList = new ArrayList<xmlData>();

				while(parser.next() != XmlPullParser.START_TAG){;}
				//Log.d("TourApiXmlParser", parser.getName() + " djshf");
				switch(type){
				case 1:
					//Log.d("pars1", "hihi");
					parseType_1(parser);
					break;
				case 2:
					parseType_2(parser);
					Log.d("pars2", "hihi");
					break;
				case 3:
					parseType_3(parser);
					Log.d("pars3", "hihi");
					break;
				case 4:
					parseType_4(parser);
					Log.d("pars4", "hihi");
					break;
				case 5:
					parseType_5(parser);
					Log.d("pars5", "hihi");
					break;
				default:
					Log.e("TourApiParser", "Wrong type. (quarryStr[0])");
					break;
				}
			} catch (Exception e) {
				Log.e("TourApiParser", e.getMessage());
			}
		} 
	}

	private void parseType_1(XmlPullParser parser){
		final int area = 1, location = 2;
		String tag = parser.getName();
		if(tag.equals("locationResponse"))	type = location; 
		else if(tag.equals("areaResponse"))	type = area;

		try{
			int evtType;
			TAGNUM tagId = TAGNUM.NULL;
			int totalCnt = 0;//, i = 0;

			int parse_contentId = 0, parse_type = 0;
			String parse_title = null, parse_tel = null;
			gpsLocation parse_map = null;
			area parse_area = null;
			address parse_address = null;
			category parse_category = null;
			date parse_date = null;

			/* totalCnt setting start */
			while(!(parser.next() == XmlPullParser.START_TAG 
					&& parser.getName().trim().equals("totalCount"))){;}

			if(parser.next() == XmlPullParser.TEXT){
				totalCnt = Integer.valueOf(parser.getText().trim());
				this.totalCnt = totalCnt;
			}
			else{
				Log.e("parseType_1", "invalid xml file");
				return;
			}

			while(!(parser.next() == XmlPullParser.END_TAG 
					&& parser.getName().trim().equals("totalCount"))){;}
			/* totalCnt setting end */
			
			parser.next();
			evtType = parser.getEventType();

			while(evtType != XmlPullParser.END_DOCUMENT){
				switch(evtType){
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(tagId != TAGNUM.NULL) break;
					else{
						if(tag.equals("contentId"))	tagId = TAGNUM.contentId;
						else if(tag.equals("type"))	tagId = TAGNUM.type;
						else if(tag.equals("title"))tagId = TAGNUM.title;
						else if(tag.equals("tel"))	tagId = TAGNUM.tel;
						else if(tag.equals("map"))	tagId = TAGNUM.map;
						else if(tag.equals("area"))	tagId = TAGNUM.area;
						else if(tag.equals("address")){
							String zipcode = "", address = "", detail = "";

							while(!(evtType == XmlPullParser.END_TAG && tag.equals("address"))){
								switch(evtType){
								case XmlPullParser.START_TAG:
									if(tag.equals("zipCode"))		tagId = TAGNUM.zipCode;
									else if(tag.equals("address"))	tagId = TAGNUM.address;
									else if(tag.equals("detail"))	tagId = TAGNUM.detail;
									break;
								case XmlPullParser.END_TAG:
									if(tag.equals("zipCode") || tag.equals("address") || tag.equals("detail"))
										tagId = TAGNUM.NULL;
									break;
								case XmlPullParser.TEXT:
									switch(tagId){
									case zipCode:
										zipcode = parser.getText().trim();
										break;
									case address:
										address = parser.getText().trim();
										break;
									case detail:
										detail = parser.getText().trim();
										break;
									}
									break;
								}
								evtType = parser.next();
								tag = parser.getName();
							}
							parse_address = new address(address, zipcode, detail);
							//System.out.println("address done");
							continue;
						}
						else if(tag.equals("category")){
							String cat1 = "", cat2 = "", cat3 = "";

							while(!(evtType == XmlPullParser.END_TAG && tag.equals("category"))){
								switch(evtType){
								case XmlPullParser.START_TAG:
									if(tag.equals("cat1"))			tagId = TAGNUM.cat1;
									else if(tag.equals("cat2"))	tagId = TAGNUM.cat2;
									else if(tag.equals("cat3"))	tagId = TAGNUM.cat3;
									break;
								case XmlPullParser.END_TAG:
									if(tag.equals("cat1") || tag.equals("cat2") || tag.equals("cat3"))
										tagId = TAGNUM.NULL;
									break;
								case XmlPullParser.TEXT:
									switch(tagId){
									case cat1:
										cat1 = parser.getText().trim();
										break;
									case cat2:
										cat2 = parser.getText().trim();
										break;
									case cat3:
										cat3 = parser.getText().trim();
										break;
									}
									break;
								}
								evtType = parser.next();
								tag = parser.getName();
							}
							parse_category = new category(cat1, cat2, cat3);
							//System.out.println("address done");
							continue;
						}
						else if(tag.equals("date"))	tagId = TAGNUM.date;
					}
					break;
				case XmlPullParser.END_TAG:
					if(		tag.equals("contentId")|| tag.equals("type")		|| tag.equals("title") ||
							tag.equals("tel")		|| tag.equals("map")		|| tag.equals("area")	||
							tag.equals("address")	|| tag.equals("category") 	|| tag.equals("date"))
						tagId = TAGNUM.NULL;
					else if(tag.equals("list")){
						xmlData parse_data = new unionData(parse_contentId, parse_type, parse_title, parse_tel,
								parse_map, parse_area, parse_address, parse_category, parse_date);
						mXmlDataList.add(parse_data);

						mXmlDataList.get(0).print();

						//unionData test = (unionData) mXmlDataList.get(i).getObject();

							//Log.d("testttt", test.getTitle());
						//i++;
					}
					break;
				case XmlPullParser.TEXT:
					switch(tagId){
					case contentId:
						parse_contentId = Integer.valueOf(parser.getText().trim());
						break;
					case type:
						parse_type = Integer.valueOf(parser.getText().trim());
						break;
					case title:
						parse_title = parser.getText().trim();
						break;
					case tel:
						parse_tel = parser.getText().trim();
						break;
					case map:
						double mapX, mapY;

						mapX = Double.valueOf(parser.getText()).doubleValue();

						parser.next();
						parser.next();
						parser.next();
						mapY = Double.valueOf(parser.getText()).doubleValue();

						parse_map = new gpsLocation(mapX, mapY);
						////System.out.println("mapX : " + mapX + "mapY : " + mapY + " map done");
						break;
					case area:
						String parse_code1, parse_code2, parse_code3;

						parse_code1 = parser.getText().trim();

						parser.next();
						parser.next();

						if(!(parser.next() == XmlPullParser.TEXT)) parse_code2 = "";
						else parse_code2 = parser.getText().trim();

						parser.next();
						parser.next();

						if(!(parser.next() == XmlPullParser.TEXT)) parse_code3 = "";
						else parse_code3 = parser.getText().trim();

						parse_area = new area(parse_code1, parse_code2, parse_code3);
						////System.out.println("area done");
						break;
					case date:
						String parse_creat, parse_mod;

						parse_creat = parser.getText().trim();

						parser.next();
						parser.next();
						if(!(parser.next() == XmlPullParser.TEXT)) parse_mod = "";
						else parse_mod = parser.getText().trim();

						parse_date = new date(parse_creat, parse_mod);
						////System.out.println("date done");
						break;
					}
					break;
				}
				evtType = parser.next();
				tag = parser.getName();
			}

			//if(totalCnt != i) Log.e("parseType_1", "totalCnt is " + totalCnt + "but ArrayList cnt is " + i);
		} catch (Exception e) {
			Log.e("TourApiParser", e.getMessage() + "parseType_1 exception");
		}
	}

	private void parseType_2(XmlPullParser parser){
		int catType = 0;
		final int sight = 12,
				leports = 28,
				culture = 14,
				lodging = 32,
				chopHouse = 39,
				shopping = 38,
				course = 25,
				events = 15;
		String tag = parser.getName();

		if(tag.equals("sightsResponse"))		 catType = sight;
		else if(tag.equals("leportsResponse"))	 catType = leports;
		else if(tag.equals("cultureResponse"))	 catType = culture;
		else if(tag.equals("lodgingResponse"))	 catType = lodging;
		else if(tag.equals("chopHouseResponse"))catType = chopHouse;
		else if(tag.equals("shoppingResponse")) catType = shopping;
		else if(tag.equals("courseResponse"))	 catType = course;
		else if(tag.equals("eventsResponse"))	 catType = events;

		try{
			int evtType = parser.getEventType();
			TAGNUM tagId = TAGNUM.NULL;
			String parse_title = null, parse_modified = null, parse_mapImg = null,
					parse_firstImg = null, parse_homepage = null, parse_manager = null,
					parse_tel = null, parse_overview = null, parse_trans = null, parse_videoURL = null;
			ArrayList<imageList> parse_imageList = new ArrayList<imageList>();
			relateGuide parse_relate = null;
			address parse_address = null;
			gpsLocation parse_map = null;
			exData parse_exData = null;

			/* 기본 정보 파싱 시작 */
			while(!(evtType == XmlPullParser.START_TAG && tag.equals("intro"))){
				switch(evtType){
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(tag.equals("title"))				tagId = TAGNUM.title;
					else if(tag.equals("modifiedTime"))	tagId = TAGNUM.modifiedTime;
					else if(tag.equals("mapImage"))		tagId = TAGNUM.mapImage;
					else if(tag.equals("firstImage"))		tagId = TAGNUM.firstImage;
					else if(tag.equals("address")){
						String zipcode = "", address = "", detail = "";

						while(!(evtType == XmlPullParser.END_TAG && tag.equals("address"))){
							switch(evtType){
							case XmlPullParser.START_TAG:
								if(tag.equals("zipCode"))		tagId = TAGNUM.zipCode;
								else if(tag.equals("address"))	tagId = TAGNUM.address;
								else if(tag.equals("detail"))	tagId = TAGNUM.detail;
								break;
							case XmlPullParser.END_TAG:
								if(tag.equals("zipCode") || tag.equals("address") || tag.equals("detail"))
									tagId = TAGNUM.NULL;
								break;
							case XmlPullParser.TEXT:
								switch(tagId){
								case zipCode:
									zipcode = parser.getText().trim();
									break;
								case address:
									address = parser.getText().trim();
									break;
								case detail:
									detail = parser.getText().trim();
									break;
								}
								break;
							}
							evtType = parser.next();
							tag = parser.getName();
						}
						parse_address = new address(address, zipcode, detail);
						//System.out.println("address done");
						continue;
					}
					else if(tag.equals("map"))				tagId = TAGNUM.map;
					else if(tag.equals("homepage"))		tagId = TAGNUM.homepage;
					else if(tag.equals("manager"))			tagId = TAGNUM.manager;
					else if(tag.equals("tel"))				tagId = TAGNUM.tel;
					else if(tag.equals("overview"))		tagId = TAGNUM.overview;
					else if(tag.equals("transGuide"))		tagId = TAGNUM.transGuide;
					else if(tag.equals("relateGuide"))		tagId = TAGNUM.relateGuide;
					else if(tag.equals("imageList"))		tagId = TAGNUM.imageList;
					else if(tag.equals("videoURL"))		tagId = TAGNUM.videoURL;
					break;
				case XmlPullParser.END_TAG:
					if(		tag.equals("title")		|| tag.equals("modifiedTime")	|| tag.equals("mapImage")	 || 
							tag.equals("firstImage")	|| tag.equals("address")		|| tag.equals("map")		 ||
							tag.equals("homepage")		|| tag.equals("manager")		|| tag.equals("tel")		 ||
							tag.equals("overview")		|| tag.equals("transGuide")	|| tag.equals("relateGuide")||
							tag.equals("imageList")	|| tag.equals("videoURL"))	
						tagId = TAGNUM.NULL;
					break;
				case XmlPullParser.TEXT:
					switch(tagId){
					case title:
						parse_title = parser.getText().trim();
						//System.out.println("title done");
						break;
					case modifiedTime:
						parse_modified = parser.getText().trim();
						//System.out.println("modtime done");
						break;
					case mapImage:
						parse_mapImg = parser.getText().trim();
						//System.out.println("mapImage done");
						break;
					case firstImage:
						parse_firstImg = parser.getText().trim();
						//System.out.println("firstImage done");
						break;
					case map:
						double mapX, mapY;

						mapX = Double.valueOf(parser.getText()).doubleValue();

						parser.next();
						parser.next();
						parser.next();
						mapY = Double.valueOf(parser.getText()).doubleValue();

						parse_map = new gpsLocation(mapX, mapY);
						//System.out.println("map done");
						break;
					case homepage:
						parse_homepage = parser.getText().trim();
						//System.out.println("homepage done");
						break;
					case manager:
						parse_manager = parser.getText().trim();
						//System.out.println("manager done");
						break;
					case tel:
						parse_tel = parser.getText().trim();
						//System.out.println("tel done");
						break;
					case overview:
						parse_overview = parser.getText().trim();
						//System.out.println("overview done");
						break;
					case transGuide:
						parse_trans = parser.getText().trim();
						//System.out.println("transGuide done");
						break;
					case relateGuide:
						String parse_tourGuide;
						ArrayList<infoList> parse_infoList = new ArrayList<infoList>();

						parse_tourGuide = parser.getText().trim();

						int infoEnd = parser.next();
						String infoTag = parser.getName();
						int infoTagId = 0;
						String name = null, contents = null;
						while(!(infoEnd == XmlPullParser.END_TAG && infoTag.equals("infoList"))){
							switch(infoEnd){
							case XmlPullParser.START_TAG:
								if(infoTag.equals("name"))				infoTagId = 1;
								else if(infoTag.equals("contents"))	infoTagId = 2;
								break;
							case XmlPullParser.TEXT:
								if(infoTagId == 1)		name = parser.getText().trim();
								else if(infoTagId == 2){
									contents = parser.getText().trim();
									parse_infoList.add(new infoList(name, contents));
								}
								break;
							}
							infoEnd = parser.next();
							infoTag = parser.getName();
						}

						parse_relate = new relateGuide(parse_tourGuide, parse_infoList);
						//System.out.println("relateGuide done");
						break;
					case imageList:
						String parse_imgURL = null, parse_imgName = null;
						int parserNext;
						parse_imgURL = parser.getText();
						parserNext = parser.next();
						parserNext = parser.next();
						parserNext = parser.next();
						if((parserNext == XmlPullParser.TEXT)) parse_imgName = parser.getText();
						parse_imageList.add(new imageList(parse_imgURL, parse_imgName));
						//System.out.println("imageList done");
						break;
					case videoURL:
						parse_videoURL = parser.getText().trim();
						//System.out.println("videoURL done");
						break;
					}
					break;
				}
				evtType = parser.next();
				tag = parser.getName();
			}
			/* 기본 정보 파싱 완료 */
			//System.out.println("end basic info");
			/* 추가 정보 파싱 시작 */
			switch(catType){
			case sight:{
				//System.out.println("start sight exData parse");
				boolean parse_heri1 = false, parse_heri2 = false, parse_heri3 = false, parse_checkTextBook = false; 
				String parse_infoCenter = null, parse_openDate = null, parse_restDate = null,
						parse_expGuide = null, parse_useSeason = null, parse_useTime = null,
						parse_checkCreditCard = null, parse_parking = null;
				ArrayList<infoList> parse_introList1 = new ArrayList<infoList>(), 
						parse_introList2 = new ArrayList<infoList>(),
						parse_useList1 = new ArrayList<infoList>(),
						parse_useList2 = new ArrayList<infoList>();
						while(evtType != XmlPullParser.END_DOCUMENT){
							switch(evtType){
							case XmlPullParser.START_DOCUMENT:
								break;
							case XmlPullParser.START_TAG:
								if(tag.equals("heritage1")) 		tagId = TAGNUM.heritage1;
								else if(tag.equals("heritage2")) 	tagId = TAGNUM.heritage2;
								else if(tag.equals("heritage3"))	tagId = TAGNUM.heritage3;
								else if(tag.equals("infoCenter"))	tagId = TAGNUM.infoCenter;
								else if(tag.equals("openDate"))	tagId = TAGNUM.openDate;
								else if(tag.equals("restDate"))	tagId = TAGNUM.restDate;
								else if(tag.equals("expGuide"))	tagId = TAGNUM.expGuide;
								else if(tag.equals("useSeason"))	tagId = TAGNUM.useSeason;
								else if(tag.equals("useTime"))		tagId = TAGNUM.useTime;
								else if(tag.equals("checkCreditCard")) tagId = TAGNUM.checkCreditCard;
								else if(tag.equals("parking"))		tagId = TAGNUM.parking;
								else if(tag.equals("checkTextBook"))   tagId = TAGNUM.checkTextBook;
								else if(tag.equals("introList1"))	tagId = TAGNUM.introList1;
								else if(tag.equals("introList2"))	tagId = TAGNUM.introList2;
								else if(tag.equals("useList1")) 	tagId = TAGNUM.useList1;
								else if(tag.equals("useList2"))	tagId = TAGNUM.useList2;
								break;
							case XmlPullParser.END_TAG:
								if(tag.equals("heritage1") || tag.equals("heritage2") || tag.equals("heritage3") || 
										tag.equals("infoCenter") || tag.equals("openDate") || tag.equals("restDate") ||
										tag.equals("expGuide") || tag.equals("useSeason") || tag.equals("useTime") ||
										tag.equals("checkCreditCard") || tag.equals("parking") || 
										tag.equals("checkTextBook") ||	tag.equals("introList1") || tag.equals("introList2") ||
										tag.equals("useList1") || tag.equals("useList2"))
									tagId = TAGNUM.NULL;
								break;
							case XmlPullParser.TEXT:
								switch(tagId){
								case heritage1:
									parse_heri1 = parser.getText().trim().equals("1");
									//System.out.println("heri1 done");
									break;
								case heritage2:
									parse_heri2 = parser.getText().trim().equals("1");
									//System.out.println("heri2 done");
									break;
								case heritage3:
									parse_heri3 = parser.getText().trim().equals("1");
									//System.out.println("heri3 done");
									break;
								case infoCenter:
									parse_infoCenter = parser.getText().trim();
									//System.out.println("infoCenter done");
									break;
								case openDate:
									parse_openDate = parser.getText().trim();
									//System.out.println("openDate done");
									break;
								case restDate:
									parse_restDate = parser.getText().trim();
									//System.out.println("restDate done");
									break;
								case expGuide:
									parse_expGuide = parser.getText().trim();
									//System.out.println("expGuide done");
									break;
								case useSeason:
									parse_useSeason = parser.getText().trim();
									//System.out.println("useSeason done");
									break;
								case useTime:
									parse_useTime = parser.getText().trim();
									//System.out.println("useTime done");
									break;
								case checkCreditCard:
									parse_checkCreditCard = parser.getText().trim();
									//System.out.println("checkCreditCard done");
									break;
								case parking:
									parse_parking = parser.getText().trim();
									//System.out.println("parking done");
									break;
								case checkTextBook:
									parse_checkTextBook = parser.getText().trim().equals("1");
									//System.out.println("checkTextBook done");
									break;
								case introList1:{
									String parse_introListName = null, parse_introListContents = null;
									parse_introListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_introListContents = parser.getText();
									parse_introList1.add(new infoList(parse_introListName, parse_introListContents));
									//System.out.println("introList1 done");
									break;
								}
								case introList2:{
									String parse_introListName = null, parse_introListContents = null;
									parse_introListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_introListContents = parser.getText();
									parse_introList2.add(new infoList(parse_introListName, parse_introListContents));
									//System.out.println("introList2 done");
									break;
								}
								case useList1:{
									String parse_useListName = null, parse_useListContents = null;
									parse_useListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_useListContents = parser.getText();
									parse_useList1.add(new infoList(parse_useListName, parse_useListContents));
									//System.out.println("useList1 done");
									break;
								}
								case useList2:{
									String parse_useListName = null, parse_useListContents = null;
									parse_useListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_useListContents = parser.getText();
									parse_useList2.add(new infoList(parse_useListName, parse_useListContents));
									//System.out.println("useList2 done");
									break;
								}
								}
								break;
							}
							evtType = parser.next();
							tag = parser.getName().trim();
						}
						parse_exData = new sightData(parse_heri1, parse_heri2, parse_heri3,
								parse_infoCenter, parse_openDate, parse_restDate,
								parse_expGuide, parse_useSeason, parse_checkCreditCard, parse_useTime,
								parse_parking, parse_checkTextBook,
								parse_introList1, parse_introList2, parse_useList1, parse_useList2);
						break;
			}
			case leports:{
				String parse_infoCenter = null, parse_scale = null, parse_accomCount = null,
						parse_restDate = null, parse_openPeriod = null, parse_useTime = null,
						parse_useFee = null, parse_reservation = null, parse_parking = null,
						parse_restRoom = null;
				ArrayList<infoList> parse_introList = new ArrayList<infoList>(), 
						parse_useList = new ArrayList<infoList>(),
						parse_facilList = new ArrayList<infoList>();
						while(evtType != XmlPullParser.END_DOCUMENT){
							switch(evtType){
							case XmlPullParser.START_DOCUMENT:
								break;
							case XmlPullParser.START_TAG:
								if(tag.equals("infoCenter"))		tagId = TAGNUM.infoCenter;
								else if(tag.equals("scale"))		tagId = TAGNUM.scale;
								else if(tag.equals("accomCount"))	tagId = TAGNUM.accomCount;
								else if(tag.equals("restDate"))	tagId = TAGNUM.restDate;
								else if(tag.equals("openPeriod"))	tagId = TAGNUM.openPeriod;
								else if(tag.equals("useTime"))		tagId = TAGNUM.useTime;
								else if(tag.equals("useFee"))		tagId = TAGNUM.useFee;
								else if(tag.equals("reservation"))	tagId = TAGNUM.reservation;
								else if(tag.equals("parking"))		tagId = TAGNUM.parking;
								else if(tag.equals("restRoom"))	tagId = TAGNUM.reservation;
								else if(tag.equals("introList"))	tagId = TAGNUM.introList;
								else if(tag.equals("useList"))		tagId = TAGNUM.useList;
								else if(tag.equals("facilList"))	tagId = TAGNUM.facilList;
								break;
							case XmlPullParser.END_TAG:
								if(		tag.equals("infoCenter") || tag.equals("scale") || tag.equals("accomCount") ||
										tag.equals("restDate") || tag.equals("openPeriod") || tag.equals("useTime") ||
										tag.equals("useFee") || tag.equals("reservation") || 
										tag.equals("parking") ||	tag.equals("restRoom") || tag.equals("introList") ||
										tag.equals("useList") || tag.equals("facilList"))
									tagId = TAGNUM.NULL;
								break;
							case XmlPullParser.TEXT:
								switch(tagId){
								case infoCenter:
									parse_infoCenter = parser.getText().trim();
									break;
								case scale:
									parse_scale = parser.getText().trim();
									break;
								case accomCount:
									parse_accomCount = parser.getText().trim();
									break;
								case restDate:
									parse_restDate = parser.getText().trim();
									break;
								case openPeriod:
									parse_openPeriod = parser.getText().trim();
									break;
								case useTime:
									parse_useTime = parser.getText().trim();
									break;
								case reservation:
									parse_reservation = parser.getText().trim();
									break;
								case parking:
									parse_parking = parser.getText().trim();
									break;
								case restRoom:
									parse_restRoom = parser.getText().trim();
									break;
								case introList:{
									String parse_introListName = null, parse_introListContents = null;
									parse_introListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_introListContents = parser.getText();
									parse_introList.add(new infoList(parse_introListName, parse_introListContents));
									//System.out.println("introList done");
									break;
								}
								case useList:{
									String parse_useListName = null, parse_useListContents = null;
									parse_useListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_useListContents = parser.getText();
									parse_useList.add(new infoList(parse_useListName, parse_useListContents));
									//System.out.println("useList done");
									break;
								}
								case facilList:{
									String parse_facilListName = null, parse_facilListContents = null;
									parse_facilListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_facilListContents = parser.getText();
									parse_facilList.add(new infoList(parse_facilListName, parse_facilListContents));
									//System.out.println("facilList done");
									break;
								}
								}
								break;
							}
							evtType = parser.next();
							tag = parser.getName().trim();
						}
						parse_exData = new leportData(
								parse_infoCenter, parse_scale, parse_accomCount,
								parse_restDate, parse_openPeriod, parse_useTime, parse_useFee,
								parse_reservation, parse_parking, parse_restRoom,
								parse_introList, parse_useList, parse_facilList);
						break;
			}
			case culture:{
				String parse_infoCenter = null, parse_scale = null, parse_accomCount = null,
						parse_useFee = null, parse_useTime = null, parse_restDate = null,
						parse_parking = null, parse_checkCreditCard = null;
				boolean parse_checkTextBook = false;
				ArrayList<infoList> parse_introList = new ArrayList<infoList>(), 
						parse_useList = new ArrayList<infoList>(),
						parse_facilList = new ArrayList<infoList>();
						while(evtType != XmlPullParser.END_DOCUMENT){
							switch(evtType){
							case XmlPullParser.START_DOCUMENT:
								break;
							case XmlPullParser.START_TAG:
								if(tag.equals("infoCenter"))		tagId = TAGNUM.infoCenter;
								else if(tag.equals("scale"))		tagId = TAGNUM.scale;
								else if(tag.equals("accomCount"))	tagId = TAGNUM.accomCount;
								else if(tag.equals("useFee"))		tagId = TAGNUM.useFee;
								else if(tag.equals("useTime"))		tagId = TAGNUM.useTime;
								else if(tag.equals("restDate"))	tagId = TAGNUM.restDate;
								else if(tag.equals("parking"))		tagId = TAGNUM.parking;
								else if(tag.equals("checkCreditCard"))	tagId = TAGNUM.checkCreditCard;
								else if(tag.equals("checkTextBook"))	tagId = TAGNUM.checkTextBook;
								else if(tag.equals("introList"))	tagId = TAGNUM.introList;
								else if(tag.equals("useList"))		tagId = TAGNUM.useList;
								else if(tag.equals("facilList"))	tagId = TAGNUM.facilList;
								break;
							case XmlPullParser.END_TAG:
								if(		tag.equals("infoCenter") || tag.equals("scale") || tag.equals("accomCount") ||
										tag.equals("useFee") || tag.equals("useTime") || tag.equals("restDate") ||
										tag.equals("parking") || tag.equals("checkCreditCard") || 
										tag.equals("checkCreitCard") ||	tag.equals("checkTextBook") || tag.equals("introList") ||
										tag.equals("useList") || tag.equals("facilList"))
									tagId = TAGNUM.NULL;
								break;
							case XmlPullParser.TEXT:
								switch(tagId){
								case infoCenter:
									parse_infoCenter = parser.getText().trim();
									break;
								case scale:
									parse_scale = parser.getText().trim();
									break;
								case accomCount:
									parse_accomCount = parser.getText().trim();
									break;
								case useFee:
									parse_useFee = parser.getText().trim();
									break;
								case useTime:
									parse_useTime = parser.getText().trim();
									break;
								case restDate:
									parse_restDate = parser.getText().trim();
									break;
								case parking:
									parse_parking = parser.getText().trim();
									break;
								case checkCreditCard:
									parse_checkCreditCard = parser.getText().trim();
									break;
								case checkTextBook:
									parse_checkTextBook = parser.getText().trim().equals("1");
									break;
								case introList:{
									String parse_introListName = null, parse_introListContents = null;
									parse_introListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_introListContents = parser.getText();
									parse_introList.add(new infoList(parse_introListName, parse_introListContents));
									//System.out.println("introList done");
									break;
								}
								case useList:{
									String parse_useListName = null, parse_useListContents = null;
									parse_useListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_useListContents = parser.getText();
									parse_useList.add(new infoList(parse_useListName, parse_useListContents));
									//System.out.println("useList done");
									break;
								}
								case facilList:{
									String parse_facilListName = null, parse_facilListContents = null;
									parse_facilListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_facilListContents = parser.getText();
									parse_facilList.add(new infoList(parse_facilListName, parse_facilListContents));
									//System.out.println("facilList done");
									break;
								}
								}
								break;
							}
							evtType = parser.next();
							tag = parser.getName();
						}
						parse_exData = new cultureData(
								parse_infoCenter, parse_scale, parse_accomCount,
								parse_useFee, parse_useTime, parse_restDate, parse_parking,
								parse_checkCreditCard, parse_checkTextBook,
								parse_introList, parse_useList, parse_facilList);
						break;
			}
			case lodging:{
				String parse_infoCenter = null, parse_scale = null, parse_accomCount = null,
						parse_roomCount = null, parse_parking = null, parse_reservation = null,
						parse_resHomepage = null;
				subFacility parse_subFacility = null; 
				ArrayList<roomIntroList> parse_roomIntroList = new ArrayList<roomIntroList>();
				ArrayList<imageList> parse_subImageList = new ArrayList<imageList>();
				while(evtType != XmlPullParser.END_DOCUMENT){
					switch(evtType){
					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:
						if(tagId != TAGNUM.NULL) break;
						else{
							if(tag.equals("infoCenter"))		tagId = TAGNUM.infoCenter;
							else if(tag.equals("scale"))		tagId = TAGNUM.scale;
							else if(tag.equals("accomCount"))	tagId = TAGNUM.accomCount;
							else if(tag.equals("roomCount"))	tagId = TAGNUM.roomCount;
							else if(tag.equals("subFacility")){
								boolean[] subFacility = { false, false, false, false, false,
										false, false, false, false, false,
										false, false };
								int i = 0;
								String tmp;
								evtType = parser.next();
								tag = parser.getName();
								while(!(tag.equals("subFacility") && evtType == XmlPullParser.END_TAG)){
									switch(evtType){
									case XmlPullParser.TEXT:
										tmp = parser.getText().trim();
										subFacility[i++] = (tmp == "1") ? true : false;
										break;
									}
									evtType = parser.next();
									tag = parser.getName();
								}
								parse_subFacility = new subFacility(subFacility);
							}
							else if(tag.equals("parking"))		tagId = TAGNUM.parking;
							else if(tag.equals("reservation"))	tagId = TAGNUM.reservation;
							else if(tag.equals("homepage"))		tagId = TAGNUM.resHomepage;
							else if(tag.equals("roomIntroList")){
								String  parse_roomIntTitle = null, parse_roomIntScale = null, parse_roomIntRoomCnt = null,
										parse_roomIntBaseCnt = null, parse_roomIntMaxCnt = null, parse_offSeasonFee1 = null,
										parse_offSeasonFee2 = null, parse_peakSeasonFee1 = null, parse_peakSeasonFee2 = null;
								boolean[] roomIntro = {false, false, false, false, false,
										false, false, false, false, false};
								int i = -1;
								evtType = parser.next();
								tag = parser.getName();
								while(!(tag.equals("subFacility") && evtType == XmlPullParser.END_TAG)){
									switch(evtType){
									case XmlPullParser.START_TAG:
										if(tag.equals("title"))				tagId = TAGNUM.title;
										else if(tag.equals("scale"))		tagId = TAGNUM.scale;
										else if(tag.equals("roomCount"))	tagId = TAGNUM.roomCount;
										else if(tag.equals("baseCount"))	tagId = TAGNUM.baseCount;
										else if(tag.equals("maxCount"))		tagId = TAGNUM.maxCount;
										else if(tag.equals("offSeasonFee1"))tagId = TAGNUM.offSeasonFee1;
										else if(tag.equals("offSeasonFee2"))tagId = TAGNUM.offSeasonFee2;
										else if(tag.equals("peakSeasonFee1"))tagId = TAGNUM.peakSeasonFee1;
										else if(tag.equals("peakSeasonFee2"))tagId = TAGNUM.peakSeasonFee2;
										else if(tag.equals("bathFacility"))	tagId = TAGNUM.bathFacility;
										else if(tag.equals("bath"))			tagId = TAGNUM.bath;
										else if(tag.equals("homeTheater"))	tagId = TAGNUM.homeTheater;
										else if(tag.equals("aircondotion"))	tagId = TAGNUM.aircondition;
										else if(tag.equals("tv"))			tagId = TAGNUM.tv;
										else if(tag.equals("pc"))			tagId = TAGNUM.pc;
										else if(tag.equals("internet"))		tagId = TAGNUM.internet;
										else if(tag.equals("refrigerator"))	tagId = TAGNUM.refrigerator;
										else if(tag.equals("table"))		tagId = TAGNUM.table;
										else if(tag.equals("hairdryer"))	tagId = TAGNUM.hairdryer;
										break;
									case XmlPullParser.END_TAG:
										if(		tag.equals("title")			||	tag.equals("scale")			||	tag.equals("roomCount")		||
												tag.equals("baseCount")		||	tag.equals("maxCount")		||	tag.equals("offSeasonFee1")	||
												tag.equals("offSeasonFee2")	||	tag.equals("peakSeasonFee1")||tag.equals("peakSeasonFee2")	||
												tag.equals("bathFacility")	||	tag.equals("bath")			||tag.equals("homeTheater")		||
												tag.equals("aircondotion")	||	tag.equals("tv")			||tag.equals("pc")				||
												tag.equals("internet")		||	tag.equals("refrigerator")	||tag.equals("table")			||
												tag.equals("hairdryer"))
											tagId = TAGNUM.NULL;
										break;
									case XmlPullParser.TEXT:
										String tmp;
										switch(tagId){
										case title:
											parse_roomIntTitle = parser.getText().trim();
											break;
										case scale:
											parse_roomIntScale = parser.getText().trim();
											break;
										case roomCount:
											parse_roomIntRoomCnt = parser.getText().trim();
											break;
										case baseCount:
											parse_roomIntBaseCnt = parser.getText().trim();
											break;
										case maxCount:
											parse_roomIntMaxCnt = parser.getText().trim();
											break;
										case offSeasonFee1:
											parse_offSeasonFee1 = parser.getText().trim();
											break;
										case offSeasonFee2:
											parse_offSeasonFee2 = parser.getText().trim();
											break;
										case peakSeasonFee1:
											parse_peakSeasonFee1 = parser.getText().trim();
											break;
										case peakSeasonFee2:
											parse_peakSeasonFee2 = parser.getText().trim();
											break;
										case bathFacility:
											i = 0;
											break;
										case bath:
											i = 1;
											break;
										case homeTheater:
											i = 2;
											break;
										case aircondition:
											i = 3;
											break;
										case tv:
											i = 4;
											break;
										case pc:
											i = 5;
											break;
										case internet:
											i = 6;
											break;
										case refrigerator:
											i = 7;
											break;
										case table:
											i = 8;
											break;
										case hairdryer:
											i = 9;
											break;
										}
										if(i != -1){
											tmp = parser.getText().trim();
											roomIntro[i] = (tmp == "1") ? true : false;
											i = -1;
										}
										break;
									}
									evtType = parser.next();
									tag = parser.getName();
								}
								parse_roomIntroList.add(new roomIntroList(parse_roomIntTitle, parse_roomIntScale, parse_roomIntRoomCnt,
										parse_roomIntBaseCnt, parse_roomIntMaxCnt, parse_offSeasonFee1,
										parse_offSeasonFee2, parse_peakSeasonFee1, parse_peakSeasonFee2,
										roomIntro));
							}
							else if(tag.equals("subImageList"))	tagId = TAGNUM.subImageList;
						}
						break;
					case XmlPullParser.END_TAG:
						if(		tag.equals("infoCenter") || tag.equals("scale") || tag.equals("accomCount") ||
								tag.equals("roomCount") || tag.equals("subFacility") ||
								tag.equals("parking") || tag.equals("reservation") || 
								tag.equals("homepage") ||	tag.equals("roomIntroList") || tag.equals("subImageList"))
							tagId = TAGNUM.NULL;
						break;
					case XmlPullParser.TEXT:
						switch(tagId){
						case infoCenter:
							parse_infoCenter = parser.getText().trim();
							break;
						case scale:
							parse_scale = parser.getText().trim();
							break;
						case accomCount:
							parse_accomCount = parser.getText().trim();
							break;
						case roomCount:
							parse_roomCount = parser.getText().trim();
							break;
						case parking:
							parse_parking = parser.getText().trim();
							break;
						case reservation:
							parse_reservation = parser.getText().trim();
							break;
						case resHomepage:
							parse_resHomepage = parser.getText().trim();
							break;
						case subImageList:{
							String parse_imgURL = null, parse_imgName = null;
							parse_imgURL = parser.getText();
							parser.next();
							parser.next();
							if((parser.next() == XmlPullParser.TEXT)) parse_imgName = parser.getText();
							parse_subImageList.add(new imageList(parse_imgURL, parse_imgName));
							//System.out.println("introList done");
							break;
						}
						}
						break;
					}
					evtType = parser.next();
					tag = parser.getName();
				}
				parse_exData = new lodgingData(
						parse_infoCenter, parse_scale, parse_accomCount,
						parse_roomCount, parse_subFacility, parse_parking,
						parse_reservation, parse_resHomepage, parse_roomIntroList,
						parse_subImageList);

				break;
			}
			case chopHouse:{
				String parse_infoCenter = null, parse_scale = null, parse_accomCount = null,
						parse_openTime = null, parse_restDate = null, parse_treatMenu = null,
						parse_checkCreditCard = null, parse_parking = null, parse_reservation = null;
				ArrayList<imageList> parse_subImageList = new ArrayList<imageList>();
				while(evtType != XmlPullParser.END_DOCUMENT){
					switch(evtType){
					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:
						if(tag.equals("infoCenter"))		tagId = TAGNUM.infoCenter;
						else if(tag.equals("scale"))		tagId = TAGNUM.scale;
						else if(tag.equals("accomCount"))	tagId = TAGNUM.accomCount;
						else if(tag.equals("openTime"))		tagId = TAGNUM.roomCount;
						else if(tag.equals("restDate"))		tagId = TAGNUM.openTime;
						else if(tag.equals("treatMenu"))	tagId = TAGNUM.treatMenu;
						else if(tag.equals("checkCreditCard"))tagId = TAGNUM.checkCreditCard;
						else if(tag.equals("parking"))		tagId = TAGNUM.parking;
						else if(tag.equals("resevation"))	tagId = TAGNUM.reservation;
						break;
					case XmlPullParser.END_TAG:
						if(		tag.equals("infoCenter")||	tag.equals("scale")			|| tag.equals("accomCount") ||
								tag.equals("openTime")	||	tag.equals("restDate")		||
								tag.equals("treatMenu") ||	tag.equals("checkCreditCard")|| 
								tag.equals("parking")	||	tag.equals("reservation")	|| tag.equals("subImageList"))
							tagId = TAGNUM.NULL;
						break;
					case XmlPullParser.TEXT:
						switch(tagId){
						case infoCenter:
							parse_infoCenter = parser.getText().trim();
							break;
						case scale:
							parse_scale = parser.getText().trim();
							break;
						case accomCount:
							parse_accomCount = parser.getText().trim();
							break;
						case openTime:
							parse_openTime = parser.getText().trim();
							break;
						case restDate:
							parse_restDate = parser.getText().trim();
							break;
						case treatMenu:
							parse_treatMenu = parser.getText().trim();
							break;
						case checkCreditCard:
							parse_checkCreditCard = parser.getText().trim();
							break;
						case parking:
							parse_parking = parser.getText().trim();
							break;
						case reservation:
							parse_reservation = parser.getText().trim();
							break;
						case subImageList:{
							String parse_imgURL = null, parse_imgName = null;
							parse_imgURL = parser.getText();
							parser.next();
							parser.next();
							if((parser.next() == XmlPullParser.TEXT)) parse_imgName = parser.getText();
							parse_subImageList.add(new imageList(parse_imgURL, parse_imgName));
							//System.out.println("introList done");
							break;
						}
						}
						break;
					}
					evtType = parser.next();
					tag = parser.getName();
				}
				parse_exData = new chopHouseData(
						parse_infoCenter, parse_scale, parse_accomCount,
						parse_openTime, parse_restDate, parse_treatMenu,
						parse_checkCreditCard, parse_parking, parse_reservation,
						parse_subImageList);
				break;
			}
			case shopping:{
				String parse_infoCenter = null, parse_scale = null, parse_saleItem = null,
						parse_fairday = null, parse_openTime = null, parse_restDate = null,
						parse_curltureCenter = null, parse_checkCreditCard = null, parse_parking = null,
						parse_restRoom = null;
				ArrayList<infoList> parse_facilList = new ArrayList<infoList>(),
						parse_shoppingList = new ArrayList<infoList>();
						while(evtType != XmlPullParser.END_DOCUMENT){
							switch(evtType){
							case XmlPullParser.START_DOCUMENT:
								break;
							case XmlPullParser.START_TAG:
								if(tag.equals("infoCenter"))		tagId = TAGNUM.infoCenter;
								else if(tag.equals("scale"))		tagId = TAGNUM.scale;
								else if(tag.equals("saleItem"))		tagId = TAGNUM.saleItem;
								else if(tag.equals("fairday"))		tagId = TAGNUM.fairday;
								else if(tag.equals("openTime"))		tagId = TAGNUM.openTime;
								else if(tag.equals("restDate"))		tagId = TAGNUM.restDate;
								else if(tag.equals("curltureCenter"))tagId = TAGNUM.curltureCenter;
								else if(tag.equals("parking"))		tagId = TAGNUM.parking;
								else if(tag.equals("resevation"))	tagId = TAGNUM.reservation;
								else if(tag.equals("facilList"))	tagId = TAGNUM.facilList;
								else if(tag.equals("shoppingList"))	tagId = TAGNUM.shoppingList;
								break;
							case XmlPullParser.END_TAG:
								if(		tag.equals("infoCenter")||	tag.equals("scale")			|| tag.equals("saleItem") ||
										tag.equals("fairday")	||	tag.equals("openTime")		||
										tag.equals("restDate")	||	tag.equals("curltureCenter")	|| 
										tag.equals("parking")	||	tag.equals("reservation")	|| tag.equals("subImageList"))
									tagId = TAGNUM.NULL;
								break;
							case XmlPullParser.TEXT:
								switch(tagId){
								case infoCenter:
									parse_infoCenter = parser.getText().trim();
									break;
								case scale:
									parse_scale = parser.getText().trim();
									break;
								case saleItem:
									parse_saleItem = parser.getText().trim();
									break;
								case fairday:
									parse_fairday = parser.getText().trim();
									break;
								case openTime:
									parse_openTime = parser.getText().trim();
									break;
								case restDate:
									parse_restDate = parser.getText().trim();
									break;
								case curltureCenter:
									parse_curltureCenter = parser.getText().trim();
									break;
								case checkCreditCard:
									parse_checkCreditCard = parser.getText().trim();
									break;
								case parking:
									parse_parking = parser.getText().trim();
									break;
								case restRoom:
									parse_restRoom = parser.getText().trim();
									break;
								case facilList:{
									String parse_facilListName = null, parse_facilListContents = null;
									parse_facilListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_facilListContents = parser.getText();
									parse_facilList.add(new infoList(parse_facilListName, parse_facilListContents));
									//System.out.println("facilList done");
									break;
								}
								case shoppingList:{
									String parse_shoppingListName = null, parse_shoppingListContents = null;
									parse_shoppingListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_shoppingListContents = parser.getText();
									parse_shoppingList.add(new infoList(parse_shoppingListName, parse_shoppingListContents));
									//System.out.println("useList done");
									break;
								}
								}
								break;
							}
							evtType = parser.next();
							tag = parser.getName();
						}
						parse_exData = new shoppingData(
								parse_infoCenter, parse_scale, parse_saleItem,
								parse_fairday, parse_openTime, parse_restDate,
								parse_curltureCenter, parse_checkCreditCard, parse_parking,
								parse_restRoom, parse_facilList, parse_shoppingList);
						break;
			}
			case course:{
				String parse_schedule = null, parse_theme = null, parse_recommendCourse = null,
						parse_courseInfo = null;
				while(evtType != XmlPullParser.END_DOCUMENT){
					switch(evtType){
					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:
						if(tag.equals("schedule"))				tagId = TAGNUM.schedule;
						else if(tag.equals("theme"))			tagId = TAGNUM.theme;
						else if(tag.equals("recommendCourse"))	tagId = TAGNUM.recommendCourse;
						else if(tag.equals("courseInfo"))		tagId = TAGNUM.courseInfo;
						break;
					case XmlPullParser.END_TAG:
						if(		tag.equals("schedule")	||	tag.equals("theme")	||	tag.equals("recommendCourse") ||
								tag.equals("courseInfo"))
							tagId = TAGNUM.NULL;
						break;
					case XmlPullParser.TEXT:
						switch(tagId){
						case schedule:
							parse_schedule = parser.getText().trim();
							break;
						case theme:
							parse_theme = parser.getText().trim();
							break;
						case recommendCourse:
							parse_recommendCourse = parser.getText().trim();
							break;
						case courseInfo:
							parse_courseInfo = parser.getText().trim();
							break;
						}
						break;
					}
					evtType = parser.next();
					tag = parser.getName();
				}
				parse_exData = new courseData(
						parse_schedule, parse_theme, parse_recommendCourse,
						parse_courseInfo);
				break;
			}
			case events:{
				boolean parse_heritage = false, parse_standing = false, parse_uncertain = false;
				String parse_sponsor = null, parse_callNumber = null, parse_arrange = null,
						parse_resTel = null, parse_openDate = null, parse_closeDate = null,
						parse_playTime = null, parse_eventPlace = null, parse_resHomepage = null,
						parse_spendTime = null, parse_ageRange = null, parse_bookingPlace = null,
						parse_bookingInfo = null, parse_subEvent = null;
				ArrayList<infoList> parse_introList = new ArrayList<infoList>(),
						parse_useList = new ArrayList<infoList>(),
						parse_subList = new ArrayList<infoList>();
						while(evtType != XmlPullParser.END_DOCUMENT){
							switch(evtType){
							case XmlPullParser.START_DOCUMENT:
								break;
							case XmlPullParser.START_TAG:
								if(tag.equals("heritage"))			tagId = TAGNUM.heritage;
								else if(tag.equals("standing"))		tagId = TAGNUM.standing;
								else if(tag.equals("sponsor"))		tagId = TAGNUM.sponsor;
								else if(tag.equals("callNumber"))	tagId = TAGNUM.callNumber;
								else if(tag.equals("arrange"))		tagId = TAGNUM.arrange;
								else if(tag.equals("tel"))			tagId = TAGNUM.tel;
								else if(tag.equals("openDate"))		tagId = TAGNUM.openDate;
								else if(tag.equals("closeDate"))	tagId = TAGNUM.closeDate;
								else if(tag.equals("uncertain"))	tagId = TAGNUM.uncertain;
								else if(tag.equals("playTime"))		tagId = TAGNUM.playTime;
								else if(tag.equals("eventPlace"))	tagId = TAGNUM.eventPlace;
								else if(tag.equals("homepage"))		tagId = TAGNUM.homepage;
								else if(tag.equals("spendTime"))	tagId = TAGNUM.spendTime;
								else if(tag.equals("ageRange"))		tagId = TAGNUM.ageRange;
								else if(tag.equals("bookingPlace"))	tagId = TAGNUM.bookingPlace;
								else if(tag.equals("bookingInfo"))	tagId = TAGNUM.bookingInfo;
								else if(tag.equals("subEvent"))		tagId = TAGNUM.subEvent;
								else if(tag.equals("introList"))	tagId = TAGNUM.introList;
								else if(tag.equals("useList"))		tagId = TAGNUM.useList;
								else if(tag.equals("subList"))		tagId = TAGNUM.subList;
								break;
							case XmlPullParser.END_TAG:
								if(		tag.equals("heritage")	||	tag.equals("standing")	||	tag.equals("sponsor") ||
										tag.equals("callNumber")||	tag.equals("arrange")	||	tag.equals("tel") ||
										tag.equals("openDate")	||	tag.equals("closeDate")	||	tag.equals("uncertain") ||
										tag.equals("playTime")	||	tag.equals("eventPlace")||	tag.equals("homepage") ||
										tag.equals("spendTime")	||	tag.equals("ageRange")	||	tag.equals("bookingPlace") ||
										tag.equals("bookingInfo")||	tag.equals("subEvent")	||	tag.equals("introList") ||
										tag.equals("useList")	||	tag.equals("subList"))
									tagId = TAGNUM.NULL;
								break;
							case XmlPullParser.TEXT:
								switch(tagId){
								case heritage:{
									String tmp = parser.getText().trim();
									parse_heritage = (tmp == "1") ? true : false;
									break;
								}
								case standing:{
									String tmp = parser.getText().trim();
									parse_standing = (tmp == "1") ? true : false;
									break;
								}	
								case sponsor:
									parse_sponsor = parser.getText().trim();
									break;
								case callNumber:
									parse_callNumber = parser.getText().trim();
									break;
								case arrange:
									parse_arrange = parser.getText().trim();
									break;
								case tel:
									parse_resTel = parser.getText().trim();
									break;
								case openDate:
									parse_openDate = parser.getText().trim();
									break;
								case closeDate:
									parse_closeDate = parser.getText().trim();
									break;
								case uncertain:{
									String tmp = parser.getText().trim();
									parse_uncertain = (tmp == "1") ? true : false;
									break;
								}
								case playTime:
									parse_playTime = parser.getText().trim();
									break;
								case eventPlace:
									parse_eventPlace = parser.getText().trim();
									break;
								case homepage:
									parse_resHomepage = parser.getText().trim();
									break;
								case spendTime:
									parse_spendTime = parser.getText().trim();
									break;
								case ageRange:
									parse_ageRange = parser.getText().trim();
									break;
								case bookingPlace:
									parse_bookingPlace = parser.getText().trim();
									break;
								case bookingInfo:
									parse_bookingInfo = parser.getText().trim();
									break;
								case subEvent:
									parse_subEvent = parser.getText().trim();
									break;
								case introList:{
									String parse_introListName = null, parse_introListContents = null;
									parse_introListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_introListContents = parser.getText();
									parse_introList.add(new infoList(parse_introListName, parse_introListContents));
									//System.out.println("facilList done");
									break;
								}
								case useList:{
									String parse_useListName = null, parse_useListContents = null;
									parse_useListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_useListContents = parser.getText();
									parse_useList.add(new infoList(parse_useListName, parse_useListContents));
									//System.out.println("useList done");
									break;
								}
								case subList:{
									String parse_subListName = null, parse_subListContents = null;
									parse_subListName = parser.getText();
									parser.next();
									parser.next();
									if((parser.next() == XmlPullParser.TEXT)) parse_subListContents = parser.getText();
									parse_subList.add(new infoList(parse_subListName, parse_subListContents));
									//System.out.println("useList done");
									break;
								}
								}
								break;
							}
							evtType = parser.next();
							tag = parser.getName();
						}
						parse_exData = new eventData(
								parse_heritage, parse_standing, parse_sponsor,
								parse_callNumber, parse_arrange, parse_resTel,
								parse_openDate, parse_closeDate, parse_uncertain,
								parse_playTime, parse_eventPlace, parse_resHomepage,
								parse_spendTime, parse_ageRange, parse_bookingPlace,
								parse_bookingInfo, parse_subEvent,
								parse_introList, parse_useList, parse_subList);
						break;
			}
			}
			/* 추가 정보 파싱 완료 */

			xmlData parse_data = new detailData(parse_title, parse_modified, parse_mapImg, parse_firstImg,
					parse_address, parse_map, parse_homepage, parse_manager, parse_tel,
					parse_overview,	parse_trans, parse_relate, parse_imageList, 
					parse_videoURL, parse_exData);
			mXmlDataList.add(parse_data);

			mXmlDataList.get(0).print();

			//detailData test = (detailData) mXmlDataList.get(0).getObject();

			//Log.d("testttt", ((sightData)test.getExData()).getSightIntro().getParking());
		} catch (Exception e) {
			Log.e("TourApiParser", e.getMessage() + "parseType_2 exception");
		}
	}

	private void parseType_3(XmlPullParser parser){
		//String tag = parser.getName();
		try{
			int evtType = parser.getEventType();
			//int tagId = 0;
			while(evtType != XmlPullParser.END_DOCUMENT){
				switch(evtType){
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					break;
				case XmlPullParser.END_TAG:
					break;
				case XmlPullParser.TEXT:
					break;
				}
				evtType = parser.next();
			}			
		} catch (Exception e) {
			Log.e("TourApiParser", e.getMessage() + "parseType_3 exception");
		}
	}

	private void parseType_4(XmlPullParser parser){
		//String tag = parser.getName();
		try{
			int evtType = parser.getEventType();
			//int tagId = 0;
			while(evtType != XmlPullParser.END_DOCUMENT){
				switch(evtType){
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					break;
				case XmlPullParser.END_TAG:
					break;
				case XmlPullParser.TEXT:
					break;
				}
				evtType = parser.next();
			}
		} catch (Exception e) {
			Log.e("TourApiParser", e.getMessage() + "parseType_4 exception");
		}
	}

	private void parseType_5(XmlPullParser parser){
		String tag = parser.getName();
		if(!tag.equals("codeResponse")){
			Log.e("parseType_5", "invalid xml");
			return;
		}

		try{
			int evtType = parser.getEventType();
			TAGNUM tagId = TAGNUM.NULL;
			boolean flag = true;

			String areaCodeType = "", parse_code = "", parse_codeName = "";
			areaCode parse_areaCode = null;

			while(parser.next() != XmlPullParser.START_TAG){;}
			areaCodeType = parser.getName().trim();

			while(evtType != XmlPullParser.END_DOCUMENT){
				switch(evtType){
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if(tag.equals("area") || tag.equals("sigungu") || tag.equals("dong"))
						tagId = TAGNUM.area;
					break;
				case XmlPullParser.END_TAG:
					if(tag.equals("area") || tag.equals("sigungu") || tag.equals("dong"))
						tagId = TAGNUM.NULL;
					else if(tag.equals("list")){
						xmlData parse_data = new codeData(areaCodeType, parse_areaCode);

						mXmlDataList.add(parse_data);

						mXmlDataList.get(0).print();

						//	codeData test = (codeData) mXmlDataList.get(0).getObject();

						//	Log.d("testttt", test);
					}
					break;
				case XmlPullParser.TEXT:
					if(tagId == TAGNUM.area){
						if(flag){
							parse_code = parser.getText().trim();
							flag = false;
						}
						else{
							parse_codeName = parser.getText().trim();
							flag = true;

							parse_areaCode = new areaCode(parse_code, parse_codeName);
						}
					}
					break;
				}
				evtType = parser.next();
				tag = parser.getName();
			}
		} catch (Exception e) {
			Log.e("TourApiParser", e.getMessage() + "parseType_5 exception");
		}
	}

	public ArrayList<xmlData> getXmlDataList(){
		return mXmlDataList;
	}

	public void run(){
		try{
			strtParsing();
			mHandler.sendEmptyMessage(0);
		} catch (Exception e){
			Log.e("TourApiParser Thread", e.getMessage());
			mHandler.sendEmptyMessage(1);
		}
		//System.out.println("complete.");
	}
}
