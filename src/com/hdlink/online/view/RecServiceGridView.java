package com.hdlink.online.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import com.hdlink.online.R;
import com.hdlink.online.model.GlobalConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongqiling on 14-11-10.
 */
public class RecServiceGridView extends GridView {

    public interface OnItemClick{ void onCall(String id); };
    public interface OnMoreClick{ void onCall(); };


    private OnItemClick onItemClickCall = null;
    private OnMoreClick onMoreClickCall = null;
    private List<String> mServices = null;

    public RecServiceGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecServiceGridView(Context context) {
        super(context);
    }

    public RecServiceGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(){
        setSelector(new ColorDrawable(Color.TRANSPARENT));
        //初始化推荐服务
        setRecomendServices(GlobalConfig.shared().getRecomendServiceList());
    }

    public void setItemClickLisener(OnItemClick callBack){
        onItemClickCall = callBack;
    }

    public void setMoreClickLisener(OnMoreClick callBack){
        onMoreClickCall = callBack;
    }

    public void setRecomendServices(List<String> recomendServices){

        int recomendSize = recomendServices.size();
        mServices = recomendSize <= 7 ? recomendServices : recomendServices.subList(0, 7);

        String[] from = { "item3", "item4" };
        int[] to = { R.id.ItemImage, R.id.ItemText };

        String[] serviceNames = new String[recomendSize];
        int[] logoSource = new int[recomendSize];

        for(int i = 0; i < recomendSize; i++){
            String serviceName = mServices.get(i);
            serviceNames[i] = GlobalConfig.shared().getServiceName(serviceName);
            logoSource[i] = GlobalConfig.shared().getServiceLogo(serviceName);
        }

        List<Map<String, Object>> gridData = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < serviceNames.length; i++) {

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put(from[0], logoSource[i]);
            map.put(from[1], serviceNames[i]);
            gridData.add(map);
        }

        SimpleAdapter adapter2 = new SimpleAdapter(getContext(), gridData,
                R.layout.homepage_grid_item_view, from, to);
        setAdapter(adapter2);
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position < 7)
                    onItemClickCall.onCall(mServices.get(position));
                else
                    onMoreClickCall.onCall();
            }
        });
    }

}
