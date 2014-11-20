package com.hdlink.online.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.hdlink.online.R;

public class SetUpActivity extends Activity {

	private TextView tv_title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_up);
		init();
	}

	private void init() {
		tv_title = (TextView) findViewById(R.id.map_txvTitle);
		tv_title.setText("设置");
		//推荐好友
		TextView linearRecommend = (TextView) findViewById(R.id.recommend_friend);
		linearRecommend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SetUpActivity.this,
						RecommendFriendActivity.class);
				startActivity(intent);
			}
		});
		
		//建议反馈
		TextView linearProposeFeedback = (TextView)findViewById(R.id.proposed_feedback);
		linearProposeFeedback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SetUpActivity.this,
						ProposedFeedback.class);
				startActivity(intent);
			}
		});
		
		//关于我们
		TextView linearReagardingOur = (TextView)findViewById(R.id.regarding_our);
		linearReagardingOur.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(SetUpActivity.this,
						RegardingOur.class);
				startActivity(intent);
			}
		});
	}
}
