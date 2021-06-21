package com.dshliazhko.android.test22;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Log", "служба создана");
        mediaPlayer = MediaPlayer.create(this, R.raw.mp3);
        mediaPlayer.setLooping(false);
        Toast.makeText(getApplication(),"time",Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // запускается в главном потоке! не забыть
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Log.d("Log", "сервис старт");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mediaPlayer.start();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return Service.START_STICKY;
        //  return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

}
