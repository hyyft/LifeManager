package com.hyyft.noteeverything.plan;

import com.hyyft.noteeverything.R;
import com.hyyft.noteeverything.myconst.PrefConst;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * ��ʹ�õ�ʱ�����ͨ��setPlanBigTagCallBack�����ûص�����
 * @author Administrator
 *
 */
public class AddPlanBigTab extends ListFragment {

	private View view;
	private ListView listView;
	private String[] bigTag;
	private PlanBigTagCallBack callBack;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.id.add_littletag_fragment, container);
		listView = (ListView) view.findViewById(R.id.add_bigtag_list);
		showBigTag();
		listView.setOnItemClickListener(listener);
		return view;
	}
	
	
	
	/**
	 * item listener ��ȡbigTag
	 */
	private OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			TextView textView = (TextView)view;
			callBack.getBigTag(textView.getText().toString());
		}
	};
	
	/**
	 * ��ȡsharedPreference������
	 */
	private void getBigTag(){
		int i = 1;
		SharedPreferences sharedPreferences = 
				getActivity().getSharedPreferences(PrefConst.NAME,Context.MODE_PRIVATE);
		while( true ){
			bigTag[i-1] = sharedPreferences.getString(""+i, "none");
			if( bigTag[i-1].equals("none") )break;
			i++;
		}
	}
	/**
	 * ��ʾbigTag
	 */
	public void showBigTag(){
		getBigTag();
		listView.setAdapter(new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1, bigTag));
	}
	
	/**
	 * ���ûص� ����
	 */
	public void setPlanBigTagCallBack(PlanBigTagCallBack callBack){
		this.callBack = callBack;
	}
	public interface PlanBigTagCallBack{
		public void  getBigTag(String bigTag);
	}
	
}
