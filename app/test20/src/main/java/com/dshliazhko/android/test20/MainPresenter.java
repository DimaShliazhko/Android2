package com.dshliazhko.android.test20;

import android.util.Log;

public class MainPresenter implements MainContract.Presenter {
    private final static String L = "Log";

    //Компоненты MVP приложения
    private MainContract.View view;
    private MainContract.Repository repository;
    private String message;

    //Обрати внимание на аргументы конструктора - мы передаем экземпляр View, а Repository просто создаём конструктором.

    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.repository = new MainRepository();
        Log.d(L, "Constructor");
    }

    @Override
    //View сообщает, что кнопка была нажата
    public void onButtonWasClicked() {
        message = repository.loadMessage();
        view.showText(message);
        Log.d(L, "onButtonWasClicked()");
    }

    //Вызываем у Presenter метод onDestroy, чтобы избежать утечек контекста и прочих неприятностей.

    @Override
    public void onDestroy() {
        /**
         * Если бы мы работали например с RxJava, в этом классе стоило бы отписываться от подписок
         * Кроме того, при работе с другими методами асинхронного андроида,здесь мы боремся с утечкой контекста
         */

        Log.d(L, "onDestroy()");
    }
}
