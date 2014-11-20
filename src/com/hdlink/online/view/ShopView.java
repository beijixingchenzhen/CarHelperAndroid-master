package com.hdlink.online.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.hdlink.online.R;

/**
 * Created by zhongqiling on 14-11-18.
 */
public class ShopView extends LinearLayout {

    public ShopView(Context context){
        super(context);

        View v = LayoutInflater.from(context).inflate(R.layout.shop_view, this);

    }


}
