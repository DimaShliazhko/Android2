package com.dshliazhko.android.livadata;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class ModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private String s;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == MyViewModel.class){
            return (T) new MyViewModel(s);
        }
        return  null;
    }

    public ModelFactory(String s) {
        super();
        this.s = s;

    }
}
