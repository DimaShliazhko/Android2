package com.dshliazhko.android.livadata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FragmentViewModel extends ViewModel {
    private final MutableLiveData<String> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<String> getMutableLiveData() {

        return mutableLiveData;
    }
    public  void setMutableLiveData (String s){
        mutableLiveData.postValue(s);

    }
}
