package com.hdlink.online.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.hdlink.online.R;

public class ProposedFeedback extends Activity{
	
	private TextView tv_title;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proposed_feedback);
		init();
	}
	
	private void init(){
		tv_title = (TextView)findViewById(R.id.map_txvTitle);
		tv_title.setText("建议反馈");
	}
}
