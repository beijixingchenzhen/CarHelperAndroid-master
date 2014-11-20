package com.hdlink.online.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hdlink.online.adapter.GridViewAdapter;
import com.hdlink.online.R;


public class AddMyCarInfoActivity extends Activity {
	
	private TextView map_txvTitle,carPlate,carPlateLetter,carPlateTimeYear,carPlateTimeMon;
	ArrayList<String> carLicenseLate = new ArrayList<String>(Arrays.asList("粤","湘","XX","粤","湘","XX","粤","湘","XX","粤","湘","XX","粤","湘","XX","粤","湘","XX"));
	ArrayList<String> carLicenseLateLetter = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G","H","I","J"));
	Button btnShowDialog;
	GridView gridView;
	private LinearLayout linearLayout,carPlateTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_mycar_info);
		init();
		selectCarPlate();
		selectCarPlateLetter();
		selectCarPlateTime();
	}
	
	private void init(){
		
		map_txvTitle = (TextView)findViewById(R.id.map_txvTitle);
		map_txvTitle.setText("添加爱车信息");
		LinearLayout addMyCarModels = (LinearLayout)findViewById(R.id.add_mycar_models);
		addMyCarModels.setOnClickListener(new OnClickListener() {
			
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(AddMyCarInfoActivity.this,CarBrandActivity.class);
			startActivity(intent);
				
			}
		});
			
		
	}
	
	private void selectCarPlate(){
		carPlate = (TextView)findViewById(R.id.car_plate);
		carPlate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				carPlate=(TextView)findViewById(R.id.car_plate);
				linearLayout=(LinearLayout) findViewById(R.id.lLayout);
				linearLayout.setVisibility(View.VISIBLE);
				gridView=(GridView) linearLayout.findViewById(R.id.gv);
				gridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						carPlate.setText(carLicenseLate.get(position));
					    linearLayout.setVisibility(View.GONE);
					}
					
				});
				gridView.setAdapter(new GridViewAdapter(carLicenseLate));
				
			}
		});
	         
	 }
	
	
	private void selectCarPlateLetter(){
		carPlateLetter = (TextView)findViewById(R.id.car_plate_letter);
		carPlateLetter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				carPlateLetter=(TextView)findViewById(R.id.car_plate_letter);
				linearLayout=(LinearLayout) findViewById(R.id.lLayout);
				linearLayout.setVisibility(View.VISIBLE);
				gridView=(GridView) linearLayout.findViewById(R.id.gv);
				gridView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						carPlateLetter.setText(carLicenseLateLetter.get(position));
					    linearLayout.setVisibility(View.GONE);
					}
					
				});
				gridView.setAdapter(new GridViewAdapter(carLicenseLateLetter));
			}
		});
	         

	}

	private void selectCarPlateTime(){
			
			carPlateTime = (LinearLayout)findViewById(R.id.car_plate_time);
			carPlateTimeYear = (TextView)findViewById(R.id.car_plate_time_year);
			carPlateTimeMon = (TextView)findViewById(R.id.car_plate_time_mon);
			carPlateTime.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					calendar();
					linearLayout=(LinearLayout) findViewById(R.id.lLayout_time);
					linearLayout.setVisibility(View.VISIBLE);
				}
			});
	}
	
	void calendar(){
		Calendar calendar = Calendar.getInstance();

        final WheelView month = (WheelView) findViewById(R.id.month);
        final WheelView year = (WheelView) findViewById(R.id.year);
        
        OnWheelChangedListener listener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateDays(year, month);
            }
        };

        // month
        final int curMonth = calendar.get(Calendar.MONTH);
        String months[] = new String[] {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November", "December"};
        month.setViewAdapter(new DateArrayAdapter(this, months, curMonth));
        month.setCurrentItem(curMonth);
       
        month.addScrollingListener(new OnWheelScrollListener() {
			@Override
			public void onScrollingFinished(WheelView wheel) {

				int position = wheel.getCurrentItem();
				String resultMonth = Integer.toString(curMonth+position-9);
				carPlateTimeMon.setText(resultMonth);
				
			}

			@Override
			public void onScrollingStarted(WheelView wheel) {
				
			}
		});
    
       
        // year
        final int curYear = calendar.get(Calendar.YEAR);
        year.setViewAdapter(new DateNumericAdapter(this, curYear-10, curYear, 0));
        year.setCurrentItem(curYear);
        year.addScrollingListener(new OnWheelScrollListener() {
			
			@Override
			public void onScrollingStarted(WheelView wheel) {
				
			}
			
			@Override
			public void onScrollingFinished(WheelView wheel) {
				
				int position = wheel.getCurrentItem();
				String resultYear = Integer.toString(curYear+position-10);
				carPlateTimeYear.setText(resultYear);
				
			}
		});
        
        //day
        updateDays(year, month);
       
	}
	 void updateDays(WheelView year, WheelView month) {
	        Calendar calendar = Calendar.getInstance();
	        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
	        calendar.set(Calendar.MONTH, month.getCurrentItem());
	        
	        
	    }
	    
	    /**
	     * Adapter for numeric wheels. Highlights the current value.
	     */
	    private class DateNumericAdapter extends NumericWheelAdapter {
	        // Index of current item
	        int currentItem;
	        // Index of item to be highlighted
	        int currentValue;
	        
	        /**
	         * Constructors
	         */
	        public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
	            super(context, minValue, maxValue);
	            this.currentValue = current;
	            setTextSize(16);
	        }
	        
	        @Override
	        protected void configureTextView(TextView view) {
	            super.configureTextView(view);
	            if (currentItem == currentValue) {
	                view.setTextColor(0xFF0000F0);
	            }
	            view.setTypeface(Typeface.SANS_SERIF);
	        }
	        
	        @Override
	        public View getItem(int index, View cachedView, ViewGroup parent) {
	            currentItem = index;
	            return super.getItem(index, cachedView, parent);
	        }
	    }
	    
	    /**
	     * Adapter for string based wheel. Highlights the current value.
	     */
	    private class DateArrayAdapter extends ArrayWheelAdapter<String> {
	        // Index of current item
	        int currentItem;
	        // Index of item to be highlighted
	        int currentValue;
	        
	        /**
	         * Constructor
	         */
	        public DateArrayAdapter(Context context, String[] items, int current) {
	            super(context, items);
	            this.currentValue = current;
	            setTextSize(16);
	        }
	        
	        @Override
	        protected void configureTextView(TextView view) {
	            super.configureTextView(view);
	            if (currentItem == currentValue) {
	                view.setTextColor(0xFF0000F0);
	            }
	            view.setTypeface(Typeface.SANS_SERIF);
	        }
	        
	        @Override
	        public View getItem(int index, View cachedView, ViewGroup parent) {
	            currentItem = index;
	            return super.getItem(index, cachedView, parent);
	        }
	    }

}
