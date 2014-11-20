package com.hdlink.online.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdlink.online.R;

public class CarLineSelectionActivity extends BaseActivity {

	private LinearLayout linearLayout1;
	private TextView map_txvTitle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_line_selection);

		init();
		setValue();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("车系选择");
	}

	private void setValue() {
		TextView[] test = new TextView[6];
		ImageView[] imageViews = new ImageView[6];
		linearLayout1 = (LinearLayout) findViewById(R.id.car_line_item);
		String[] testName = { "一汽奥迪", "进口奥迪", "奥迪S系列", "清理大师", "跑酷", "壁纸",
				"单机斗地主" };
		for (int i = 0; i < 6; i++) {
			LinearLayout layout = new LinearLayout(this);
			// 设置文本
			test[i] = new TextView(this);
			test[i].setText(testName[i]);// 设置文本
			test[i].setId(i);
			test[i].setHeight(50);
			test[i].setGravity(Gravity.CENTER_VERTICAL);
			test[i].setTextColor(Color.parseColor("#999999"));// 设置文字颜色

			imageViews[i] = new ImageView(this);
			// 设置图片
			layout.addView(test[i]);
			layout.setBackgroundColor(Color.parseColor("#d5d5d5"));
			linearLayout1.addView(layout);

			setValueInfo();
		}
	}

	private void setValueInfo() {
		TextView[] test = new TextView[6];
		String[] brandName = { "A4L", "A6L", "Q5", "Q6", "Q7", "Q8", "A78" };
		for (int i = 0; i < 6; i++) {
			LinearLayout layout = new LinearLayout(this);
			// 设置文本
			test[i] = new TextView(this);
			test[i].setText(brandName[i]);// 设置文本
			test[i].setId(i);
			test[i].setHeight(30);
			test[i].setTextSize(20);
			test[i].setGravity(Gravity.CENTER_VERTICAL);
			test[i].setTextColor(Color.parseColor("#000000"));// 设置文字颜色
			setOnclickIntent(layout);
			
			//添加背景图片
			test[i].setBackgroundResource(R.drawable.choose_car_item_selector);
			
			layout.addView(test[i]);
			linearLayout1.addView(layout);

		}
	}
	
	private void setOnclickIntent(LinearLayout tv) {
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(CarLineSelectionActivity.this,
						CarModlesChoiceActivity.class);
				startActivity(intent);
			}
		});
	}
}
