package com.hdlink.online.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hdlink.online.R;
import com.hdlink.online.bean.Content;
import com.hdlink.online.bean.Title;
import com.hdlink.online.utils.SharedPreferenceHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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

public class AllServiceActivity extends BaseActivity {

	private Button back_btn;
	private TextView map_txvTitle;
	private GridView beauty_gd;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_allservice);

		init();
	}

	private void init() {

		back_btn = (Button) this.findViewById(R.id.back_btn);
		back_btn.setVisibility(View.VISIBLE);
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("更多分类");

		beauty_gd = (GridView) this.findViewById(R.id.beauty_gd);
		//mGInitAdapter();
		
		String res = SharedPreferenceHelper.readDataFromSD();
		
//		String gson = new Gson().toJson(res);
//
//		System.out.println("res=====>" + res);
//		System.out.println("gson=====>" + gson);
//		
//		List<Title<Content>> resjson = new Gson().fromJson(res,
//				new TypeToken<List<Title<Content>>>() {
//				}.getType());

	}

	/*private void mGInitAdapter() {

		List<Map<String, Object>> data2 = new ArrayList<Map<String, Object>>();

		String[] from = { "item4" };
		int[] to = { R.id.ItemText };

		String[] text = { "洗车123", "精洗", "空调护理", "抛光打蜡", "漆面镀膜", "漆面镀晶", "美容套餐" };

		for (int i = 0; i < resjson.get(0).getSub().size(); i++) {

			HashMap<String, Object> map2 = new HashMap<String, Object>();
			map2.put(from[0], resjson.get(0).getSub().get(i).getName());
			data2.add(map2);
		}

		SimpleAdapter adapter2 = new SimpleAdapter(this, data2,
				R.layout.beauty_gd_items, from, to);
		beauty_gd.setAdapter(adapter2);
		beauty_gd.setOnItemClickListener(bGlistener);
	}*/

	private OnItemClickListener bGlistener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

		}
	};

}
