package com.hdlink.online.fragments;

import com.hdlink.online.R;
import com.hdlink.online.activity.AddMyCarActiVity;
import com.hdlink.online.activity.PersonalInformationActivity;
import com.hdlink.online.activity.personalInformation.MyCollectListActivity;
import com.hdlink.online.activity.personalInformation.MyIntegralListActivity;
import com.hdlink.online.activity.personalInformation.MyOrderDetailsActivity;
import com.hdlink.online.activity.personalInformation.MyOrderListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hdlink.online.activity.BaseActivity;
import com.hdlink.online.R;
import com.hdlink.online.activity.AddMyCarActiVity;
import com.hdlink.online.activity.FindServiceActivity;
import com.hdlink.online.activity.SetUpActivity;

/**
 * 
 * @author tuwei
 * 
 */

public class MyProfileFragment extends BaseFragment implements OnClickListener{

	private View view;
	private TextView tv_login_hint;
	private TextView tv_login;
	private LinearLayout myIntegral;
	private LinearLayout myCar;
	private LinearLayout myOrder;
	private LinearLayout myCollect;
	private LinearLayout mySetting;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.tab_myprofile, null);
		findView();
		
		addListener();
		return view;
	}
	private void findView(){
		tv_login_hint=(TextView)view.findViewById(R.id.myself_login_hint);
		tv_login=(TextView)view.findViewById(R.id.myself_login_login);
		myIntegral=(LinearLayout)view.findViewById(R.id.myself_my_integral);
		myCar=(LinearLayout)view.findViewById(R.id.myself_my_car);
		myOrder=(LinearLayout)view.findViewById(R.id.myself_my_order);
		myCollect=(LinearLayout)view.findViewById(R.id.myself_my_collect);
		mySetting=(LinearLayout)view.findViewById(R.id.myself_my_setting);
	}
	private void addListener(){
		tv_login.setOnClickListener(this);
		myIntegral.setOnClickListener(this);
		myCar.setOnClickListener(this);
		myOrder.setOnClickListener(this);
		myCollect.setOnClickListener(this);
		mySetting.setOnClickListener(this);
	}
	private void setIntent(Class clas){
		startActivity(new Intent(getActivity(), clas));
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.myself_login_login://登陆
			break;

		case R.id.myself_my_integral:
			
			break;
		case R.id.myself_my_car:
			
			break;
		case R.id.myself_my_collect:
			Intent intent = new Intent(self.getActivity(), MyCollectListActivity.class);
            self.getActivity().startActivity(intent);
			break;
		case R.id.myself_my_order:
			
			break;
		case R.id.myself_my_setting:
			
			break;
		}
	}
}
