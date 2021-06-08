package com.dshliazhko.android.livadata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;


public class Fragment1 extends Fragment {
    private EditText editText;
    private FragmentViewModel fragmentViewModel;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment1, container, false);
        editText = view.findViewById(R.id.viewFr1Edit);
        button = view.findViewById(R.id.viewFr1Button);
        fragmentViewModel = ViewModelProviders.of(getActivity()).get(FragmentViewModel.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentViewModel.setMutableLiveData(editText.getText().toString());
            }
        });

        return view;
    }
}
