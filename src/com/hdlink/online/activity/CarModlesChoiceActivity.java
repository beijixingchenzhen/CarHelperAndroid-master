package com.hdlink.online.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdlink.online.R;

/**
 * 车型选择
 * 
 * @author lVfb
 * 
 */
public class CarModlesChoiceActivity extends BaseActivity {

	private TextView map_txvTitle;
	private LinearLayout linearLayoutModels;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.car_models_choice);
		init();
		carModelsValue();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("车型选择");
	}

	/**
	 * 
	 */
	private void carModelsValue() {
		TextView[] textView = new TextView[10];
		linearLayoutModels = (LinearLayout) findViewById(R.id.linear_models_choice);
		String textName[] = { "2013款 A4L 1.6T2013款 A4L 1.6T2013款 A4L 1.6T2013款 A4L 1.6T2013款 A4L 1.6T2013款 A4L 1.6T", "2014款 A4L 1.8T",
				"2014款 A4L 1.9T", "2014款 A4L 2.0T", "2014款 A6L 2.8T",
				"2014款 A6L 2.8T", "2014款 A6L 2.8T", "2014款 A6L 2.8T",
				"2014款 A6L 2.8T", "2014款 A6L 2.8T"};
		for (int i = 0; i < 10; i++) {
			textView[i] = new TextView(this);
			textView[i].setText(textName[i]);
			textView[i].setTextColor(Color.parseColor("#000000")); 
			textView[i].setTextSize(20);
			textView[i].setGravity(Gravity.CENTER_VERTICAL);
			textView[i].setEllipsize(TruncateAt.MARQUEE);
			//添加背景图片
			textView[i].setBackgroundResource(R.drawable.choose_car_item_selector);
			linearLayoutModels.addView(textView[i]);
		}
	}
}
