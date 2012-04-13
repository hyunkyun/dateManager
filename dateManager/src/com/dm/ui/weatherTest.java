package com.dm.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dm.R;

/***
 * 
 * @author HyunKyun
 *
 */
public class weatherTest extends Fragment {
	 int mNum;
	 
    static weatherTest newInstance(int num) {
    	weatherTest f = new weatherTest();
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);
        return f;
    }
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.weather, container, false);
        LinearLayout l = (LinearLayout)v.findViewById(R.id.first);
        final TextView msg = (TextView)v.findViewById(R.id.message);
        
        l.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		msg.setText("first clicked");
        	}
        });
        
        LinearLayout l2 = (LinearLayout)v.findViewById(R.id.second);
        
        l2.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		msg.setText("second clicked");
        	}
        });
        
        LinearLayout l3 = (LinearLayout)v.findViewById(R.id.third);
        
        l3.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		Toast.makeText(getActivity(), "third", 500).show();
        		msg.setText("third clicked");
        	}
        });
        
        LinearLayout l4 = (LinearLayout)v.findViewById(R.id.forth);
        
        l4.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		msg.setText("forth clicked");
        	}
        });
        
        LinearLayout l5= (LinearLayout)v.findViewById(R.id.fifth);
        
        l5.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		msg.setText("fifth clicked");
        	}
        });
        
        LinearLayout l6= (LinearLayout)v.findViewById(R.id.sixth);
        
        l6.setOnClickListener(new View.OnClickListener(){
        	public void onClick(View v){
        		msg.setText("sixth clicked");
        	}
        });
        return v;
    }
}