package com.hdlink.online.application;

import android.app.Application;


public class MyApplication extends Application {
	
    private static MyApplication mInstance = null;
    
	@Override
    public void onCreate() {
	    super.onCreate();
		mInstance = this;
	}
	
	public static MyApplication getInstance() {
		return mInstance;
	}
	
}
