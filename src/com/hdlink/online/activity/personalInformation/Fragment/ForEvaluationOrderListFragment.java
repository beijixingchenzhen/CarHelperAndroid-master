package com.hdlink.online.activity.personalInformation.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.hdlink.online.R;
import com.hdlink.online.activity.AllServiceActivity;
import com.hdlink.online.activity.FindServiceActivity;
import com.hdlink.online.activity.ServiceClassifyActivity;
import com.hdlink.online.view.MyGridView;
import com.hdlink.online.view.ViewFlow;

/**
 * 
 * @author tuwei
 * 
 */

public class ForEvaluationOrderListFragment extends Fragment {

	private View view;
	private LinearLayout linearLayout;
	private LinearLayout.LayoutParams layoutParam = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
	

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.my_all_order_list, null);
		linearLayout = (LinearLayout) view.findViewById(R.id.myallorderlist);
		
		
		for(int i=0;i<10;i++){
			setvalue();
		}
		setOn();
		return view;
	}

	private void setvalue() {
		LinearLayout linearLayout2=new LinearLayout(getActivity());
		linearLayout2.setBackgroundColor(Color.parseColor("#FFFFFF"));
		//服务图片
		ImageView imageView=new ImageView(getActivity());

		imageView.setImageResource(R.drawable.ic_launcher);
		
		imageView.setMinimumHeight(60);
		imageView.setMinimumWidth(60);
		
		//服务名字
		TextView serviceName=new TextView(getActivity());
		serviceName.setText("洁美洗车美容:精洗");
		serviceName.setTextSize(14);
		serviceName.setTextColor(Color.parseColor("#000000"));
		
		//金额数量
		TextView textView2=new TextView(getActivity());
		textView2.setText("金额：");
		textView2.setTextSize(14);
		textView2.setTextColor(Color.parseColor("#000000"));
		

		TextView textView3=new TextView(getActivity());
		textView3.setText("元");
		textView3.setTextSize(14);
		textView3.setTextColor(Color.parseColor("#000000"));
		

		TextView textView4=new TextView(getActivity());
		textView4.setText("50.0");
		textView4.setTextSize(18);
		textView4.setTextColor(Color.parseColor("#FF0000"));

		LinearLayout moneyName=new LinearLayout(getActivity());
		moneyName.addView(textView2);
		moneyName.addView(textView4);
		moneyName.addView(textView3);

		ImageView imageView2=new ImageView(getActivity());

		imageView2.setImageResource(R.drawable.qs_r11_c9);
		
		//中间
		LinearLayout linearLayoutln=new LinearLayout(getActivity());
		linearLayoutln.setOrientation(1);
		linearLayoutln.addView(serviceName);
		linearLayoutln.addView(moneyName);

		//左边
		LinearLayout linearLayoutleft=new LinearLayout(getActivity());
		linearLayoutleft.addView(imageView);
		linearLayoutleft.setGravity(Gravity.LEFT);

		//右边
		LinearLayout linearLayoutright=new LinearLayout(getActivity());
		linearLayoutright.addView(imageView2);
		linearLayoutright.setGravity(Gravity.RIGHT);
		
		
		linearLayout2.addView(linearLayoutleft);
		linearLayout2.addView(linearLayoutln);
		linearLayout2.addView(linearLayoutright);
		
		linearLayout2.setGravity(Gravity.CENTER);
		
		layoutParam.setMargins(0, 10, 0, 0);
		linearLayout2.setLayoutParams(layoutParam);
		
		
		linearLayout2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), FindServiceActivity.class);
				startActivity(intent);
			}
		});
		linearLayout.addView(linearLayout2);
	}

	private void setOn() {

		linearLayout = (LinearLayout) view.findViewById(R.id.myallorderlist);
		linearLayout.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View arg0) {
				// TODO Auto-generated method stub

				TextView textView = (TextView) view
						.findViewById(R.id.textView2);
				textView.setText("长按");
				return true;
			}
		});
	}

}
