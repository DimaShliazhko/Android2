package com.dshliazhko.android.test2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {
    private EditText editText;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.viewAddText);

        Button btnTime = (Button) findViewById(R.id.btnTime);
        Button btnDate = (Button) findViewById(R.id.btnDate);

        btnTime.setOnClickListener(this);
        btnDate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        intent = new Intent();
        intent.putExtra("Add",editText.getText().toString());
        Log.d("Dima","text отправка"+editText.getText());
        switch(v.getId()) {
            case R.id.btnTime:

                intent.setAction("showtime");
                startActivity(intent);
                break;
            case R.id.btnDate:


                intent.setAction("showdate");
                startActivity(intent);
                break;
        }
    }
}