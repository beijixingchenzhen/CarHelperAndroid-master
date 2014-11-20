package com.hdlink.online.activity.personalInformation;

import com.hdlink.online.R;
import com.hdlink.online.activity.BaseActivity;
import com.hdlink.online.activity.PersonalInformationActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author tuwei
 * 
 *         更多分类
 * 
 */

public class PersonalUpdatePasswordActivity extends BaseActivity {

	private Button back_btn,right_btn;
	private TextView map_txvTitle;
	private GridView beauty_gd;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_update_password);
		init();
	}



	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("修改密码");
	}
	

}
