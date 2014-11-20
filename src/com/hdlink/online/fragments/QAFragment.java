package com.hdlink.online.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hdlink.online.activity.BaseActivity;

import com.hdlink.online.R;
import com.hdlink.online.activity.QueryCityActivity;
import com.hdlink.online.adapter.GridViewAdapter;
import com.hdlink.online.model.QueryCityInterface;
import com.hdlink.online.utils.MyUtil;


/**
 * 
 * @author tuwei 
 * 
 */

public class QAFragment extends Fragment implements OnClickListener,QueryCityInterface{
	private final String SHARE_NAME="com.query";
	private final String REMEMBER_MSG="com.isRemember";//是否记住车信息
	private final String KEY_CITY="com.key.city";//城市
	private final String KEY_CAR_SHORT="com.key.short";//简称
	private final String KEY_CAR_NUM="com.key.num";//城市字母
	private final String KEY_CAR_NUMBER="com.key.number";//车牌号码
	private final String KEY_CJH="com.cjh";//车架号
	private final String KEY_FDJ="com.fdj";//发动机号
	
	private Button back_btn;
	private TextView map_txvTitle;
	private View rootView;
	private LinearLayout select_city_lly;
	private TextView tv_cph;    //
	private TextView tv_cph_icon;
	private TextView tv_cph_no;
	private TextView tv_cph_no_icon;
	private EditText et_cph_number;
	private EditText et_fdj;//发动机
	private EditText et_cj;//车架号
	private CheckBox checkBox;
	private TextView tv_submit;
	
	private SharedPreferences mSharedPreferences;
	private TextView tv_QueryCity;
	private static QAFragment mQAFragment;
	/**1为城市简称，2为车牌号*/
	private int isCarNum=-1;
	private PopupWindow pop;
	private GridView grid;
	/**是否记住车信息*/
	private boolean isRemember=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.tab_qa, null);
		findView();
		initView();
		addListener();
		return rootView;
	}
	public static QAFragment getInstance(){
		return mQAFragment;
	}
	private void initView() {
		// TODO Auto-generated method stub
		mSharedPreferences=getActivity().getSharedPreferences(SHARE_NAME, Activity.MODE_PRIVATE);
		isRemember=mSharedPreferences.getBoolean(REMEMBER_MSG, true);
		checkBox.setChecked(isRemember);
		mQAFragment=this;
		//初始化保存的车辆信息
		if(isRemember){
			tv_QueryCity.setText(mSharedPreferences.getString(KEY_CITY, ""));
			tv_cph.setText(mSharedPreferences.getString(KEY_CAR_SHORT, ""));
			tv_cph_no.setText(mSharedPreferences.getString(KEY_CAR_NUM, ""));
			et_cph_number.setText(mSharedPreferences.getString(KEY_CAR_NUMBER, ""));
			et_cj.setText(mSharedPreferences.getString(KEY_CJH, ""));
			et_fdj.setText(mSharedPreferences.getString(KEY_FDJ, ""));
		}
	}
	private void addListener() {
		// TODO Auto-generated method stub
		select_city_lly.setOnClickListener(this);
		tv_cph.setOnClickListener(this);
		tv_cph_icon.setOnClickListener(this);
		tv_cph_no.setOnClickListener(this);
		tv_cph_no_icon.setOnClickListener(this);
		tv_submit.setOnClickListener(this);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				mSharedPreferences.edit().putBoolean(REMEMBER_MSG,isChecked).commit();
				Editor mEditor=mSharedPreferences.edit();
				/**保存车信息*/
				if(isChecked){
					saveCarMsg(mEditor);
				}else{
					/**删除车信息*/
					mEditor.remove(KEY_CITY);
					mEditor.remove(KEY_CAR_SHORT);
					mEditor.remove(KEY_CAR_NUM);
					mEditor.remove(KEY_CAR_NUMBER);
					mEditor.remove(KEY_CJH);
					mEditor.remove(KEY_FDJ);
				}
			}

		});
	}
	/**
	 * 保存车辆信息
	 * @param mEditor
	 */
	private void saveCarMsg(Editor mEditor) {
		mEditor.putString(KEY_CITY, tv_QueryCity.getText().toString());
		mEditor.putString(KEY_CAR_SHORT, tv_cph.getText().toString());
		mEditor.putString(KEY_CAR_NUM, tv_cph_no.getText().toString());
		mEditor.putString(KEY_CAR_NUMBER, et_cph_number.getText().toString());
		mEditor.putString(KEY_CJH, et_cj.getText().toString());
		mEditor.putString(KEY_FDJ, et_fdj.getText().toString());
		mEditor.commit();
	}
	private void findView(){
		select_city_lly=(LinearLayout)rootView.findViewById(R.id.query_query_city);
		 tv_cph = (TextView)rootView.findViewById(R.id.query_query_city_cph);
		 tv_cph_icon = (TextView)rootView.findViewById(R.id.query_query_city_cph_icon);
		 tv_cph_no=(TextView)rootView.findViewById(R.id.query_query_city_cph_no);
		 tv_cph_no_icon=(TextView)rootView.findViewById(R.id.query_query_city_cph_no_icon);
		 et_cph_number=(EditText)rootView.findViewById(R.id.query_query_city_cph_number);
		 et_fdj=(EditText)rootView.findViewById(R.id.query_query_fdj);
		 et_cj=(EditText)rootView.findViewById(R.id.query_query_cjh);
		 checkBox=(CheckBox)rootView.findViewById(R.id.query_query_remember_checkbox);
		 tv_submit=(TextView)rootView.findViewById(R.id.query_query_submit);
		 tv_QueryCity=(TextView)rootView.findViewById(R.id.query_query_city_tv);
	}
	private boolean checkNull(){
		if(MyUtil.isNull(tv_QueryCity.getText().toString())){
			MyUtil.showToast(getActivity(), "请选择查询城市!");
			return true;
		}
		if(MyUtil.isNull(tv_cph.getText().toString())){
			MyUtil.showToast(getActivity(), "请选择车牌城市简称!");
			return true;
		}
		if(MyUtil.isNull(tv_cph_no.getText().toString())){
			MyUtil.showToast(getActivity(), "请选择城市字母!");
			return true;
		}
		if(MyUtil.isNull(et_cph_number.getText().toString())){
			MyUtil.showToast(getActivity(), "请输入车牌号!");
			return true;
		}
		if(MyUtil.isNull(et_fdj.getText().toString())){
			MyUtil.showToast(getActivity(), "请输入发动机号!");
			return true;
		}
		if(MyUtil.isNull(et_cj.getText().toString())){
			MyUtil.showToast(getActivity(), "请输入车架号!");
			return true;
		}
		return false;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.query_query_city:
			startActivity(new Intent(getActivity(), QueryCityActivity.class));
			break;

		case R.id.query_query_city_cph:
			showPopWindow(1, v);
			break;
		case R.id.query_query_city_cph_icon:
			showPopWindow(1, v);
			break;
		case R.id.query_query_city_cph_no:
			showPopWindow(2, v);
			break;
		case R.id.query_query_city_cph_no_icon:
			showPopWindow(2, v);
			break;
		case R.id.query_query_submit:
			if(!checkNull()){
				MyUtil.showToast(getActivity(), "开始查询");
			}
			/**保存车辆信息*/
			saveCarMsg(mSharedPreferences.edit());
			break;
		}
	}
	/**
	 * 初始化弹出框
	 */
	private void initPopWindow(final List<String> datas){
		LayoutInflater inflater=getActivity().getLayoutInflater();
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
				MyUtil.showToast(getActivity(), "position:"+position);
				if(isCarNum==1){
					tv_cph.setText(datas.get(position));
				}else{
					tv_cph_no.setText(datas.get(position));
				}
				pop.dismiss();
			}
		});
		pop.setOnDismissListener(new OnDismissListener() {
			
			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				tv_cph_icon.setText("∨");
				tv_cph_no_icon.setText("∨");
			}
		});
	}
	private void showPopWindow(int flag,View v){
		if(pop!=null&&pop.isShowing()){
			pop.dismiss();
			return;
		}
		if(flag==1){
			tv_cph_icon.setText("∧");
			tv_cph_no_icon.setText("∨");
		}else if(flag==2){
			tv_cph_no_icon.setText("∧");
			tv_cph_icon.setText("∨");
		}
		if(isCarNum==flag&&pop!=null){
			pop.showAsDropDown(v,0,10);
		}else{
			List<String> datas=flag==1?getDatas():getDataCity();
			initPopWindow(datas);
			pop.showAsDropDown(v,0,10);
		}
		isCarNum=flag;
	}
	@Override
	public void setOnListener(String province, String city) {
		// TODO Auto-generated method stub
		Log.i("info", "city---->"+city);
		tv_QueryCity.setText(province+"  "+city);
	}
	
	/**
	 * 省份
	 * 添加测试数据
	 * @return
	 */
	private List<String> getDatas(){
		List<String> list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("京");
			list.add("沪");
			list.add("粤");
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
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.add("H");
		list.add("I");
		list.add("J");
		list.add("K");
		list.add("L");
		list.add("M");
		list.add("N");
		list.add("O");
		list.add("P");
		list.add("Q");
		list.add("R");
		list.add("S");
		list.add("T");
		list.add("U");
		list.add("V");
		list.add("W");
		list.add("X");
		list.add("Y");
		list.add("Z");
		return list;
	}
}
