package com.dshliazhko.android.test20;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String L = "Log";
    private MainContract.Presenter presenter;

    private Button mButton;
    private TextView myTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        mButton = findViewById(R.id.viewButtom);
        myTv = findViewById(R.id.viewText);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onButtonWasClicked();
            }
        });
        Log.d(L, "onCreate()");

    }

    //Создаём Presenter и в аргументе передаём ему this - эта Activity расширяет интерфейс MainContract.View


    @Override
    public void showText(String s) {
        myTv.setText(s);
        Log.d(L, "showMessage()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        Log.d(L, "onDestroy()");
    }
}