package com.hdlink.online.activity;


import com.hdlink.online.R;
import com.hdlink.online.activity.personalInformation.PersonalUpdatePasswordActivity;
import com.hdlink.online.activity.personalInformation.PersonalUpdateUsernameActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonalInformationActivity extends BaseActivity {


	private View view;
	private Button back_btn;
	private TextView map_txvTitle;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_information);
//		init();
//		setclike();
	}

	private void init() {
		back_btn = (Button) this.findViewById(R.id.back_btn);
		back_btn.setVisibility(View.VISIBLE);
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("个人信息");
	}

	private void setclike(){
		RelativeLayout gridView=(RelativeLayout) this.findViewById(R.id.personaUserNameLayout);
		gridView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(PersonalInformationActivity.this, PersonalUpdateUsernameActivity.class);
				startActivity(intent);
			}
		});
		
		gridView=(RelativeLayout) this.findViewById(R.id.personaPasswordLayout);
		gridView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(PersonalInformationActivity.this, PersonalUpdatePasswordActivity.class);
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
