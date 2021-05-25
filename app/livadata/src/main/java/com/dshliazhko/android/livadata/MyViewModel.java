package com.dshliazhko.android.livadata;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    //  private SharedPreferences preferences;
    // загрузка данных
    MutableLiveData<String> mutableLiveData;
    private String s;

  /*  public MyViewModel(@NonNull Application application, String s) {
        super(application);
        this.s = s;
    }

   */
    public MyViewModel(@NonNull  String s) {

        this.s = s;
    }

    public LiveData<String> getDate() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            loadDate();
        }
        return mutableLiveData;
    }

    public void loadDate() {
        //    preferences = getApplication().getSharedPreferences("Pref", Context.MODE_PRIVATE);
        //   String s =   preferences.getString("Edit1","ничего не сохранилось");
          mutableLiveData.postValue(s);

        Log.d("dima","S = "+s);
       // mutableLiveData.postValue("поворот");

    }


}
