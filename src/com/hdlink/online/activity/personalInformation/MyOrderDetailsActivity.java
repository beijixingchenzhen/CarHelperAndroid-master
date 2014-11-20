package com.hdlink.online.activity.personalInformation;

import com.hdlink.online.R;
import com.hdlink.online.activity.OrderPaymentDetailsActivity;
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

public class MyOrderDetailsActivity extends FragmentActivity {

	private Button back_btn, right_btn;
	private TextView map_txvTitle;
	private FragmentTabHost mTabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order_details);
		init();
		setvalue();
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("我的订单");
	}
	
	private void setvalue(){
//		Button button=(Button)this.findViewById(R.id.myorderdetailsbutton);
//		button.setText("评论");
//		button.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				Intent intent = new Intent(MyOrderDetailsActivity.this, MyOrderCommentActivity.class);
//				startActivity(intent);
//			}
//		});
		
		Button button=(Button)this.findViewById(R.id.myorderdetailsbutton);
		button.setText("支付");
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MyOrderDetailsActivity.this, OrderPaymentDetailsActivity.class);
				startActivity(intent);
			}
		});
	}
	
}
