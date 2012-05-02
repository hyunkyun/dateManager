package com.dm.content.tourapi.ctrl;

import java.io.InputStream;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/**
 * @author  Gwak seok jong
 */
public abstract class xmlParser {
	public XmlPullParser getXmlParser(String url, String type){
		try{
			URL targetURL = new URL(url);
			InputStream is = targetURL.openStream();
			
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			
			parser.setInput(is, type);
						
			return parser;
		} catch(Exception e){
			Log.d("XmlParser", e.getMessage());
			return null;
		}
	}
	
	public abstract void strtParsing() throws Exception;
}
