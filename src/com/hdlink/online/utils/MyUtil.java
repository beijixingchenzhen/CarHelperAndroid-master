package com.hdlink.online.utils;

import android.content.Context;
import android.widget.Toast;

public class MyUtil {
	public static void showToast(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static boolean isNull(String str){
		if(str==null||"null".equalsIgnoreCase(str)||"".equals(str)){
			return true;
		}else{
			return false;
		}
	}
}
