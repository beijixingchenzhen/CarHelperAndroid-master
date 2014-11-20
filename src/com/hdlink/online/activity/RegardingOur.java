package com.hdlink.online.activity;

import com.hdlink.online.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RegardingOur extends Activity {
	private TextView tv_title;
	private TextView tv_summary;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regarding_our);
		init();
	}

	private void init() {
		tv_title = (TextView) findViewById(R.id.map_txvTitle);
		tv_title.setText("关于我们");

		tv_summary = (TextView) findViewById(R.id.summary);
		tv_summary
				.setText("91车助手是深圳市海德在线有限公司...");
	}
}
