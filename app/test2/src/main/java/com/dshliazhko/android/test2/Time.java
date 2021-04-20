package com.dshliazhko.android.test2;

    import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

    import androidx.appcompat.app.AppCompatActivity;

public class Time extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.time);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(new Date(System.currentTimeMillis()));

            TextView tvTime = (TextView) findViewById(R.id.tvTime);
            tvTime.setText(time);
        }
    }
