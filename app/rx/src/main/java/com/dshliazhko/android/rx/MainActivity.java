package com.dshliazhko.android.rx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<String> observable = Observable.fromArray(new String[]{"one", "two", "three"});
        Observer<String> observer = new Observer<String>(){

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("LOGG","onSubscribe "+d.toString());
            }

            @Override
            public void onNext(@NonNull String s) {
                Log.d("LOGG","onNext"+s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("LOGG","onError"+e);
            }

            @Override
            public void onComplete() {
                Log.d("LOGG","onComplete");
            }

        };
        observable.subscribe(observer);
    }
}