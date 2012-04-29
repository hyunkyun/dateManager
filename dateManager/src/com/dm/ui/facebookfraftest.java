package com.dm.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dm.R;
import com.dm.content.facebook.BaseRequestListener;
import com.dm.content.facebook.LoginButton;
import com.dm.content.facebook.SessionEvents;
import com.dm.content.facebook.SessionEvents.AuthListener;
import com.dm.content.facebook.SessionEvents.LogoutListener;
import com.dm.content.facebook.lib.AsyncFacebookRunner;
import com.dm.content.facebook.lib.Facebook;
import com.dm.content.facebook.lib.FacebookError;
import com.dm.content.facebook.lib.Util;
import com.dm.content.facebook.SessionStore;

/***
 * 
 * @author HyunKyun
 *
 */
public class facebookfraftest extends Fragment {
	
	//public static final String APP_ID = "225012670861641";
	public static final String APP_ID = "150967961690718";
	public String USER_ID;
	public String EMAIL;
	private LoginButton auth;
	private Facebook mFacebook;
	String[] permission;
	//master
	private AsyncFacebookRunner mAsyncRunner;
	ProgressDialog dialog;	
	
	EditText comment;
	Button post;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFacebook = new Facebook(APP_ID);
		mAsyncRunner = new AsyncFacebookRunner(mFacebook);
		SessionEvents.addAuthListener(new SampleAuthListener());
		SessionEvents.addLogoutListener(new SampleLogoutListener());
		SessionStore.restore(mFacebook, getActivity());
		//getRequestFaceBook();
		
	}
	
	/** Called when the activity is first created. */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.facebook, container, false);
		/*
		 * 세션을 살리는건데,
		 * 
		 * 인증을 안받았기때문에 실패한다. 최초실행시 
		 * 
		 */
		
		permission = new String[]{"publish_stream", "email"};
		Log.e("facebook", "init");
		
	//	comment = (EditText)findViewById(R.id.comment);
	//	post = (Button) findViewById(R.id.post);

		auth = (LoginButton) v.findViewById(R.id.auth);
		auth.init(getActivity(), mFacebook , permission );
		
		/*post.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (mFacebook.isSessionValid()) {			

					String commentString = comment.getText().toString();

					try {
						
						dialog = new ProgressDialog(MainActivity.this);		
						dialog.setMessage("Writing a comment . . .");		
						dialog.show();	
												
						Bundle parameters = new Bundle();						
						parameters.putString("message", commentString);
						
						mAsyncRunner.request(USER_ID+"/feed", parameters, "POST", new WallPostRequestListener(), null);					

					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					Toast.makeText(MainActivity.this, "Not Authorized!", Toast.LENGTH_SHORT).show();
				}
			}
		});*/
		return v;
	}

	public void getRequestFaceBook()
	{
		dialog = new ProgressDialog(getActivity());		
		dialog.setMessage("계정 정보를 확인합니다.");		
		dialog.show();
		Log.e("facebook", "getRequestFacebook");
		mAsyncRunner.request("me", new SampleRequestListener());
	}
/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		mFacebook.authorizeCallback(requestCode, resultCode, data);
	}
*/
	/*
	 * 인증 다이얼로그 리스너
	 */
	
	public class SampleAuthListener implements AuthListener {
		public void onAuthSucceed() {
			/*
			 * 리퀘스트 한다.
			 * 
			 */
			Toast.makeText(getActivity(), "You have logged in! ",Toast.LENGTH_SHORT).show();
			getRequestFaceBook();
			
		}

		public void onAuthFail(String error) {
			Toast.makeText(getActivity(), "Login Failed: ",	Toast.LENGTH_SHORT).show();
		}
	}

	public class SampleLogoutListener implements LogoutListener {
		public void onLogoutBegin() {
			Toast.makeText(getActivity(), "Logging out...",	Toast.LENGTH_SHORT).show();
		}

		public void onLogoutFinish() {
			Toast.makeText(getActivity(), "You have logged out! ", Toast.LENGTH_SHORT).show();
		}
	}	
	
	public class SampleRequestListener extends BaseRequestListener {

        public void onComplete(final String response, final Object state) {
            try {
            	if(dialog.isShowing())
            		dialog.dismiss();
                JSONObject json = Util.parseJson(response);   
                USER_ID = json.getString("id");
                EMAIL = json.getString("email");

            } catch (JSONException e) {
                Log.w("Facebook-Example", "JSON Error in response");
            } catch (FacebookError e) {
                Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
            }
            
            getActivity().runOnUiThread(new Runnable() {
                public void run() {            	                
                	 Toast.makeText(getActivity(), "ID : " + USER_ID + "\nEMAIL : " + EMAIL, Toast.LENGTH_SHORT).show();	                              	
                }
            });     
        }
    }
	/*
	 * 글쓰기 포스트용 리스너
	 */
	
	public class WallPostRequestListener extends BaseRequestListener {

        public void onComplete(final String response, final Object state) {
            
        	Log.d("Facebook-Example", "Got response: " + response);
            
        	if(dialog.isShowing())
        		dialog.dismiss();
            
            String message = null;
            
            try {
                
            	JSONObject json = Util.parseJson(response);                
                message = json.getString("message");
                
            } catch (JSONException e) {
            	
            	getActivity().runOnUiThread(new Runnable() {
                     public void run() {            	                
                     		Toast.makeText(getActivity(), "글쓰기 성공!" , Toast.LENGTH_SHORT).show();	                              	
                     }
                 });            	            	
                Log.w("Facebook-Example", "JSON Error in response");
            } catch (FacebookError e) {                       	
            	
            	getActivity().runOnUiThread(new Runnable() {
                     public void run() {            	                
                     		Toast.makeText(getActivity(), "글쓰기 실패!" , Toast.LENGTH_SHORT).show();	                              	
                     }
                 });
                Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());                
            }           
        }
    }
	

}

