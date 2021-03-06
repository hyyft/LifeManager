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
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * 在使用之前必须设置回调函数
 * @author Administrator
 *
 */
public class AddPlanLittleTag extends ListFragment {

	private View view;
	private ListView listView;
	private String[] littleTags;
	private String littleTag = "none";
	private String bigTag = "none";
	private AddPlanLittleTagCallBack callBack;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.addplan_tagfragment_layout, container , false);
		listView = (ListView) view.findViewById(android.R.id.list);

		showLittleTag("日常" , "1");
		return view;
	}
	

	/**
	 * 获取sharedPreference的数据
	 * @param bigTag
	 */
	private void getLittleTag( String bigTagID){
		int i = 1;
	
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PrefConst.NAME,Context.MODE_PRIVATE);
        
		String[] temp = new String[100];
		while( true ){
			
			temp[i-1] = sharedPreferences.getString(bigTagID+"-"+i, "none");
			if( temp[i-1].equals("none") )break;			
			i++;
		}
		littleTags = new String[i-1];
		for(  int j = 0 ; j<i-1 ; j++){
			littleTags[j] = temp[j];
			
			}
		
	}
	/**
	 * 根据bigTag显示littleTag
	 * @param bigTag  bigTag
	 */
	public void showLittleTag(String bigTag , String bigTagID){
		this.bigTag = bigTag;
		getLittleTag(bigTagID);
		listView.setAdapter(new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_list_item_1, littleTags));
	}

	
	public void setCallBack(AddPlanLittleTagCallBack callBack){
		this.callBack = callBack;
	}
	
	
	public interface AddPlanLittleTagCallBack{
		public void returnTag( String bigTag , String littleTag );
	}


	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		littleTag = (String)l.getItemAtPosition(position);
		callBack.returnTag(bigTag, littleTag);
	}
	
	
	
}
