package com.hdlink.online.activity;

import java.util.ArrayList;

import com.hdlink.online.R;
import com.hdlink.online.model.ServiceClassifyModel;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ServiceClassifyActivity extends BaseActivity {

	private TextView map_txvTitle;
	private String[] testName = { "日历", "播放器", "游戏", "清理大师", "跑酷", "壁纸",
			"单机斗地主" };
	private LinearLayout linearLayout1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_classify);
		init();
		getData();
		setValue();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("更多分类");
	}

	private void getData() {
		ServiceClassifyModel classifyModel=new ServiceClassifyModel();
		classifyModel.setId("1");
		classifyModel.setName("2");
		
		
		String[] testName = { "日历", "播放器", "游戏", "清理大师", "跑酷", "壁纸", "单机斗地主" };
		this.testName = testName;
	}

	private void setValue() {
		TextView[] test = new TextView[6];
		ImageView[] imageViews = new ImageView[6];
		linearLayout1 = (LinearLayout) findViewById(R.id.item);
		for (int i = 0; i < 6; i++) {
			LinearLayout layout = new LinearLayout(this);
			// 设置文本
			test[i] = new TextView(this);
			test[i].setText("I=" + i);// 设置文本
			test[i].setId(i);
			test[i].setTextSize(30); // 设置文字大小
			test[i].setTextColor(Color.parseColor("#000000"));// 设置文字颜色

			imageViews[i] = new ImageView(this);
			imageViews[i].setImageResource(R.drawable.ic_launcher);
			// 设置图片

			layout.addView(imageViews[i]);
			layout.addView(test[i]);
			linearLayout1.addView(layout);
			setitems();
		}
	}

	private void setitems() {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
		layoutParams.setMargins(10, 3, 10, 3);
		ArrayList<Button> childBtns = new ArrayList<Button>();
		int size = testName.length;
		Button[] button = new Button[size];
		int totoalBtns = 0;
		for (int j = 0; j < size; j++) {
			button[j] = (Button) LayoutInflater.from(this).inflate(
					R.layout.assist_item_button, null);
			LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			int length = testName[j].length();

			// 判断字段长度，根据字段长度写按钮长度
			if (length < 3) {
				itemParams.weight = 1;
				totoalBtns++;
			} else if (length < 5) {
				itemParams.weight = 2;
				totoalBtns += 2;
			} else {
				itemParams.weight = 3;
				totoalBtns += 3;
			}
			button[j].setText(testName[j]);
			button[j].setId(j);
			button[j].setTextColor(Color.WHITE);
			
			setclike(button[j]);
			
			childBtns.add(button[j]);
			if (totoalBtns >= 5) {
				LinearLayout horizLL = new LinearLayout(this);
				horizLL.setOrientation(LinearLayout.HORIZONTAL);
				horizLL.setLayoutParams(layoutParams);

				for (Button addBtn : childBtns) {
					horizLL.addView(addBtn);
				}
				linearLayout1.addView(horizLL);
				childBtns.clear();
				totoalBtns = 0;
			}

		}
		// 循环完成后，把剩余的加进去
		if (!childBtns.isEmpty()) {
			LinearLayout horizLL = new LinearLayout(this);
			horizLL.setOrientation(LinearLayout.HORIZONTAL);
			horizLL.setLayoutParams(layoutParams);

			for (Button addBtn : childBtns) {
				horizLL.addView(addBtn);
			}
			linearLayout1.addView(horizLL);
			childBtns.clear();
			totoalBtns = 0;
		}
	}
	
	private void setclike(Button button){
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int i = (Integer) v.getId();
				System.out.println("id===="+i);
				Intent intent = new Intent(ServiceClassifyActivity.this, FindServiceActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
