package com.dshliazhko.android.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<List> weathers;
    private EditText editText;
    private Button button;
    private String getButtonText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weathers = new ArrayList<>();
        editText = findViewById(R.id.viewEdit);
        button = findViewById(R.id.viewButton);
        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getButtonText = editText.getText().toString();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonText = editText.getText().toString();
                getWeather(getButtonText);
            }
        });

        getWeather(getButtonText);
    }


    public void getWeather(String s) {


        App.getApi().getMainWeather(s).enqueue(new Callback<ModelWeather>() {

            @Override
            public void onResponse(Call<ModelWeather> call, Response<ModelWeather> response) {
                if (response.body() != null) {
                    //weathers.addAll(  response.body().getList()  );

                    Log.d("Dima", "result " + response.body().getList().toString());

                 //   recyclerView.getAdapter().notifyDataSetChanged();


                    //  response.body().getList().get
                    //    weathers.addAll(response.body());
                    //  Log.d("Dima", "name = "+ response.body().getMessage());
                    Log.d("Dima", "list = " + response.body().getList().size());
                    Log.d("Dima", "name = " + response.body().getList().get(1).main.getTemp());
                    Log.d("Dima", "name = " + response.body().getList().get(1).dt_txt);
                    Log.d("Dima", "name = " + response.body().getList().get(1).weather.get(0).description);
                    PostsAdapter adapter = new PostsAdapter(response.body().getList());
                    recyclerView.setAdapter(adapter);

                } else {
                    Toast.makeText(MainActivity.this, "NULL", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelWeather> call, Throwable t) {
                Toast.makeText(MainActivity.this, "An error occurred during networking" + t, Toast.LENGTH_SHORT).show();
                Log.d("Dima", "Error  " + t);

            }
        });
    }
}