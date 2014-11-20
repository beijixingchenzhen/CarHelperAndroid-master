package com.hdlink.online.activity;

import com.hdlink.online.R;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.hdlink.online.model.GlobalConfig;

public class ServiceDetailActivity extends BackBaseActivity{
	
	private Button back_btn;
	private TextView map_txvTitle;

    private String mServiceId = "";
    private String mServiceName = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_detail);
        setLogoType(WITH_BACK);
        if(getIntent().hasExtra("serviceId")){
            mServiceId = getIntent().getStringExtra("serviceId");
            mServiceName = GlobalConfig.shared().getServiceName(mServiceId);
            setTitle(mServiceName);
;
            ((TextView)findViewById(R.id.service_detail_name)).setText(mServiceName);
            ((TextView)findViewById(R.id.service_detail_price)).setText("88");
            ((TextView)findViewById(R.id.service_detail_origin_price)).setText("300");

        }

	}

	

}
