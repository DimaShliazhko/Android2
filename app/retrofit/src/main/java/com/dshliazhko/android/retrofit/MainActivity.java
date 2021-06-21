package com.dshliazhko.android.retrofit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
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
    private LocationManager locationManager;
    private TextView textView;
    private Button buttonSettings;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {


            if (location != null) {
                showLocation(location);
            } else {
                textView.setText("location");
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Toast toast = Toast.makeText(getApplicationContext(), "Провайдеры включены", Toast.LENGTH_LONG);
            toast.show();

            if (s.equals(LocationManager.GPS_PROVIDER)) {
                textView.setText("Status: " + String.valueOf(i));
            } else if (s.equals(LocationManager.NETWORK_PROVIDER)) {
                textView.setText("Status: " + String.valueOf(i));
            }
        }

        @Override
        public void onProviderEnabled(String s) {
            Toast toast = Toast.makeText(getApplicationContext(), "Провайдеры включены", Toast.LENGTH_LONG);
            toast.show();
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            showLocation(locationManager.getLastKnownLocation(s));


        }

        @Override
        public void onProviderDisabled(String s) {
            Toast toast = Toast.makeText(getApplicationContext(), "Провайдеры выключены", Toast.LENGTH_LONG);
            toast.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weathers = new ArrayList<>();
        editText = findViewById(R.id.viewEdit);
        textView = findViewById(R.id.viewText);
        button = findViewById(R.id.viewButton);
        buttonSettings = findViewById(R.id.viewButtonSettings);
        recyclerView = (RecyclerView) findViewById(R.id.posts_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        getButtonText = editText.getText().toString();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // checkEnabled();


        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getButtonText = editText.getText().toString();
                getWeather(getButtonText);
            }
        });

        getWeather(getButtonText);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkEnabled();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(getApplicationContext(), "РАзрешения не предоставлены", Toast.LENGTH_LONG);
            toast.show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 10, 2, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 10, 2, locationListener);


    }

    private void checkEnabled() {
        textView.setText("Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER));
        textView.setText("Enabled: "
                + locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER));

    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    private void showLocation(Location location) {
        textView.setText(formatLocation(location));
    }

    private String formatLocation(Location location) {
        if (location == null)
            return "NO";
        return String.format(
                "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
                location.getLatitude(), location.getLongitude(), new Date(
                        location.getTime()));
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