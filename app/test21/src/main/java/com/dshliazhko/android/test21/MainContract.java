package com.dshliazhko.android.test21;

import android.content.SharedPreferences;

public interface MainContract {
    interface View {
        void showText(String s);

        String getEditText();
    }

    interface Presenter {
        void onClickKeepButton();

        void onClickKeepButtonOut();

        void onDestroy();

    }

    interface Repository {

        void insertPref(String s);
        String loadText();
    }
}
