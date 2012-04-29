/*
 * Copyright 2010 Facebook, Inc.

 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dm.content.facebook;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.dm.R;
import com.dm.content.facebook.SessionEvents.AuthListener;
import com.dm.content.facebook.SessionEvents.LogoutListener;
import com.dm.content.facebook.lib.AsyncFacebookRunner;
import com.dm.content.facebook.lib.DialogError;
import com.dm.content.facebook.lib.Facebook;
import com.dm.content.facebook.lib.FacebookError;
import com.dm.content.facebook.lib.Facebook.DialogListener;
import com.dm.ui.dialog.AlertFragment;

public class LoginButton extends ImageButton {

	private Facebook mFb;
	private Handler mHandler;
	private SessionListener mSessionListener = new SessionListener();
	private String[] mPermissions;
	private Activity mActivity;

	public boolean IsAuth = false;

	public LoginButton(Context context) {
		super(context);
	}

	public LoginButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LoginButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void init(final Activity activity, final Facebook fb) {
		init(activity, fb, new String[] {});
	}

	public void init(final Activity activity, final Facebook fb,
			final String[] permissions) {
		mActivity = activity;
		mFb = fb;
		mPermissions = permissions;

		mHandler = new Handler();
		setAdjustViewBounds(true);
		setImageResource(fb.isSessionValid() ?  R.drawable.logout_button : R.drawable.login_button);
		drawableStateChanged();
		SessionEvents.addAuthListener(mSessionListener);
		SessionEvents.addLogoutListener(mSessionListener);

		this.setOnClickListener(new ButtonOnClickListener());

		Log.e("DAY","END!!");
	}

	private final class ButtonOnClickListener implements OnClickListener {            	
		public void onClick(View v) {
			Log.e("DAY","CLICK!!");
			showDialog();
		}
	}

	private final class LoginDialogListener implements DialogListener {
		public void onComplete(Bundle values) {
			SessionEvents.onLoginSuccess();
		}

		public void onFacebookError(FacebookError error) {
			SessionEvents.onLoginError(error.getMessage());
		}

		public void onError(DialogError error) {
			SessionEvents.onLoginError(error.getMessage());
		}

		public void onCancel() {
			SessionEvents.onLoginError("Action Canceled");                           
		}
	}

	private class LogoutRequestListener extends BaseRequestListener {
		public void onComplete(String response, final Object state) {
			// callback should be run in the original thread, 
			// not the background thread
			mHandler.post(new Runnable() {
				public void run() {
					SessionEvents.onLogoutFinish();
				}
			});
		}
	}

	private class SessionListener implements AuthListener, LogoutListener {
		public void onAuthSucceed() {
			setImageResource(R.drawable.logout_button);
			SessionStore.save(mFb, getContext());
		}
		public void onAuthFail(String error) {
		}

		public void onLogoutBegin() {           
		}

		public void onLogoutFinish() {
			SessionStore.clear(getContext());
			setImageResource(R.drawable.login_button);
		}
	}    

	void showDialog() {
		Builder d = new Builder(mActivity)
		.setTitle(R.string.alert_facebook_title)
		.setMessage(R.string.alert_facebook_content)
		.setPositiveButton(R.string.dialog_agree, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				if (mFb.isSessionValid()) {
					SessionEvents.onLogoutBegin();
					AsyncFacebookRunner asyncRunner = new AsyncFacebookRunner(mFb);
					asyncRunner.logout(getContext(), new LogoutRequestListener());
				} else {
					mFb.authorize(mActivity, mPermissions, new LoginDialogListener());
				}
			}
		})
		.setNegativeButton(R.string.dialog_disagree, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
			}
		} );
		//DialogFragment newFragment = new AlertFragment(d);
		//newFragment.show(mActivity.getFragmentManager(), "dialog");
	}
}
