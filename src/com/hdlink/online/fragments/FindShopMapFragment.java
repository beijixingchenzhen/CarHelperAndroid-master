package com.hdlink.online.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hdlink.online.R;
import com.hdlink.online.model.GlobalConfig;
import com.hdlink.online.view.ExpandTabView;
import com.hdlink.online.view.FilterTabPopView;

import java.util.ArrayList;

/**
 * Created by zhongqiling on 14-11-17.
 */
public class FindShopMapFragment extends BaseFragment{

    ExpandTabView expandTab = null;
    View mView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.findshop_map_frag, null);
        expandTab = (ExpandTabView)mView.findViewById(R.id.findservice_map_tab);

        ArrayList<String> tabnames = new ArrayList<String>();
        tabnames.add("洗车");
        tabnames.add("区域");

        ArrayList<View> tabViews = new ArrayList<View>();
        FilterTabPopView serviceCatotoryPopView = new FilterTabPopView(getActivity());
        serviceCatotoryPopView.init(GlobalConfig.shared().getFirstCatogoryNames(),
                GlobalConfig.shared().getSecondCategory());
        serviceCatotoryPopView.setSelectedListener(new FilterTabPopView.OnSelectedListener() {
            @Override
            public void onCall(String data) {
                expandTab.setTitle(data, 0);
                expandTab.close();
            }
        });

        tabViews.add(serviceCatotoryPopView);

        FilterTabPopView regionPopView = new FilterTabPopView(getActivity());
        regionPopView.init(GlobalConfig.shared().regions);
        regionPopView.setSelectedListener(new FilterTabPopView.OnSelectedListener() {
            @Override
            public void onCall(String data) {
                expandTab.setTitle(data, 1);
                expandTab.close();
            }
        });
        tabViews.add(regionPopView);

        expandTab.setValue(tabnames, tabViews);
        return v;
    }
}
