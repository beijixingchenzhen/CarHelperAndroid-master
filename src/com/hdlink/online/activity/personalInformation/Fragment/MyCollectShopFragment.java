package com.hdlink.online.activity.personalInformation.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hdlink.online.R;
import com.hdlink.online.activity.AllServiceActivity;
import com.hdlink.online.activity.FindServiceActivity;
import com.hdlink.online.activity.PersonalInformationActivity;
import com.hdlink.online.activity.ServiceClassifyActivity;
import com.hdlink.online.activity.personalInformation.MyOrderDetailsActivity;
import com.hdlink.online.activity.personalInformation.PersonalUpdatePasswordActivity;
import com.hdlink.online.view.MyGridView;
import com.hdlink.online.view.ViewFlow;

/**
 * 
 * @author cz
 *
 */

public class MyCollectShopFragment extends Fragment {

	private View view;
	private ListView listView;
	private LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(
			LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.my_all_order_list, null);
		listView = (ListView) view.findViewById(R.id.myallorderlist);

		setvalue();

		return view;
	}

	private void setvalue() {
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("collectshopname", "门店名称：：：：");// 图像资源的ID
			listItem.add(map);
		}

		SimpleAdapter listItemAdapter = new SimpleAdapter(getActivity(),
				listItem,// 数据源
				R.layout.my_collect_shop,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "collectshopname" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.collect_shop_name });

		listView.setAdapter(listItemAdapter);
		listView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView adapter, View view, int position,
			long id) {
				Intent intent = new Intent(getActivity(), MyOrderDetailsActivity.class);
				startActivity(intent);
			}
		});
		
	}

}
