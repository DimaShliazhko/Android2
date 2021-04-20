package com.dshliazhko.android.test22;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyBroadcast extends BroadcastReceiver {

    // выполняется в главном потоке
    @Override
    public void onReceive(Context context, Intent intent) {
        StringBuilder msgStr = new StringBuilder("Текущее время: ");
        Format formatter = new SimpleDateFormat("hh:mm:ss a");
        msgStr.append(formatter.format(new Date()));
        Toast.makeText(context,"Broadcast Receivers запущен"+msgStr,Toast.LENGTH_LONG).show();
       // Toast.makeText(context,"Broadcast Receivers запущен"+msgStr,Toast.LENGTH_LONG).show();


    }
}
