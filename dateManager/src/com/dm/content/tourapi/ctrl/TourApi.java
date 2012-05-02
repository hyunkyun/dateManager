package com.dm.content.tourapi.ctrl;
import java.io.FileOutputStream;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * @author  Gwak seok jong
 */
public class TourApi extends api{
	
	public TourApi(String _key) {
		super(_key);
		// TODO Auto-generated constructor stub
	}

	public void setKey(String _key){
		key = new String(_key);
	}
	
	/*	test �� �ڵ�	*/
	//public static void main(String[] argv){
	//String parm[] = {"1","1", "31", "15", "0", "32","0","0","B02010900", "0", "0", "0", "0", "0" ,"0", "0"};	
	public String getUrl(String[] parm) {
		
		int UDABC = Integer.parseInt(parm[0]);
		/* UDABC�� ���ں� �ǹ�
		 * ��� �˻� 			Union : 1 
		 * �� ���� �˻� 		Detail : 2 
		 * ���� ��� �˻� 		Article : 3 
		 * ��õ ���� ���� �� �˻�	Best : 4 
		 * ���� �ڵ� �˻�			Code : 5
		 */
		String quarry = "";// = "union?reqType=1&code1=1&type=32";
		/*	���� ���� ����	*/

		try{
			/*	Switch�� ����	*/
			switch(UDABC){

			/*	��� �˻� 			Union : 1	*/
			case 1:{
				quarry += "union?";

				try{
					if(parm[1].matches("1")){
						// ex) {"1","1", "31", "15", "0", "32","0","0","B02010900", "0", "0", "0", "0", "0" ,"0", "0"}
						String reqMsg[] = { "reqType", "code1", "code2", "code3", "type", "cat1", "cat2", "cat3",
								"title", "date", "themeType", "goodstayYN", "venikiaYN", "pageIndex", "pageSize"};

						quarry += reqMsg[0] + "=1";
						
						parm[9] = URLEncoder.encode(parm[9], "UTF-8");

						for(int i = 1; i < 15; i++){
							if(parm[i+1].matches("0")){
								if(i == 1 || i == 4){
									System.err.println(reqMsg[i] + " Wrong request!!(case Union 1)");
									break;
								}
								continue;
							}

							quarry += "&" + reqMsg[i] + "=" + parm[i+1];
						}
					}
					else if(parm[1].matches("2")){
						// ex) {"1","2", "126.97860756362526", "37.569102135431045","500","12","0","0","A02020700", "0", "0", "0", "0", "0" ,"0", "0"};
						String reqMsg[] = { "reqType", "mapX", "mapY", "radius", "type", "cat1", "cat2", "cat3",
								"title", "date", "themeType", "goodstayYN", "venikiaYN", "pageIndex", "pageSize"};

						quarry += reqMsg[0] + "=2";
						
						parm[9] = URLEncoder.encode(parm[9], "UTF-8");

						for(int i = 1; i < 15; i++){
							if(parm[i+1].matches("0")){
								if(i < 5 && i > 0){
									System.err.println(reqMsg[i] + " Wrong request!!(case Union 1)");
									break;
								}
								continue;
							}

							quarry += "&" + reqMsg[i] + "=" + parm[i+1];
						}
					}
					else System.err.println(" Wrong reqType Number!!(case Union 1)");
				} catch (Exception e){
					System.err.println("Fatal violation(case Union 1): " + e.getMessage()); 
					e.printStackTrace();
				}
				break;
			}
			/*	��� �˻� 			Union : 1	*/

			/*	�� ���� �˻� 		Detail : 2	*/
			case 2:{
				/*	ex) {"2", "129507", "Y", "Y", "0", "0", "0", "0", "0", "0", "0"};	*/
				try{
					String reqMsg[] = { "contentId", "defaultYN", "overviewYN", "transGuideYN", "relateGuideYN",
							"imageYN", "videoYN", "introYN", "detailYN", "subImageYN" };

					quarry += "detail?contentId=" + parm[1];

					for(int i = 1; i < 10; i++){
						if(parm[i+1].matches("0"))	continue;

						quarry += "&" + reqMsg[i] + "=" + parm[i+1];
					}

				} catch (Exception e){
					System.err.println("Fatal violation(case Detail 2): " + e.getMessage()); 
					e.printStackTrace();
				}
				break;
			}
			/*	�� ���� �˻� 		Detail : 2	*/

			/*	���� ��� �˻� 		Article : 3	*/
			case 3:{
				/*	ex) {"3", "304", "�빮��", "0", "1", "20"};	*/
				try{
					String type = parm[1], title = parm[2], overviewYN = parm[3], pageIndex = parm[4], pageSize = parm[5];

					quarry += "article?type=" + type;

					String enStr = URLEncoder.encode(title, "UTF-8");

					if(!(title.matches("0")))		quarry += "&title=" + enStr;
					if(!(overviewYN.matches("0")))	quarry += "&overviewYN=" + overviewYN;
					if(!(pageIndex.matches("0")))	quarry += "&pageIndex=" + pageIndex;
					if(!(pageSize.matches("0")))	quarry += "&pageSize=" + pageSize;

				} catch (Exception e){
					System.err.println("Fatal violation(case Article 3): " + e.getMessage()); 
					e.printStackTrace();
				}
				break;
			}
			/*	���� ��� �˻� 		Article : 3	*/

			/*	��õ ���� ���� �� �˻�	Best : 4	*/
			case 4:{ 
				// ex) {"4", "201004", "1", "20"}
				try{
					String month = parm[1], pageIndex = parm[2], pageSize = parm[3];

					quarry += "best?" + "month=" + month;

					if(!(pageIndex.matches("0")))	quarry += "&pageIndex=" + pageIndex;
					if(!(pageSize.matches("0")))	quarry += "&pageSize=" + pageSize;

				} catch (Exception e){
					System.err.println("Fatal violation(case Best 4): " + e.getMessage()); 
					e.printStackTrace();
				}
				break;
			}
			/*	��õ ���� ���� �� �˻�	Best : 4	*/

			/*	���� �ڵ� �˻�		Code : 5	*/
			case 5:{
				// ex) {"5", "31", "0", "0", "0"}
				try{
					String code1 = parm[1], code2 = parm[2], code3 = parm[3];

					quarry += "code?code1=" + code1;

					if(!(code2.matches("0"))){
						quarry += "&code3=" + code2;
						if(!(code3.matches("0"))){
							quarry += "&code3=" + code3;
						}
					}
				} catch (Exception e){
					System.err.println("Fatal violation(case Code 5): " + e.getMessage()); 
					e.printStackTrace();
				}
				break;
			}
			/*	���� �ڵ� �˻�		Code : 5	*/

			}   /*	Switch�� ����	*/
			
			/*	Quarry ��� ���	*/
			
			String retStr = quarry;
			//System.out.println(retStr);
			
			return retStr;
			
		} catch(Exception e){
			System.err.println("Fatal violation(case Code 5): " + e.getMessage()); 
			e.printStackTrace();
			
			return "";
		}
	}

	/**
	 * @author  TourApi
	 */
	public String getXmlTourAPI(String QuarryURL) { //QueryString ����

		String url = "http://tourapi.visitkorea.or.kr/TourAPI/services/";

		url = url + QuarryURL;

		//HttpClient ���� 
		HttpClient client = new HttpClient(); 

		//��û Method ���� 
		HttpMethod method = new GetMethod(url); 
		String responseXML = ""; 

		method.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		if(key.matches("")){
			System.err.println("must set key value!!!");
			return "";
		}
		method.setRequestHeader("Authentication", key);
		//������� TourAPI ����Ű ����  

		try { 
			//System.out.println( "QueryString>>>" + method.getQueryString() ); 
			
			//Http ��û �� ��û ��� 
			int statusCode = client.executeMethod(method); 
			//System.out.println("HTTP status code : " + statusCode ); 


			//��û��� 
			if(statusCode == HttpStatus.SC_OK){ 
				//System.out.println("=========================================================="); 
				//System.out.println("��û ����\n"); 

				//����� ȭ�鿡 ��� 
				responseXML = new String( method.getResponseBody(), "UTF-8" ); 
				//System.out.println("���� XML:\n" + responseXML); 
				//System.out.println("=========================================================="); 

				//����� ���Ͽ� ���� 
				//saveBytes("res/xml/reson.xml", method.getResponseBody()); 
			} else { 
				System.err.println("Method failed: " + method.getStatusLine());

				return "";
			} 


		} catch (Exception e) { 
			System.err.println("Fatal protocol violation: " + e.getMessage()); 
			e.printStackTrace(); 
		} finally { 
			// Release the connection 
			method.releaseConnection(); 
		} //try end

		return responseXML;
	} //getXmlTourAPI end
	
	/**
	 * @author  TourApi
	 */
	//���Ͽ� byte[]�� �����ϴ� method
	public static void saveBytes(String fileName, byte[] byteData) throws Exception{

		FileOutputStream fo = null;

		try { 
			fo = new FileOutputStream(fileName); 
			fo.write(byteData); 
		} finally{ 
			try { fo.close(); fo = null; } catch(Exception e){} 
		} 
	} 
}