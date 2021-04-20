package com.dshliazhko.android.test2;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Info extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.double_activity);
        textView = (TextView) findViewById(R.id.viewPastText);

        // получаем Intent, который вызывал это Activity
        Intent intent = getIntent();

        textView.setText(intent.getStringExtra("Add"));
        // читаем из него action
        Log.d("Dima","text прием"+  intent.getStringExtra("Add"));
        String action = intent.getAction();

        String format = "", textInfo = "";

        // в зависимости от action заполняем переменные
        if (action.equals("showtime")) {
            format = "HH:mm:ss";
            textInfo = "Time: ";
        } else if (action.equals("showdate")) {
            format = "dd.MM.yyyy";
            textInfo = "Date: ";
        }

        // в зависимости от содержимого переменной format
        // получаем дату или время в переменную datetime
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String datetime = sdf.format(new Date(System.currentTimeMillis()));

        TextView tvDate = (TextView) findViewById(R.id.tvInfo);
        tvDate.setText(textInfo + datetime);
    }
}