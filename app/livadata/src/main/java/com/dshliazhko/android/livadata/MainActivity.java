package com.dshliazhko.android.livadata;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Button button;
    private TextView textView;
    private  MyViewModel myViewModel;
    // private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.viewEditText1);
        editText2 = findViewById(R.id.viewEditText2);
        button = findViewById(R.id.viewButton);
        textView = findViewById(R.id.viewText1);
        //  preferences = getSharedPreferences("Pref",MODE_PRIVATE);
        //   SharedPreferences.Editor editor = preferences.edit();
        // editor.putString("Edit1", editText1.toString());
        // editor.putString("Edit2", editText2.toString());
        // editor.commit();




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              textView.setText(editText1.getText().toString());
                createMaoel(editText1.getText().toString());
            }
        });
    }

    public void createMaoel (String s){
        Log.d("dima","activity s = "+s);
        myViewModel   = ViewModelProviders.of(this, new ModelFactory(s)).get(MyViewModel.class);
        LiveData<String> data = myViewModel.getDate();
        data .observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);

            }
        });
    }
}