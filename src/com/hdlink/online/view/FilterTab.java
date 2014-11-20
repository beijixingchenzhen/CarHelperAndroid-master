package com.hdlink.online.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * Created by zhongqiling on 14-11-10.
 */
public class FilterTab extends LinearLayout{

    private String tabname = "";

    public FilterTab(Context context, String name){
        super(context);
        tabname = name;
    }


    public void init(Context context){
//        LayoutInflater.from(context).inflate(, this);
    }
}
