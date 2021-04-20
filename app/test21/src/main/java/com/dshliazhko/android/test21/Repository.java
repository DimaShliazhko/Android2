package com.dshliazhko.android.test21;

import android.content.SharedPreferences;
import android.util.Log;

public class Repository implements MainContract.Repository {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String mess;

    public Repository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    @Override
    public void insertPref(String s) {
        Log.d("Dima", s);
        editor.putString("insert", s);
        editor.apply();
    }

    @Override
    public String loadText() {
        mess = sharedPreferences.getString("insert", "ничего не вставилось");
        return mess;
    }
}
