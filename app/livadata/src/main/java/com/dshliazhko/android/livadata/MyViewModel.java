package com.dshliazhko.android.livadata;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {


    // загрузка данных
    MutableLiveData<String> mutableLiveData;

    public void setMutableLiveData(String s) {
       mutableLiveData.setValue(s);
    }

    private String s;



    public LiveData<String> getDate() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadDate();
        }
        return mutableLiveData;
    }

    public void loadDate() {

        mutableLiveData.postValue(s);
        Log.d("dima", "S = " + s);

    }


}
