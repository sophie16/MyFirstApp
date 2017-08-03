package com.example.myfirstapp;

import android.media.Image;

/**
 * Created by Asus on 30-07-2017.
 */

public class DataObject {

    private String mText1;
    private String mText2;
    private String mImageURL;

    DataObject (String text1, String text2, String imageURL){
        mText1 = text1;
        mText2 = text2;
        mImageURL = imageURL;
    }


    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }

}
