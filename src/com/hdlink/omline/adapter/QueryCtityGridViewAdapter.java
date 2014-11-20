package com.hdlink.omline.adapter;

import java.util.List;

import com.hdlink.online.R;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QueryCtityGridViewAdapter extends BaseAdapter{
  
	private List<String> data;
	
	public QueryCtityGridViewAdapter(List<String> data){
		this.data=data;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressLint({ "InflateParams", "ViewHolder" }) @Override
	public View getView(int position, View convertView, ViewGroup parent) {
	View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.query_query_city_grid_item,null);
	TextView car_name=(TextView) view.findViewById(R.id.tv_car_name);	
	car_name.setText(data.get(position));
	return view;
	}

}
