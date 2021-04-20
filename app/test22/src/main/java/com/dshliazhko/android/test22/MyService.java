package com.dshliazhko.android.test22;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Log", "служба создана");
        mediaPlayer = MediaPlayer.create(this, R.raw.mp3);
        mediaPlayer.setLooping(false);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // запускается в главном потоке! не забыть
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

        //  mediaPlayer.start();
        // return Service.START_STICKY;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

}
