package com.hyyft.noteeverything.plan;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * ������ʾtimePicker��dateTimePicker
 * @author hyyft
 *
 */
public class CreateTimeDialog {
	
	Context context;
	public CreateTimeDialog(Context context){
		this.context = context;
	}

	public static final int DATE_DIALOG = 1;
	public static final int TIME_DIALOG = 2;
	protected static final String TAG = "CreateTimeDialog";
	
	public void getDate(){
		onCreateDialog(DATE_DIALOG).show();
	}
	
	private Dialog onCreateDialog(int id) { 
        //������ȡ���ں�ʱ��� 
        Calendar calendar = Calendar.getInstance();  
         
        Dialog dialog = null; 
        switch(id) { 
            case DATE_DIALOG: 
                DatePickerDialog.OnDateSetListener dateListener =  
                    new DatePickerDialog.OnDateSetListener() {
                        @Override 
                        public void onDateSet(DatePicker datePicker,  
                                int year, int month, int dayOfMonth) { 
                        	
                        	
                        } 
                    }; 
                dialog = new DatePickerDialog(context, 
                        dateListener, 
                        calendar.get(Calendar.YEAR), 
                        calendar.get(Calendar.MONTH), 
                        calendar.get(Calendar.DAY_OF_MONTH)); 
                break; 
            case TIME_DIALOG: 
                TimePickerDialog.OnTimeSetListener timeListener =  
                    new TimePickerDialog.OnTimeSetListener() { 
                         
                        @Override 
                        public void onTimeSet(TimePicker timerPicker, 
                                int hourOfDay, int minute) { 
                           
                        	
                        } 
                    }; 
                    dialog = new TimePickerDialog(context , timeListener, 
                            calendar.get(Calendar.HOUR_OF_DAY), 
                            calendar.get(Calendar.MINUTE), 
                            true);   //�Ƿ�Ϊ��ʮ���� 
                break; 
            default: 
                break; 
        } 
        return dialog; 
    } 


}