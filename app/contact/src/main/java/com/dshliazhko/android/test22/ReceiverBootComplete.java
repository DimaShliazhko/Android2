package com.dshliazhko.android.test22;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverBootComplete extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Log","бродкст старт");
        Intent service = new Intent(context,MyService.class);
        context.startService(service);
    }
}
