package com.hdlink.online.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.hdlink.online.R;
import com.hdlink.online.activity.ServiceDetailActivity;
import com.hdlink.online.model.CarService;
import com.hdlink.online.model.GlobalConfig;
import com.hdlink.online.model.Shop;
import com.hdlink.online.view.ExpandTabView;
import com.hdlink.online.view.FilterTabPopView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongqiling on 14-11-17.
 */
public class FindShopListFragment extends BaseFragment{

    private ExpandTabView filterView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.findshop_list_frag, null);

        filterView = (ExpandTabView)v.findViewById(R.id.findshop_list_tab);

        ArrayList<String> tabText = new ArrayList<String>();
        tabText.add("全部分类");
        tabText.add("附近");
        tabText.add("默认排序");

        ArrayList<View> tabViews = new ArrayList<View>();
        FilterTabPopView serviceCatotoryPopView = new FilterTabPopView(getActivity());
        serviceCatotoryPopView.init(GlobalConfig.shared().getFirstCatogoryNames(),
                GlobalConfig.shared().getSecondCategory());
        serviceCatotoryPopView.setSelectedListener(new FilterTabPopView.OnSelectedListener() {
            @Override
            public void onCall(String data) {
                filterView.setTitle(data, 0);
                filterView.close();
            }
        });

        tabViews.add(serviceCatotoryPopView);

        FilterTabPopView regionPopView = new FilterTabPopView(getActivity());
        regionPopView.init(GlobalConfig.shared().regions);
        regionPopView.setSelectedListener(new FilterTabPopView.OnSelectedListener() {
            @Override
            public void onCall(String data) {
                filterView.setTitle(data, 1);
                filterView.close();
            }
        });
        tabViews.add(regionPopView);

        FilterTabPopView sortPopView = new FilterTabPopView(getActivity());
        sortPopView.init(GlobalConfig.shared().sortItems);
        sortPopView.setSelectedListener(new FilterTabPopView.OnSelectedListener() {
            @Override
            public void onCall(String data) {
                filterView.setTitle(data, 2);
                filterView.close();
            }
        });

        tabViews.add(sortPopView);

        filterView.setValue(tabText, tabViews);
        filterView.setOnButtonClickListener(new ExpandTabView.OnButtonClickListener() {
            @Override
            public void onClick(int selectPosition) {
                Log.e("click", "clck");
            }
        });

        List<Shop> shops = new ArrayList<Shop>();
        for(int i = 0; i < 15; i++){
            Shop shop = new Shop("洁美汽车美容", "福田区宝石路3号花园", "", 100+30*i);
        }

//        List<Map<String, Object>> adapterData = new ArrayList<Map<String, Object>>();
//        for(CarService service : services){
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("shopName", service.shopName);
//            map.put("price", service.price);
//            map.put("originPrice", service.originPrice);
//            map.put("distance", service.distance);
//            adapterData.add(map);
//        }
//        SimpleAdapter adapter = new SimpleAdapter(getActivity(), adapterData, R.layout.service_view,
//                new String[]{"shopName", "price", "originPrice", "distance"},
//                new int[]{R.id.service_view_shop_name, R.id.service_view_price,
//                        R.id.service_view_originPrice, R.id.service_view_distance});
//
//        ListView serviceList = (ListView)mView.findViewById(R.id.findservie_list);
//        serviceList.setAdapter(adapter);
//        serviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
//                intent.putExtra("serviceId", "1");
//                getActivity().startActivity(intent);
//            }
//        });


        return v;
    }
}
