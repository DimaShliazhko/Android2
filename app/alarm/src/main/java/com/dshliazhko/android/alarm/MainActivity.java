package com.dshliazhko.android.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmService.class);
        //намериние в будущем
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        //AlarmService alarmService = new AlarmService();
        //  startService(intent);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),  1000, pendingIntent);
        //   MyAlarmBroadcast myAlarmBroadcast = new MyAlarmBroadcast();
        // IntentFilter intentFilter = new IntentFilter();
        //     intentFilter.addAction(Intent.ACTION_TIME_TICK);
        //   intentFilter.addAction(Intent.CATEGORY_LAUNCHER);
        //  this.registerReceiver(myAlarmBroadcast,intentFilter);

    }
}