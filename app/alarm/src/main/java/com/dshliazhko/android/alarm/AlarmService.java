package com.dshliazhko.android.alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlarmService extends Service {

    private MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplication(),"time",Toast.LENGTH_LONG).show();
        Log.d("Log", "служба создана");
        mediaPlayer = MediaPlayer.create(this, R.raw.mp3);
        mediaPlayer.setLooping(false);
         }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;


    }
}
