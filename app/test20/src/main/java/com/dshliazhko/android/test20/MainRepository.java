package com.dshliazhko.android.test20;

import android.util.Log;

public class MainRepository implements MainContract.Repository {
   final static String L= "Log";
   int i = 0;
    @Override
    public String loadMessage() {
        Log.d(L,"loadmess");
        i++;
        return "обращение к БД успешно"+i;
    }
}
