package com.hdlink.online.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdlink.online.R;

/**
 * 
 * @author tuwei
 * 
 */

public class CarBrandActivity extends FragmentActivity {

	/*
	 * private TableRow tableRow = null; private ImageView iv = null; private
	 * TextView text_title;
	 */
	private LinearLayout linerarLayoutBrandIcon;

	// 閫夋嫨姹借溅鍝佺墝鍒楄〃
	private LinearLayout linerarLayoutBrandItem;

	// 设置标题
	private TextView map_txvTitle;

	@Override
	public void onCreate(Bundle arg0) {

		super.onCreate(arg0);
		setContentView(R.layout.car_brand_choice);

		init();
		addHotModels();
		carBrandChoice();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("鍝佺墝閫夋嫨");
	}

	private void addHotModels() {
		ImageView[] iViewS = new ImageView[7];
		linerarLayoutBrandIcon = (LinearLayout) findViewById(R.id.brand_icon);
		for (int i = 0; i < 7; i++) {
			iViewS[i] = new ImageView(this);
			iViewS[i].setImageResource(R.drawable.ic_launcher);
			linerarLayoutBrandIcon.addView(iViewS[i]);
		}
	}

	private void carBrandChoice() {
		TextView[] text = new TextView[6];
		String[] testName = { "A", "B", "C", "D", "E", "F", "G" };
		linerarLayoutBrandItem = (LinearLayout) findViewById(R.id.brand_item);
		for (int i = 0; i < 6; i++) {
			// 璁剧疆鏂囨湰
			text[i] = new TextView(this);
			text[i].setText(testName[i]);
			text[i].setTextColor(Color.parseColor("#BCBBBB"));
			text[i].setId(i);

			linerarLayoutBrandItem.addView(text[i]);
			carBrandChoiceValue();
		}

	}

	private void carBrandChoiceValue() {
		TextView[] text = new TextView[6];
		ImageView[] imageViews = new ImageView[6];
		linerarLayoutBrandItem = (LinearLayout) findViewById(R.id.brand_item);
		String[] testName = { "濂ヨ开", "瀹濋┈", "闀垮煄", "甯濊豹", "绂忕壒", "GMC", "鎮嶉┈" };
		for (int i = 0; i < 6; i++) {
			// 鍒涘缓绾挎�у竷灞�
			LinearLayout layout = new LinearLayout(this);

			// 璁剧疆鏂囨湰
			text[i] = new TextView(this);
			text[i].setText(testName[i]);
			text[i].setGravity(Gravity.CENTER_VERTICAL);
			text[i].setTextColor(Color.parseColor("#000000"));
			text[i].setId(i);
			carLineIntent(text[i]);
			// 璁剧疆鍥剧墖
			imageViews[i] = new ImageView(this);
			imageViews[i].setImageResource(R.drawable.ic_launcher);

			layout.setBackgroundColor(Color.parseColor("#FFFFFF"));
			layout.addView(imageViews[i]);
			layout.addView(text[i]);
			linerarLayoutBrandItem.addView(layout);

		}
	}

	private void carLineIntent(TextView tv) {
		tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int id = v.getId();
				Intent intent = new Intent(CarBrandActivity.this,
						CarLineSelectionActivity.class);
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
