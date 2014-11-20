package com.hdlink.online.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hdlink.online.R;
import com.hdlink.online.model.CarService;

/**
 * Created by zhongqiling on 14-11-13.
 */
public class ServiceView extends LinearLayout {

    private CarService mService = null;

    public ServiceView(Context context, CarService service){
        super(context);
        assert service != null;
        mService = service;

        View view = LayoutInflater.from(context).inflate(R.layout.service_view, this);
        ((TextView)view.findViewById(R.id.service_view_shop_name)).setText(service.shopName);
        ((TextView)view.findViewById(R.id.service_view_price)).setText(service.price);
        ((TextView)view.findViewById(R.id.service_view_originPrice)).setText(service.originPrice);
        ((TextView)view.findViewById(R.id.service_view_distance)).setText(service.distance);


    }




}
