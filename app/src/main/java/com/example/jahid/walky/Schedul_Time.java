package com.example.jahid.walky;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;


    public class Schedul_Time extends AppCompatActivity {
        Button button;
        int hr,mn;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.schedule);
          Button button = (Button) findViewById(R.id.SetAlarm);
            EditText hours;
            hours= (EditText)findViewById(R.id.hours);
            EditText minute;
            minute= (EditText)findViewById(R.id.minute);
            String str=hours.getText().toString();
             String str2=minute.getText().toString();
             mn=Integer.parseInt(str2);
            hr= Integer.parseInt(str);
           button.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY,hr);
                    calendar.set(Calendar.MINUTE,mn);
                    Intent intent = new Intent(getApplicationContext(),NotificationReciever.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);
                }
            });

        }
    }


