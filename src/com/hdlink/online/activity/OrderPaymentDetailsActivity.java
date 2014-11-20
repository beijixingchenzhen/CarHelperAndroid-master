package com.hdlink.online.activity;

import com.hdlink.online.R;
import com.hdlink.online.activity.personalInformation.MyOrderDetailsActivity;
import com.hdlink.online.activity.personalInformation.Fragment.AllOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForConsumptionOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForEvaluationOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.NoPaymentOrderListFragment;

import android.content.Intent;
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

public class OrderPaymentDetailsActivity extends FragmentActivity {

	private Button back_btn, right_btn;
	private TextView map_txvTitle;
	private FragmentTabHost mTabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_payment_details);
		init();
		setOnclick();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("订单支付");
	}
	
	private void setOnclick(){
//		myorderdetailsbutton
		Button button = (Button) this.findViewById(R.id.myorderdetailsbutton);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(OrderPaymentDetailsActivity.this, OrderPaymentSuccessActivity.class);
				startActivity(intent);
			}
		});
	}

	


}
