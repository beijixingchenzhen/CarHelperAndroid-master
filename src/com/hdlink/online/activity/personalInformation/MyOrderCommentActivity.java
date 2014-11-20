package com.hdlink.online.activity.personalInformation;

import com.hdlink.online.R;
import com.hdlink.online.activity.personalInformation.Fragment.AllOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForConsumptionOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForEvaluationOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.NoPaymentOrderListFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 
 * @author tuwei
 * 
 *         更多分类
 * 
 */

public class MyOrderCommentActivity extends FragmentActivity {

	private Button back_btn, right_btn;
	private TextView map_txvTitle;
	private FragmentTabHost mTabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order_comment);
		init();
		setvalue();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("我的订单");
		right_btn=(Button) this.findViewById(R.id.right_btn);
		right_btn.setText("提交");
	}
	
	private void setvalue(){
		
	}
	
}
