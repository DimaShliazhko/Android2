package com.dshliazhko.android.test21;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private EditText editText;
    private TextView textView;
    private Button keep;
    private Button out;
    private SharedPreferences sharedPreferences;
    private MainContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.viewEditText);
        textView = findViewById(R.id.viewTextView);
        keep = findViewById(R.id.viewButtonKeepPref);
        out = findViewById(R.id.viewButtonOut);
        sharedPreferences = getPreferences(MODE_PRIVATE);
        presenter = new MainPresenter(this, sharedPreferences);

        keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickKeepButton( );
            }
        });

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickKeepButtonOut();
            }
        });

    }


    @Override
    public void showText(String s) {
        textView.setText(s);

    }

    @Override
    public String getEditText() {
        return editText.getText().toString();
    }
}