package com.nerdwin15.demo.alarmdemo;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AlarmMainActivity extends Activity {
    
    //Create the shared preferences object
       SharedPreferences sharedpreferences;
       public static final String MyPREFERENCES = "MyPrefs" ;
       public static final String Hours = "hours";
       int hournumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
       setContentView(R.layout.main);
       
       sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       hournumber = 2;
       if (sharedpreferences.contains(Name))    {
         hournumber = sharedpreferences.getInt(Hours, 2);
        }

        //Create an offset from the current time in which the alarm will go off.
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, hournumber*3600);

        //Create a new PendingIntent and add it to the AlarmManager
        Intent intent = new Intent(this, AlarmReceiverActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
            12345, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = 
            (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                pendingIntent);
    }
    
    private void modifyTime(int number) {
        SharedPreferences.Editor editor = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE).edit();
        editor.putInt(Hours, number);
        editor.commit();
    }

}
