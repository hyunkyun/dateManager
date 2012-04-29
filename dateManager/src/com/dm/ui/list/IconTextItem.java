package com.dm.ui.list;
 
import android.graphics.drawable.Drawable;
 
public class IconTextItem {

    private Drawable mIcon;
    private String[] mData;
 
    private boolean mSelectable = true;
 
    public IconTextItem(Drawable icon, String[] obj) {
        mIcon = icon;
        mData = obj;
    }

    public IconTextItem(Drawable icon, String obj01, String obj02) {
        mIcon = icon;
        mData = new String[2];
        mData[0] = obj01;
        mData[1] = obj02;
    }

    public boolean isSelectable() {
        return mSelectable;
    }

    public void setSelectable(boolean selectable) {
        mSelectable = selectable;
    }
 
    public String[] getData() {
        return mData;
    }
 
    public String getData(int index) {
        if (mData == null || index >= mData.length) {
            return null;
        }
         
        return mData[index];
    }
     
    public void setData(String[] obj) {
        mData = obj;
    }
 
    public void setIcon(Drawable icon) {
        mIcon = icon;
    }
 
    public Drawable getIcon() {
        return mIcon;
    }
 
    public int compareTo(IconTextItem other) {
        if (mData != null) {
            String[] otherData = other.getData();
            if (mData.length == otherData.length) {
                for (int i = 0; i < mData.length; i++) {
                    if (!mData[i].equals(otherData[i])) {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        } else {
            throw new IllegalArgumentException();
        }
         
        return 0;
    }

}