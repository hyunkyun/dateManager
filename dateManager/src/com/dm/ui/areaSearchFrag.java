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
					System.out.println("�Ÿ��� �Է��ϼ���.");
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
		 * �Ʒ��� �̿� ����� String[]�� �� �����̸�,
		 * �����̸� �ڿ� * ���� ���� �ݵ�� �ʿ��� ���� ��Ÿ����.
		 * ���� �������� ��� ���ÿ��� �׿� �ش��ϴ� ���� ������ �ϸ�,
		 * ������ ���� ��쿣 "0"�� ���� �־� �־�� �Ѵ�.
		 * 
		 * ���� String[]�� �����ÿ� �ڽ��� �˻� �Ǵ� �̿��� ������ ���� ��ŭ "0" �� �Ҵ��Ͽ� �ָ� �Ǽ��� ������ ���̴�.
		 * ex) ���� ��� �˻��� ������ ����� String�� ������ ���� �����ϸ� �ȴ�.
		 * String quarryStr[] = {"1", "1", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0" ,"0", "0"};
		 * ������ quarryStr[0]�� quarryStr[1]�� ��� 1�� ä���� ������ 
		 * ���� ��� �˻��� ��� �̿��� Str�� Ÿ���� 1���� quarryStr[0]���� 1�̶�� ������ �Ǻ��ϰ� �Ǹ�,
		 * �˻��� ������ �ش��ϴ� reqType�� �׻� 1�� ���� �����Ƿ� �̸� �Ҵ��ϴ� ���� ������, 
		 * �� ������ �Ҵ�� ���� �����ϸ� �ȵȴ�.
		 * ���� "0"���� �� ���� ������ �����ϴ�.
		 * 
		 * 1.1 ���� ��� �˻�
		 * =============================================
		 * [0]�� "1"�� ���� ������.
		 * ��û ���� ��� [1] ~ [15]
		 * reqType*			�˻� ����		1�� ���� ������ String[]�� �ι�° value�� �ȴ�.
		 * code1*			���� �ڵ�		���� ��� �˻��� �ʿ��� �ּ����� �ڵ��̴�.
		 * code2			��/��/�� �ڵ�	���� ��� �˻����� ���� �������� ������ ã�� ���� �ڵ��̴�.
		 * code3			��/��/�� �ڵ�	���� ��� �˻����� ���� �������� ������ ã�� ���� �ڵ��̴�.
		 * type*			������ Ÿ�� �ڵ�	������ ���� ���� ��� �˻��� ���ؼ� ��� ��Ҹ� ã�������� ���� ��. ex) ������, ������ etc.
		 * cat1				��з�		������ Ÿ���� �������� �з� ����
		 * cat2				�ߺз�		-
		 * cat3				�Һз�		-
		 * title			����			��ҿ� ������ String���� �ּ� 2�ڰ� �Է� �Ǿ�� �ϸ�, �ش� String�� ���Ե� �˻� ������� ���� �� �ְ� ���ִ� ����.
		 * date				��� �Ⱓ		Ư�� ��� �Ⱓ�� �Է� �Ҷ� ���ȴ�. YYYYMMDD�� �Է� �ȴ�.
		 * themeType		�׸������ڵ�	type�� ������ ��쿡�� ��� �����ϴ�. �� �̿ܿ� "0" �� ���� �����߸� �Ѵ�.
		 * goodstayYN		�½����� ����	type�� ������ ��쿡�� ��� �����ϴ�. �� �̿ܿ� "0" �� ���� �����߸� �Ѵ�.
		 * venikiaYN		����Ű�� ����	type�� ������ ��쿡�� ��� �����ϴ�. �� �̿ܿ� "0" �� ���� �����߸� �Ѵ�.
		 * pageIndex		������ ��ȣ		��û�� ������ ��ȣ. "0"�ϰ�� �⺻������ 1�� ����ϰ� �ȴ�.
		 * pageSize			������ ũ��		�������� ������ �������� �˻� ����� ������ ���� �ϴ� ���̸�, "0"�ϰ�� �⺻������ 10���� �����ȴ�.
		 * =============================================
		 * 
		 * 1.2. ��ġ ��� �˻�
		 * =============================================
		 * [0]�� "1"�� ���� ������.
		 * ��û ���� ��� [1] ~ [15]
		 * reqType*			�˻� ����		2�� ���� ������ String[]�� �ι�° value�� �ȴ�.
		 * mapX*			X ��ǥ		���� ��� �˻��� �ʿ��� �ּ����� �ڵ��̴�.
		 * mapY*			Y ��ǥ		���� ��� �˻����� ���� �������� ������ ã�� ���� �ڵ��̴�.
		 * radius*			�ݰ� m ����	���� ��� �˻����� ���� �������� ������ ã�� ���� �ڵ��̴�.
		 * type*			������ Ÿ�� �ڵ�	������ ���� ���� ��� �˻��� ���ؼ� ��� ��Ҹ� ã�������� ���� ��. ex) ������, ������ etc.
		 * cat1				��з�		������ Ÿ���� �������� �з� ����
		 * cat2				�ߺз�		-
		 * cat3				�Һз�		-
		 * title			����			��ҿ� ������ String���� �ּ� 2�ڰ� �Է� �Ǿ�� �ϸ�, �ش� String�� ���Ե� �˻� ������� ���� �� �ְ� ���ִ� ����.
		 * date				��� �Ⱓ		Ư�� ��� �Ⱓ�� �Է� �Ҷ� ���ȴ�. YYYYMMDD�� �Է� �ȴ�.
		 * themeType		�׸������ڵ�	type�� ������ ��쿡�� ��� �����ϴ�. �� �̿ܿ� "0" �� ���� �����߸� �Ѵ�.
		 * goodstayYN		�½����� ����	type�� ������ ��쿡�� ��� �����ϴ�.("Y" or "N", ��ǻ� "N"�� �� ������ ����.) �� �̿ܿ� "0" �� ���� �����߸� �Ѵ�.
		 * venikiaYN		����Ű�� ����	type�� ������ ��쿡�� ��� �����ϴ�.("Y" or "N", ��ǻ� "N"�� �� ������ ����.) �� �̿ܿ� "0" �� ���� �����߸� �Ѵ�.
		 * pageIndex		������ ��ȣ		��û�� ������ ��ȣ. "0"�ϰ�� �⺻������ 1�� ����ϰ� �ȴ�.
		 * pageSize			������ ũ��		�������� ������ �������� �˻� ����� ������ ���� �ϴ� ���̸�, "0"�ϰ�� �⺻������ 10���� �����ȴ�.
		 * =============================================
		 */
		return v;
	}

	private void processQuery(String[] quarryStr){
		mStartTime = System.currentTimeMillis();
		mXMLParser.setUrl(quarryStr);
		Thread thread = new Thread(mXMLParser);
		thread.start();
		progressDialog = ProgressDialog.show(getActivity(), "Loading..", "����Ʈ�� �ҷ��ɴϴ�",true,false);
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
