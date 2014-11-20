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

public class MyOrderListActivity extends FragmentActivity {

	private Button back_btn, right_btn;
	private TextView map_txvTitle;
	private FragmentTabHost mTabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order_list);
		init();
		initTabs();
		
	}

	private void init() {
		map_txvTitle = (TextView) this.findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("我的订单");
		right_btn = (Button) this.findViewById(R.id.right_btn);
		right_btn.setVisibility(View.VISIBLE);
		right_btn.setText("编辑");
	}

	private void initTabs() {


		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

//		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//		mTabHost.setup(this, getSupportFragmentManager(), R.id.r);

		
		mTabHost.getTabWidget().setDividerDrawable(R.color.white);

		buildTab(mTabHost, "tab_one", R.drawable.tabbar_four_nor, "全部",
				AllOrderListFragment.class);

		buildTab(mTabHost, "tab_two", R.drawable.tabbar_four_nor, "未付款",
				NoPaymentOrderListFragment.class);

		buildTab(mTabHost, "tab_three", R.drawable.tabbar_four_nor,"待消费", 
				ForConsumptionOrderListFragment.class);

		buildTab(mTabHost, "tab_four", R.drawable.tabbar_four_nor, "待评价",
				ForEvaluationOrderListFragment.class);

		mTabHost.getTabWidget().getChildAt(0).setOnClickListener(listener);
		mTabHost.getTabWidget().getChildAt(1).setOnClickListener(listener1);
		mTabHost.getTabWidget().getChildAt(2).setOnClickListener(listener2);
		mTabHost.getTabWidget().getChildAt(3).setOnClickListener(listener3);


		mTabHost.setCurrentTab(0);

	}

	/**
	 * 
	 * @param tabHost
	 * @param tag
	 * @param bgId
	 * @param cls
	 */
	private void buildTab(FragmentTabHost tabHost, String tag, int bgId,
			String text, Class<?> cls) {

		LayoutInflater inflater = getLayoutInflater();

		LinearLayout ll = (LinearLayout) inflater.inflate(
				R.layout.my_order_tabhost_tab, null);


		TextView tabhost_item_tx = (TextView) ll
				.findViewById(R.id.tabhost_item_tx);


		tabhost_item_tx.setText(text);

		tabHost.addTab(tabHost.newTabSpec(tag).setIndicator(ll), cls, null);
	}


	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {

			mTabHost.setCurrentTab(0);

		}
	};

	private OnClickListener listener1 = new OnClickListener() {

		@Override
		public void onClick(View v) {

			mTabHost.setCurrentTab(1);

		}
	};
	

	private OnClickListener listener2 = new OnClickListener() {

		@Override
		public void onClick(View v) {
			mTabHost.setCurrentTab(2);

		}
	};

	
	
	private OnClickListener listener3 = new OnClickListener() {

		@Override
		public void onClick(View v) {


				mTabHost.setCurrentTab(3);
		}
	};


}
