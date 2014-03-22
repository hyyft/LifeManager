package com.hyyft.noteeverything.fragment;



import com.hyyft.noteeverything.R;
import com.hyyft.noteeverything.adapter.DayPlanAdapter;
import com.hyyft.noteeverything.global.NoteGlobal;
import com.hyyft.noteeverything.planactivity.AddPlanActivity;
import com.hyyft.noteeverything.planactivity.CalendarActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PlanFragment extends ListFragment {

	public static final int CALENDAR_REQUEST_CODE = 1;
	public static final int ADDPLAN_REQUEST_CODE = 2;
	private static final String Tag = "PlanFragment";
	private TextView dateTextView;
	private NoteGlobal noteGlobal;
	private ListView listView;
	private Button mbtn_add;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View mainView = inflater.inflate(R.layout.dayplanfragment_layout, container, false);
		listView = (ListView)mainView.findViewById(android.R.id.list);
		
		mbtn_add = (Button)mainView.findViewById(R.id.btn_add_plan);		
		mbtn_add.setOnClickListener(btn_add_l);	
		
		dateTextView = (TextView)mainView.findViewById(R.id.date_textview);
		dateTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getActivity(), CalendarActivity.class);
				startActivityForResult(intent, CALENDAR_REQUEST_CODE);
			}
		});
	
		addPlan_View();  //���ؽ��յļƻ�
		

		//�������ڣ���ʾ��������ڣ�
		Time time = new Time();
		time.setToNow();
		dateTextView.setText(""+time.year+"-"+(time.month+1)+"-"+time.monthDay);
		
		
		return mainView;
	}

	
	/**
	 * ����subActivity�ķ���ֵ
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//��ȡ����
		switch (requestCode) {
		case CALENDAR_REQUEST_CODE:
			if( resultCode == CalendarActivity.CALENDAR_RESULT_CODE_OK ){
				String date = data.getExtras().getString(CalendarActivity.CALENDAR_RESULT);
				dateTextView.setText(date);
			}
			break;

		case ADDPLAN_REQUEST_CODE:
			if( resultCode == 1 ){
				addPlan_View();  //���¼��ؼƻ�
			}
			break;
		default:
			break;
		}
	}
	
	private void addPlan_View(){
		  //���ؽ��ռƻ�
				DayPlanAdapter adapter = new DayPlanAdapter(getActivity());		
				noteGlobal = (NoteGlobal)getActivity().getApplication();
				for( int i=0 ; i<noteGlobal.planList.size() ;i++  ){
					
					adapter.addList(noteGlobal.planList.get(i));
					Log.i(Tag , noteGlobal.planList.get(i).toString());
				}		
				listView.setAdapter(adapter);
	}
	
	/**
	 * btn_add ��ť����Ϣ��Ӧ����
	 */
	private OnClickListener btn_add_l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(getActivity(), AddPlanActivity.class);
			startActivityForResult(intent, ADDPLAN_REQUEST_CODE);
		}
	};

	
}
