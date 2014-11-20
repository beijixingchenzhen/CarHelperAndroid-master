package com.hdlink.online.fragments;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hdlink.online.R;
import com.hdlink.online.activity.BaseActivity;
import com.hdlink.online.activity.FindServiceActivity;
import com.hdlink.online.model.GlobalConfig;
import com.hdlink.online.view.RecServiceGridView;

/**
 *
 * @author tuwei
 *
 */

public class HomePageFragment extends BaseFragment {

	private View view;
	private RecServiceGridView serviceGrid;

	private int[] ids = { R.drawable.ic_launcher, R.drawable.ic_launcher,
			R.drawable.ic_launcher };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		view = inflater.inflate(R.layout.tab_homepage, null);
//		viewFlow = (ViewFlow) view.findViewById(R.id.viewflow);
//		viewFlow.setAdapter(new ImageAdapterTabhostOne(getActivity()));
//		viewFlow.setTextDirection(4500); // 延迟
//		viewFlow.setSelection(3 * 1000); // 起始位置
//		viewFlow.startAutoFlowTimer(); // 自动播放

		serviceGrid = (RecServiceGridView) view.findViewById(R.id.rec_service_grid);
        serviceGrid.init();
        serviceGrid.setItemClickLisener(new RecServiceGridView.OnItemClick() {
            @Override
            public void onCall(String id) {
                Intent intent = new Intent(self.getActivity(), FindServiceActivity.class);
                intent.putExtra("serviceId", id);
                self.getActivity().startActivity(intent);
            }
        });

        serviceGrid.setMoreClickLisener(new RecServiceGridView.OnMoreClick() {
            @Override
            public void onCall() {

            }
        });

		return view;
	}

    @Override
    public void onShow() {
        getBaseActivity().setDefaultLayout("车生活", BaseActivity.WITH_NONE);
    }


    public void initRecommendGrid(List<String> services){

    }

	@Override
	public void onResume() {

		super.onResume();
	}

//	class ImageAdapterTabhostOne extends BaseAdapter {
//
//		private Context mContext;
//		private LayoutInflater mInflater;
//
//		public ImageAdapterTabhostOne(Context context) {
//			mContext = context;
//			mInflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		}
//
//		@Override
//		public int getCount() {
//			return Integer.MAX_VALUE;
//			// return list.size();
//		}
//
//		@Override
//		public Object getItem(int position) {
//
//			return position;
//		}
//
//		@Override
//		public long getItemId(int position) {
//
//			return position;
//		}
//
//		@Override
//		public View getView(final int position, View convertView,
//				ViewGroup parent) {
//
//			if (convertView == null) {
//				convertView = mInflater.inflate(R.layout.tabhost_tab_one_item1,
//						null);
//			}
//			System.out.println("====================>");
//			((ImageView) convertView.findViewById(R.id.gallery_image))
//					.setImageResource(ids[position % ids.length]);
//
//			convertView.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//
//				}
//			});
//
//			return convertView;
//		}

//	}

}
