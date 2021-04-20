package com.dshliazhko.android.test21;

import android.content.SharedPreferences;
import android.util.Log;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private MainContract.Repository repository;
    private String mess;
    private SharedPreferences preferences;

    public MainPresenter(MainContract.View view,SharedPreferences preferences) {
        this.view = view;
        this.repository = new Repository(preferences);
        this.preferences = preferences;
    }


    //View сообщает, что кнопка была нажата
    @Override
    public void onClickKeepButton() {
        mess = view.getEditText();
        repository.insertPref(mess);
    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onClickKeepButtonOut() {
        mess = repository.loadText();
        view.showText(mess);
    }

    @Override
    public void onDestroy() {
        Log.d("Log", "onDestroy()");
    }
}
