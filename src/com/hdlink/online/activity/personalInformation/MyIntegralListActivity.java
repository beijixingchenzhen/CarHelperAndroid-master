package com.hdlink.online.activity.personalInformation;

import java.util.ArrayList;
import java.util.HashMap;

import com.hdlink.online.R;
import com.hdlink.online.activity.personalInformation.Fragment.AllOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForConsumptionOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForEvaluationOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.NoPaymentOrderListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author tuwei
 * 
 *         更多分类
 * 
 */

public class MyIntegralListActivity extends FragmentActivity {

	private Button back_btn, right_btn;
	private TextView map_txvTitle;
	private FragmentTabHost mTabHost;
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_integral_list);
		init();
		setvalue();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("我的积分");
		right_btn=(Button) this.findViewById(R.id.right_btn);
		right_btn.setText("积分说明");
		listView = (ListView) this.findViewById(R.id.myallintegrallist);
	}
	
	private void setvalue() {
		ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < 10; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("textfen", "-"+(i+2)+"分");// 图像资源的ID
			listItem.add(map);
		}

		SimpleAdapter listItemAdapter = new SimpleAdapter(MyIntegralListActivity.this,
				listItem,// 数据源
				R.layout.my_integral_list_assist,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "textfen" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.textfen });

		listView.setAdapter(listItemAdapter);
		
	}
}
