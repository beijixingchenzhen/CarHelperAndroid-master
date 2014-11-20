package com.hdlink.online.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.hdlink.online.BuildConfig;
import com.hdlink.online.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongqiling on 14-11-11.
 */
public class FilterTabPopView extends LinearLayout{

    public interface OnSelectedListener{
        public void onCall(String data);
    }

    private Context mContext = null;
    private OnSelectedListener mListener = null;
    //当前要选择的data，当二级目录为子级的数据
    private List<String> mData = new ArrayList<String>();
    //二级页面时候指代父级
    private List<String> mParData = null;
    private List<List<String>> mSubData = null;

    private ListView mListView = null;
    private ArrayAdapter mListAdapter = null;
    private ListView mParListView = null;

    private ArrayAdapter mSubListAdapter = null;

    public FilterTabPopView(Context context){
        super(context);
        mContext = context;
    }

    public FilterTabPopView(Context context, AttributeSet attrs, int defStyle){
        super(context);
        mContext = context;
    }

    public FilterTabPopView(Context context, AttributeSet attrs){
        super(context);
        mContext = context;
    }

    public void init(List<String> data){
        init(data, null);
    }


    public void init(List<String> data, List<List<String>> subData){

//        int layoutId = R.layout.findservice_pop_view_2;
        int layoutId = subData == null ? R.layout.findservice_pop_view : R.layout.findservice_pop_view_2;

        View v = LayoutInflater.from(mContext).inflate(layoutId, this);

        mData = subData == null ? data : null;
        if(subData == null){
            mData = data;
        }else{
            mParData = data;
            mSubData = subData;

            mParListView = (ListView)v.findViewById(R.id.filter_tab_pop_par);
            mParListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    set(position);
                }
            });
        }

        mListView = (ListView)v.findViewById(R.id.filter_tab_pop_list);
        mListView.setAdapter(createAdapter(data));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                select(position);
            }
        });


        if(subData != null){
            mParListView = (ListView)v.findViewById(R.id.filter_tab_pop_par);
            mParListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    set(position);
                }
            });
        }


//        Button btn = (Button)findViewById(R.id.btn);
//        btn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("a", "a");
//            }
//        });
    }

    public void setSelectedListener(OnSelectedListener listener){
        mListener = listener;
    }

    private BaseAdapter createAdapter(List<String> data){
//        TextAdapter adapter = new TextAdapter(getContext(), data, R.drawable.choose_item_selected, R.drawable.choose_eara_item_selector);
//        adapter.setTextSize(17);
//        adapter.setSelectedPositionNoNotify(0);

        List<Map<String, Object>> adapterData = new ArrayList<Map<String, Object>>();
        for(String item : data){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("text", item);
            adapterData.add(map);
        }

        return new SimpleAdapter(getContext(), adapterData, R.layout.findservice_pop_list_item,
                                                new String[]{"text"}, new int[]{R.id.find_service_pop_item_text});

    }


    //设置第一个list的当前选中位置
    public void set(int position){
        int total = mParData.size();
        if(position < total && position >= 0){
            mListView.setAdapter(createAdapter(mSubData.get(position)));
        }
    }

    public void select(int position){
        String data = position >=0 && position < mData.size() ? mData.get(position) : "";
        mListener.onCall(data);
    }

}
