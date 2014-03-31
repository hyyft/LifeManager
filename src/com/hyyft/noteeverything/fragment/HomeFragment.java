package com.hyyft.noteeverything.fragment;

import com.hyyft.noteeverything.R;
import com.hyyft.noteeverything.weather.Weather;
import com.hyyft.noteeverything.weather.WeatherAsyncTast;
import com.hyyft.noteeverything.weather.WeatherAsyncTast.GetWeatherTast;
import android.view.View.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment { 


	private TextView addrTextView , temTextView , windTextView , weatherTextView;
	private TextView updateTextView;
	private ImageView iconImageView , ficonImageView;
	private TextView fTem , fWeather;
	private View view , view2 , view3 , view4;
	private Weather[] weathers;
	private final int WEATHER_DAY = 4;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		weathers = new Weather[WEATHER_DAY];
		WeatherAsyncTast wAsyncTast = new WeatherAsyncTast(getWeatherTast);
		wAsyncTast.execute(new String[]{});
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		view = inflater.inflate(R.layout.homefragment_layout, container, false);
		if(view2==null)
			view2 = inflater.inflate(R.layout.weather_future, null);
		if(view3==null)
			view3 = inflater.inflate(R.layout.weather_future, null);
		if(view4==null)
			view4 = inflater.inflate(R.layout.weather_future, null);
		
		findView(view);
		if( weathers[0] != null ){
			getWeather();
		}
		updateTextView.setOnClickListener(updateListener);
		return view;		
	}
	
	private OnClickListener updateListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			WeatherAsyncTast wAsyncTast = new WeatherAsyncTast(getWeatherTast);
			HomeFragment.this.updateTextView.setText("������.....");
			wAsyncTast.execute(new String[]{});
			
		}
	}; 
	
	/**
	 * ��ȡ�����ؼ��Ķ���
	 * @param view  fragment�Ĳ���
	 */
	private void findView(View view){
		addrTextView =  (TextView)view.findViewById(R.id.w_addr_tv);
		temTextView = (TextView)view.findViewById(R.id.w_tem_tv);
		windTextView = (TextView)view.findViewById(R.id.w_wind_tv);
		weatherTextView = (TextView)view.findViewById(R.id.w_weather_tv);
		updateTextView = (TextView)view.findViewById(R.id.w_update_tv);
		iconImageView = (ImageView)view.findViewById(R.id.w_icon_iv);
				
		
	}
	
	/**
	 * ����UI
	 */
	private void getWeather(){
		
		int indentify;
		Resources resources = getActivity().getResources();	

		addrTextView.setText(weathers[0].getAddr());
		temTextView.setText(weathers[0].getTemNow());
		windTextView.setText(resources.getString(R.string.w_text_weather)+weathers[0].getWeather());
		weatherTextView.setText(resources.getString(R.string.w_text_wind)+weathers[0].getWind());	
		//���ñ�������ͼ��
		indentify = resources.getIdentifier(getActivity().getPackageName()+":drawable/"+weathers[0].getIcon(), null, null);
		iconImageView.setImageResource(indentify);
	
		//���õڶ�������
		
		ficonImageView = (ImageView) view2.findViewById(R.id.w_ficon_tv);
		fTem = (TextView) view2.findViewById(R.id.w_ftem_tv);
		fWeather = (TextView) view2.findViewById(R.id.w_fweather_tv);
		
		fTem.setText(weathers[1].getTemHight());
		fWeather.setText(weathers[1].getWeather());
		indentify = resources.getIdentifier(getActivity().getPackageName()+":drawable/"+weathers[1].getIcon(), null, null);
		ficonImageView.setImageResource(indentify);
		LinearLayout layout = (LinearLayout)view.findViewById(R.id.w_futrue1_container);
		layout.removeAllViews();
		layout.addView(view2);
		
		
		// ���õ���������
		
		ficonImageView = (ImageView) view3.findViewById(R.id.w_ficon_tv);
		fTem = (TextView) view3.findViewById(R.id.w_ftem_tv);
		fWeather = (TextView) view3.findViewById(R.id.w_fweather_tv);

		fTem.setText(weathers[2].getTemHight());
		fWeather.setText(weathers[2].getWeather());
		indentify = resources.getIdentifier(getActivity().getPackageName()
				+ ":drawable/" + weathers[2].getIcon(), null, null);
		ficonImageView.setImageResource(indentify);
		 layout = (LinearLayout) view
				.findViewById(R.id.w_futrue2_container);
		 layout.removeAllViews();
		layout.addView(view3);
		
		// ���õ���������
		
		ficonImageView = (ImageView) view4.findViewById(R.id.w_ficon_tv);
		fTem = (TextView) view4.findViewById(R.id.w_ftem_tv);
		fWeather = (TextView) view4.findViewById(R.id.w_fweather_tv);

		fTem.setText(weathers[3].getTemHight());
		fWeather.setText(weathers[3].getWeather());
		indentify = resources.getIdentifier(getActivity().getPackageName()
				+ ":drawable/" + weathers[3].getIcon(), null, null);
		ficonImageView.setImageResource(indentify);
		 layout = (LinearLayout) view
				.findViewById(R.id.w_futrue3_container);
		layout.removeAllViews();
		layout.addView(view4);
		
	}
	
	/**
	 * ����UI�Ľӿڶ��󣬿����ڴ����������и���fragment��UI
	 */
	private GetWeatherTast getWeatherTast = new GetWeatherTast() {
		
		@Override
		public void updateWeather(Weather[] weathers) {
			// TODO Auto-generated method stub
			if (weathers == null) {
					Toast.makeText(getActivity(), "���糬ʱ", Toast.LENGTH_LONG).show();
					
			} else {
				HomeFragment.this.weathers = weathers;
				getWeather();
			}
			if(updateTextView.getText().toString().equals("������.....")){
				updateTextView.setText(getActivity().getResources().getString(R.string.w_text_update));
			}
	
		}
	};

	
}
