package com.dshliazhko.android.livadata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    private EditText editText1;
    private EditText editText2;
    private Button button;
    private TextView textView;
    private MyViewModel myViewModel;
    private Fragment fragment1;
    private Fragment fragment2;
    private FragmentViewModel  fragmentViewModel;
    // private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1 = findViewById(R.id.viewEditText1);
        editText2 = findViewById(R.id.viewEditText2);
        button = findViewById(R.id.viewButton);
        textView = findViewById(R.id.viewText1);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        transaction = getSupportFragmentManager().beginTransaction().add(R.id.viewConteiner1, fragment1);
        transaction.commit();
        transaction = getSupportFragmentManager().beginTransaction().add(R.id.viewConteiner2, fragment2);
        transaction.commit();





        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        createModel();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText(editText1.getText().toString());
                myViewModel.setMutableLiveData(editText1.getText().toString());

            }
        });
    }

    public void createModel() {

        LiveData<String> data = myViewModel.getDate();
        data.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);

            }
        });
    }
}