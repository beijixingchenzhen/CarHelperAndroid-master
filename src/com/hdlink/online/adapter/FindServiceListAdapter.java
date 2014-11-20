package com.hdlink.online.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.hdlink.online.R;
import com.hdlink.online.model.CarService;

import java.util.List;

/**
 * Created by zhongqiling on 14-11-6.
 */
public class FindServiceListAdapter extends BaseAdapter {
    public List<CarService> mList;
    private LayoutInflater layoutInflater;
    private Context context;

    public FindServiceListAdapter(Context context, List<CarService> mList) {
        this.context = context;
        this.mList = mList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {

        return mList.size();
    }

    @Override
    public Object getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.main_content_items, null);
            viewHolder.title_tv = (TextView) convertView.findViewById(R.id.title_tv);
            viewHolder.address_tv = (TextView) convertView.findViewById(R.id.address_tv);
            viewHolder.concrete_tv = (TextView) convertView.findViewById(R.id.concrete_tv);
            viewHolder.money_tv = (TextView) convertView.findViewById(R.id.money_tv);
            viewHolder.distance_tv = (TextView) convertView.findViewById(R.id.distance_tv);
            convertView.setTag(viewHolder);
        }else{

            viewHolder = (ViewHolder) convertView.getTag();
        }

        CarService service = mList.get(position);
        viewHolder.title_tv.setText(service.name);
        viewHolder.address_tv.setText("丹竹头大厦");
        viewHolder.money_tv.setText(String.valueOf(service.price));
        viewHolder.distance_tv.setText(String.valueOf(service.distance));

        return convertView;
    }

    public final class ViewHolder{

        private TextView title_tv,address_tv,concrete_tv,money_tv,distance_tv;
    }
}
