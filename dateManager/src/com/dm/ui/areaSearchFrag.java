package com.dm.ui;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.dm.R;
import com.dm.content.tourapi.ctrl.TourApiParser;
import com.dm.content.tourapi.data.unionData;
import com.dm.content.tourapi.data.xmlData;

public class areaSearchFrag extends Fragment {
	private View v;
	private long mStartTime;
	private long mEndTime;
	private TourApiParser mXMLParser;
	private ArrayList<xmlData> resultList;
	private ProgressDialog progressDialog;

	private final Handler handler = new Handler(){
		public void handleMessage(final Message msg){
			Log.i("hi", "Handler");
			progressDialog.dismiss();
			resultList = mXMLParser.getXmlDataList();
			
			System.out.println("now!");
			
			Intent intent = new Intent(getActivity(), tourApiView.class);
			
			int size = resultList.size();
			intent.putExtra("size", size);
			unionData tmpData = null;
			String[] title = new String[size];
			String[] imgUrl = new String[size];
			int[] contentID = new int[size];
			double[] mapX = new double[size];
			double[] mapY = new double[size];
			
			for(int i = 0; i < size; i++){
				tmpData = (unionData)resultList.get(i);
				title[i] = tmpData.getTitle();
				imgUrl[i] = "";
				contentID[i] = tmpData.getContentId();
				mapX[i] = tmpData.getMap().getMapX();
				mapY[i] = tmpData.getMap().getMapY();
			}
			
			intent.putExtra("contentID", contentID);
			intent.putExtra("imgUrl", imgUrl);
			intent.putExtra("title", title);
			intent.putExtra("mapX", mapX);
			intent.putExtra("mapY", mapY);
			
			startActivity(intent);
		}
	};

	private final Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			Log.i("hi", "Thread");
			if(msg.what == 1){
				progressDialog.dismiss();
				Log.e("TourApiParser Thread", "Thread run exception");
			}
			else{
				mEndTime = System.currentTimeMillis();
				Log.d("Taken time", Long.toString((mEndTime - mStartTime) / 1000L));
				show();
			}
		}
	};

	public void show(){
		Log.i("hi", "show()");
		new Thread(){
			public void run(){
				handler.sendEmptyMessage(0);
			}
		}.start();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mXMLParser = new TourApiParser(mHandler, "TA2012030214352741002461");
	}

	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.area_search, container, false);
		Log.i("hi", "onCreateView");
		/* option process */

		final EditText surSearchDis= (EditText)v.findViewById(R.id.surSearchDis);
		Button surSearchBtn = (Button)v.findViewById(R.id.surSearchBtn);
		Log.i("hi", "BUtton");
		surSearchBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String quarryStr[] = {"1", "2", "", "", "",
									"", "0", "0", "0", "0",
									"0", "0", "0", "0", "1", "20"};
				
				quarryStr[2] = "126.97860756362526"; //getGPSX();
				quarryStr[3] = "37.569102135431045"; //getGPSY();
				quarryStr[5] = "12";//getContensType();
				if((quarryStr[4] = surSearchDis.getText().toString()).equals(""))
					System.out.println("거리를 입력하세요.");
				else processQuery(quarryStr);
			}
		});
		EditText searchLoctionName= (EditText)v.findViewById(R.id.searchLoctionName);
		Button nameSearchBtn = (Button)v.findViewById(R.id.nameSearchBtn);

		nameSearchBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				/* search */
			}
		});

		Button searchLeports = (Button)v.findViewById(R.id.searchLeports);

		searchLeports.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String quarryStr[] = {"1", "1", "", "", "",
									"28", "0", "0", "0", "0",
									"0", "0", "0", "0", "1", "20"};

				quarryStr[2] = "1";
				quarryStr[3] = "0";
				quarryStr[4] = "0";

				processQuery(quarryStr);
			}
		});
		Button searchLodging = (Button)v.findViewById(R.id.searchLodging);

		searchLodging.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String quarryStr[] = {"1", "1", "", "", "",
									"32", "0", "0", "0", "0",
									"0", "0", "0", "0", "1", "20"};

				quarryStr[2] = "1";
				quarryStr[3] = "0";
				quarryStr[4] = "0";

				processQuery(quarryStr);
			}
		});
		Button searchChophouse = (Button)v.findViewById(R.id.searchChophouse);

		searchChophouse.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String quarryStr[] = {"1", "1", "", "", "",
									"12", "0", "0", "0", "0",
									"0", "0", "0", "0", "1", "20"};

				quarryStr[2] = "1";
				quarryStr[3] = "0";
				quarryStr[4] = "0";

				processQuery(quarryStr);

			}
		});
		Button searchCulture = (Button)v.findViewById(R.id.searchCulture);

		searchCulture.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				String[] quarryStr = {"1", "1", "", "", "",
									"14", "0", "0", "0", "0",
									"0", "0", "0", "0", "1", "20"};

				quarryStr[2] = "1";
				quarryStr[3] = "0";
				quarryStr[4] = "0";
				processQuery(quarryStr);
			}
		});
		/*
		 * 아래는 이용 방식의 String[]에 들어갈 내용이며,
		 * 변수이름 뒤에 * 붙은 경우는 반드시 필요한 값을 나타낸다.
		 * 없는 변수들의 경우 사용시에는 그에 해당하는 값이 쓰여야 하며,
		 * 쓰이지 않을 경우엔 "0"의 값을 넣어 주어야 한다.
		 * 
		 * 따라서 String[]을 생성시에 자신이 검색 또는 이용할 변수의 갯수 만큼 "0" 을 할당하여 주면 실수가 적어질 것이다.
		 * ex) 지역 기반 검색시 쿼리로 사용할 String을 다음과 같이 생성하면 된다.
		 * String quarryStr[] = {"1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" ,"0", "0"};
		 * 위에서 quarryStr[0]과 quarryStr[1]의 경우 1로 채워진 이유는 
		 * 지역 기반 검색의 경우 이용할 Str의 타입이 1임을 quarryStr[0]에서 1이라는 값으로 판별하게 되며,
		 * 검색의 종류에 해당하는 reqType은 항상 1의 값을 가지므로 미리 할당하는 것이 좋으며, 
		 * 이 생성시 할당된 값은 변경하면 안된다.
		 * 물론 "0"으로 들어간 값은 변경이 가능하다.
		 * 
		 * 1.1 지역 기반 검색
		 * =============================================
		 * [0]은 "1"의 값을 가진다.
		 * 요청 변수 목록 [1] ~ [15]
		 * reqType*			검색 종류		1의 값을 가지며 String[]의 두번째 value가 된다.
		 * code1*			지역 코드		지역 기반 검색에 필요한 최소한의 코드이다.
		 * code2			시/군/구 코드	지역 기반 검색에서 좀더 세부적인 지역을 찾기 위한 코드이다.
		 * code3			읍/면/동 코드	지역 기반 검색에서 좀더 세부적인 지역을 찾기 위한 코드이다.
		 * type*			컨텐츠 타입 코드	유형에 대한 정보 장소 검색에 대해서 어떠한 장소를 찾을건지에 대한 값. ex) 관광지, 음식점 etc.
		 * cat1				대분류		컨텐츠 타입의 세부적인 분류 내역
		 * cat2				중분류		-
		 * cat3				소분류		-
		 * title			제목			장소에 들어가야할 String으로 최소 2자가 입력 되어야 하며, 해당 String이 포함된 검색 결과만을 받을 수 있게 해주는 변수.
		 * date				행사 기간		특정 행사 기간을 입력 할때 사용된다. YYYYMMDD로 입력 된다.
		 * themeType		테마유형코드	type이 숙박일 경우에만 사용 가능하다. 그 이외엔 "0" 의 값을 가져야만 한다.
		 * goodstayYN		굿스테이 여부	type이 숙박일 경우에만 사용 가능하다. 그 이외엔 "0" 의 값을 가져야만 한다.
		 * venikiaYN		베니키아 여부	type이 숙박일 경우에만 사용 가능하다. 그 이외엔 "0" 의 값을 가져야만 한다.
		 * pageIndex		페이지 번호		요청할 페이지 번호. "0"일경우 기본적으로 1로 취급하게 된다.
		 * pageSize			페이지 크기		페이지당 데이터 수량으로 검색 결과의 갯수를 지정 하는 값이며, "0"일경우 기본적으로 10으로 지정된다.
		 * =============================================
		 * 
		 * 1.2. 위치 기반 검색
		 * =============================================
		 * [0]은 "1"의 값을 가진다.
		 * 요청 변수 목록 [1] ~ [15]
		 * reqType*			검색 종류		2의 값을 가지며 String[]의 두번째 value가 된다.
		 * mapX*			X 좌표		지역 기반 검색에 필요한 최소한의 코드이다.
		 * mapY*			Y 좌표		지역 기반 검색에서 좀더 세부적인 지역을 찾기 위한 코드이다.
		 * radius*			반경 m 단위	지역 기반 검색에서 좀더 세부적인 지역을 찾기 위한 코드이다.
		 * type*			컨텐츠 타입 코드	유형에 대한 정보 장소 검색에 대해서 어떠한 장소를 찾을건지에 대한 값. ex) 관광지, 음식점 etc.
		 * cat1				대분류		컨텐츠 타입의 세부적인 분류 내역
		 * cat2				중분류		-
		 * cat3				소분류		-
		 * title			제목			장소에 들어가야할 String으로 최소 2자가 입력 되어야 하며, 해당 String이 포함된 검색 결과만을 받을 수 있게 해주는 변수.
		 * date				행사 기간		특정 행사 기간을 입력 할때 사용된다. YYYYMMDD로 입력 된다.
		 * themeType		테마유형코드	type이 숙박일 경우에만 사용 가능하다. 그 이외엔 "0" 의 값을 가져야만 한다.
		 * goodstayYN		굿스테이 여부	type이 숙박일 경우에만 사용 가능하다.("Y" or "N", 사실상 "N"가 들어갈 이유는 없다.) 그 이외엔 "0" 의 값을 가져야만 한다.
		 * venikiaYN		베니키아 여부	type이 숙박일 경우에만 사용 가능하다.("Y" or "N", 사실상 "N"가 들어갈 이유는 없다.) 그 이외엔 "0" 의 값을 가져야만 한다.
		 * pageIndex		페이지 번호		요청할 페이지 번호. "0"일경우 기본적으로 1로 취급하게 된다.
		 * pageSize			페이지 크기		페이지당 데이터 수량으로 검색 결과의 갯수를 지정 하는 값이며, "0"일경우 기본적으로 10으로 지정된다.
		 * =============================================
		 */
		return v;
	}

	private void processQuery(String[] quarryStr){
		mStartTime = System.currentTimeMillis();
		mXMLParser.setUrl(quarryStr);
		Thread thread = new Thread(mXMLParser);
		thread.start();
		progressDialog = ProgressDialog.show(getActivity(), "Loading..", "리스트를 불러옵니다",true,false);
	}
	
	/*void addFragmentToStack() {        
		Fragment newFragment = CountingFragment.newInstance();
		
		// Add the fragment to the activity, pushing this transaction        
		// on to the back stack.        
		FragmentTransaction ft = getFragmentManager().beginTransaction();        
		ft.replace(R.id.simple_fragment, newFragment);        
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);        
		ft.addToBackStack(null);        
		ft.commit();
	}*/
}
