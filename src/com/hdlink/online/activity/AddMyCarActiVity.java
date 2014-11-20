package com.hdlink.online.activity;

import com.hdlink.online.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddMyCarActiVity extends Activity{

	private TextView map_txvTitle;
	private LinearLayout addMyCarInfo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_mycar);
		init();
	}
	
	private void init(){
		map_txvTitle = (TextView)findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("我的愛车");
		addMyCarInfo = (LinearLayout)findViewById(R.id.add_mycar);
		addMyCarInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AddMyCarActiVity.this,AddMyCarInfoActivity.class);
				startActivity(intent);
				
			}
		});
	}

}
