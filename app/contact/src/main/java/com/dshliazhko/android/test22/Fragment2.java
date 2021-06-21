package com.dshliazhko.android.test22;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Fragment2 extends Fragment {

    private Contract.Presenter presenter;
    private ImageButton imageButton;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private List<Contact> listContact;
    private EditText searchText;
    private Context context;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        imageButton = view.findViewById(R.id.viewAddContactClick);
        searchText = view.findViewById(R.id.searchText);
        initImageButton();
        presenter = new Presenter((Contract.ViewActivity) context);
        presenter.createDb();
        initRecyclerView(view);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //listContact = App.getInstance().getDatabase().contactDao().search(searchText.getText().toString());
                presenter.search(searchText.getText().toString(), contactAdapter);
                //  presenter.getlistContact(contactAdapter);
                //contactAdapter.setList(listContact);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // listContact = App.getInstance().getDatabase().contactDao().getAll();
        // contactAdapter.setList(listContact);

        presenter.getlistContact(contactAdapter);
        //    presenter.setContactAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();

        return view;
    }

    private void initImageButton() {

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             //   getActivity().startService(new Intent(getContext(),MyService.class));
                presenter.clickAddButton(contactAdapter);

            }
        });

    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.viewRecyclecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        contactAdapter = new ContactAdapter((Presenter) presenter, context);
        // contactAdapter = new ContactAdapter((Presenter) presenter,listContact);
        presenter.getlistContact(contactAdapter);
        recyclerView.setAdapter(contactAdapter);

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast toast;
        switch (item.getItemId()) {
            case 1:
                Contact contact = contactAdapter.remuve();
                presenter.remuve(contact);
                presenter.getlistContact(contactAdapter);
                contactAdapter.notifyDataSetChanged();
                toast = Toast.makeText(getActivity(), "нажато " + item.getItemId(), Toast.LENGTH_LONG);
                toast.show();
                break;
            case 2:
                Log.d("Log", "контакт" + contactAdapter.reNameContact().getName().toString());
                presenter.clickAddButton(contactAdapter.reNameContact());
                Contact contact2 = contactAdapter.reNameContact();
                presenter.update(contact2);
                //    presenter.getlistContact(contactAdapter);

                toast = Toast.makeText(getActivity(), "нажато " + item.getItemId(), Toast.LENGTH_LONG);
                toast.show();
                break;
        }
        return super.onContextItemSelected(item);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().stopService(new Intent(getContext(),MyService.class));
    }
}
