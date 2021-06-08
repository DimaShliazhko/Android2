package com.dshliazhko.android.livadata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class Fragment2 extends Fragment {
    private TextView textView;
    private FragmentViewModel fragmentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment2, container, false);
        textView = view.findViewById(R.id.viewFr2Text);
        fragmentViewModel = ViewModelProviders.of(getActivity()).get(FragmentViewModel.class);
        fragmentViewModel.getMutableLiveData().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        return view;
    }
}
