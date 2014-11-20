package com.hdlink.online.fragments;

import java.util.ArrayList;
import java.util.List;

import com.hdlink.online.adapter.GridViewAdapter;
import com.hdlink.online.R;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi") public class CarPlateFragment extends Fragment implements OnClickListener, OnItemClickListener {
 
	private LinearLayout linearLayout;
	private TextView car_p;
	private GridView gridView;
	private List<String> car_name=new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	    for (int i = 0; i < 20; i++) {
	    	car_name.add("��"+i);
		}
		View view=inflater.inflate(R.layout.car_plate_fragment, null);
		car_p=(TextView) view.findViewById(R.id.car_p);
		linearLayout=(LinearLayout) view.findViewById(R.id.lLayout);
		car_p.setOnClickListener(this);
		return view;
	}
	@Override
	public void onClick(View v) { 
		 switch (v.getId()) {
		case R.id.car_p:
			linearLayout.setVisibility(View.VISIBLE);
			gridView=(GridView) linearLayout.findViewById(R.id.gv);
			gridView.setOnItemClickListener(this);
			gridView.setAdapter(new GridViewAdapter(car_name));
			break;
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		    Toast.makeText(getActivity(), car_name.get(position), 0).show();
		    car_p.setText(car_name.get(position));
		    linearLayout.setVisibility(View.GONE);
	}

}
 