package com.hdlink.online.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdlink.online.R;


public class RecommendFriendActivity extends Activity {
	
	private TextView linear_title;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recommend_friend);
		init();
	}
	private void init(){
		linear_title = (TextView)findViewById(R.id.map_txvTitle);
		linear_title.setText("推荐给好友");
	}
}
