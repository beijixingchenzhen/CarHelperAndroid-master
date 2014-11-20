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

public class PersonalUpdateUsernameActivity extends BaseActivity {

	private Button back_btn,right_btn;
	private TextView map_txvTitle;
	private GridView beauty_gd;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_update_username);
		init();
		setclike();
	}



	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("昵称");
		right_btn = (Button) this.findViewById(R.id.right_btn);
		right_btn.setVisibility(View.VISIBLE);
		right_btn.setText("保存");
	}
	
	private void setclike(){
		
		//点击叉图片，情况文字
		ImageView gridView=(ImageView) this.findViewById(R.id.updateusernameimg1);
		final EditText editText=(EditText) this.findViewById(R.id.updateusernametext1);
		gridView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				editText.setText("");
			}
		});
	}
	

}
