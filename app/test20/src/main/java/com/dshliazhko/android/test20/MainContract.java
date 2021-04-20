package com.dshliazhko.android.test20;

public interface MainContract {
    interface View {
        void showText(String s);
    }

    interface Repository {
        String loadMessage();
    }

    interface Presenter {
        void onButtonWasClicked();

        void onDestroy();

    }
}
