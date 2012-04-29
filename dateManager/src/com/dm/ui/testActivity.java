package com.dm.ui;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dm.R;
import com.dm.service.db.CategoryDAO;
import com.dm.service.db.CategoryDAO.Category;

/***
 * 
 * @author HyunKyun
 *
 */
public class testActivity extends Fragment {
	public static String TAG = "testActivity";
	 int mNum;
	 
    static testActivity newInstance(int num) {
    	testActivity f = new testActivity();
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
        View v = inflater.inflate(R.layout.testlayout, container, false);
        TextView msg = (TextView)v.findViewById(R.id.message);
        

       // View tv = v.findViewById(R.id.text);
        //((TextView)tv).setText("Fragment #" + 1);
       // tv.setBackgroundDrawable(getResources().getDrawable(android.R.drawable.gallery_thumb));
        
        //to test database access with LogCat
        
        CategoryDAO test = new CategoryDAO(getActivity());
        Category c = new Category(-1, "ㅎㅎㅎ");
        //test.deleteCategory(6);
        msg.setText("" + test.insertCategory(c));
        
        /*if( test.insertCategory(c) == false)
        	msg.setText("입력에 실패하였습니다.");
        else{*/
        
        	List<Category> temp = test.getCategory();
        	
        	for(int i = 0 ; i < temp.size(); i++)
        	{
        		Category itr = temp.get(i);
        		msg.append(" id : "+ itr.getId() + ", name : " + itr.getName() + "\n");
        		
        	}
        //}
        	
        
        test.logListInfo(test.getCategory());
        return v;
    }
}