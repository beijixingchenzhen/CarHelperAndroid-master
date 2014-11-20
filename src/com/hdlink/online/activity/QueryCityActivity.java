package com.hdlink.online.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.hdlink.omline.adapter.QueryCtityGridViewAdapter;
import com.hdlink.online.R;
import com.hdlink.online.adapter.GridViewAdapter;
import com.hdlink.online.fragments.QAFragment;
import com.hdlink.online.utils.MyUtil;

public class QueryCityActivity extends BaseActivity implements OnClickListener{
	private GridView hotGridView;
	private TextView tv_province;
	private TextView tv_province_icon;
	private TextView tv_city;
	private TextView tv_city_icon;
	private Button btn_back;
	private TextView tv_title;
	private Button btn_save;
	private PopupWindow pop;
	private GridView grid;
	/*1为省份,2为城市,默认为-1*/
	private int isCity=-1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_check_city);
		findView();
		initView();
		addListener();
	}
	private void initView() {
		tv_title.setText(R.string.query_city_title);
		btn_save.setVisibility(View.VISIBLE);
		btn_save.setTextColor(Color.YELLOW);
		List<String> datas=getData();
		QueryCtityGridViewAdapter adapter=new QueryCtityGridViewAdapter(datas);
		hotGridView.setAdapter(adapter);
		
		/**计算GridView每一行的高度*/
		LayoutInflater inflater=getLayoutInflater();
		View view=inflater.inflate(R.layout.query_query_city_grid_item, null);
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        /**获取一行的高度*/
        int height =view.getMeasuredHeight();
        LinearLayout. LayoutParams lp=(LinearLayout.LayoutParams) hotGridView.getLayoutParams();
        /**计算总高度*/
        int sum=datas.size()/4;
        height=datas.size()%4==0?sum*height:(sum+1)*height;
        Log.i("info", "height---->"+height);
        lp.height=height+10;
        hotGridView.setLayoutParams(lp);
        
	}
	private void findView(){
		hotGridView=(GridView)this.findViewById(R.id.query_query_city_hot);
		tv_province=(TextView)this.findViewById(R.id.query_query_city_province);
		tv_province_icon=(TextView)this.findViewById(R.id.query_query_city_province_icon);
		tv_city=(TextView)this.findViewById(R.id.query_query_city_city);
		tv_city_icon=(TextView)this.findViewById(R.id.query_query_city_city_icon);
		
		View view=setActionBarLayout(R.layout.activity_headline);
		btn_back=(Button)view.findViewById(R.id.back_btn);
		tv_title=(TextView)view.findViewById(R.id.map_txvTitle);
		btn_save=(Button)view.findViewById(R.id.right_btn);
	}
	private void addListener(){
		tv_city.setOnClickListener(this);
		tv_city_icon.setOnClickListener(this);
		tv_province.setOnClickListener(this);
		tv_province_icon.setOnClickListener(this);
		btn_back.setOnClickListener(this);
		btn_save.setOnClickListener(this);
		hotGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
//				MyUtil.showToast(QueryCityActivity.this, "position:"+position);
				QAFragment.getInstance().setOnListener("", getData().get(position));
				QueryCityActivity.this.finish();
			}
		});
	}
	/**
	 * 初始化弹出框
	 */
	private void initPopWindow(final List<String> datas){
		LayoutInflater inflater=getLayoutInflater();
		View view=inflater.inflate(R.layout.query_query_city_grid_pop, null);
		pop=new PopupWindow(view,WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setOutsideTouchable(false);
		pop.setFocusable(true);
		grid=(GridView)view.findViewById(R.id.query_query_city_pop);
		grid.setAdapter(new GridViewAdapter(datas));
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				MyUtil.showToast(QueryCityActivity.this, "position:"+position);
				if(isCity==1){
					tv_province.setText(datas.get(position));
				}else{
					tv_city.setText(datas.get(position));
				}
				pop.dismiss();
			}
		});
		pop.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				tv_province_icon.setText("∨");
				tv_city_icon.setText("∨");
			}
		});
	}
	private void showPopWindow(int flag,View v){
		if(pop!=null&&pop.isShowing()){
			pop.dismiss();
			return;
		}
		if(flag==1){
			tv_province_icon.setText("∧");
			tv_city_icon.setText("∨");
		}else if(flag==2){
			tv_city_icon.setText("∧");
			tv_province_icon.setText("∨");
		}
		if(isCity==flag&&pop!=null){
			pop.showAsDropDown(v,0,10);
		}else{
			List<String> datas=flag==1?getDatas():getDataCity();
			initPopWindow(datas);
			pop.showAsDropDown(v,0,10);
		}
		isCity=flag;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.query_query_city_province:
			showPopWindow(1, v);
			break;

		case R.id.query_query_city_province_icon:
			showPopWindow(1, v);
			break;
		case R.id.query_query_city_city:
			showPopWindow(2, v);
			break;
		case R.id.query_query_city_city_icon:
			showPopWindow(2, v);
			break;
		case R.id.back_btn:
			this.finish();
			break;
		case R.id.right_btn://保存
			String province=tv_province.getText().toString();
			String city=tv_city.getText().toString();
			if(MyUtil.isNull(province)){
				MyUtil.showToast(this, "请选择省份");
				return ;
			}
			if(MyUtil.isNull(city)){
				MyUtil.showToast(this, "请选择城市");
				return;
			}
			QAFragment.getInstance().setOnListener(province, city);
			this.finish();
			break;
		}
	}
	
	/**
	 * 添加测试数据
	 * @return
	 */
	private List<String> getData(){
		List<String> list=new ArrayList<String>();
		for(int i=0;i<2;i++){
			list.add("北京");
			list.add("上海");
			list.add("广州");
			list.add("深圳");
		}
		return list;
	}
	/**
	 * 省份
	 * 添加测试数据
	 * @return
	 */
	private List<String> getDatas(){
		List<String> list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("湖南");
		}
		return list;
	}
	/**
	 * 城市
	 * 添加测试数据
	 * @return
	 */
	private List<String> getDataCity(){
		List<String> list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("长沙");
		}
		return list;
	}
}
