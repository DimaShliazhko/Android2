package com.dshliazhko.android.test22;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

public class FragmentAddContact extends Fragment {
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 1;
    private static final int Pick_image = 2;
    private Contact contact;
    private Contact contactUpdate;
    private EditText name;
    private EditText number;
    private Button buttonSave;
    private Presenter presenter;
    private ContactDao contactDao;
    private ImageView imageView;
    private ContactAdapter contactAdapter;
    private String pathImage;


    private Uri imageUri;


    public FragmentAddContact(Contact contact) {
        this.contactUpdate = contact;

    }

    public FragmentAddContact() {


    }


    public FragmentAddContact(ContactAdapter contactAdapter) {
        this.contactAdapter = contactAdapter;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                    // readContacts();
                } else {
                    // permission denied
                }
                return;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_coctact, null);
        name = view.findViewById(R.id.viewFrAddName);
        number = view.findViewById(R.id.viewFrAddNum);
        imageView = view.findViewById(R.id.viewFrAddImag);
        buttonSave = view.findViewById(R.id.viewSaмeButton);

        Context context = getActivity();
        presenter = new Presenter((Contract.ViewActivity) context);
        //  contactDao = presenter.createDb();
        presenter.createDb();

        if (contactUpdate != null) {

            name.setText(contactUpdate.getName());
            number.setText(contactUpdate.getNumber());
            pathImage = contactUpdate.getPathImage();
            loadImage(pathImage);


        }
        imageView.setClickable(true);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // presenter.clickImagineView(view);
                Log.d("Log", "Click Imageme");
                int permmissionStatus = ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
                if (permmissionStatus == PackageManager.PERMISSION_GRANTED) {
                    loadGallery();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ_EXTERNAL_STORAGE);
                }


            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                contact = new Contact();
                contact.setName(name.getText().toString());
                contact.setNumber(number.getText().toString());
                if (imageUri == null) {
                    //  imageUri= (R.drawable.ic_baseline_cloud_download_24);
                    contact.setPathImage(getResources().getDrawable(R.drawable.ic_baseline_cloud_download_24).toString());
                } else {
                    contact.setPathImage(imageUri.toString());
                }

                if (contactUpdate != null) {
                    contact.setAnInt(contactUpdate.getAnInt());
                    Log.d("Log", "contactUpdate " + contactUpdate.getName().toString());
                    presenter.update(contact);
                } else {
                    presenter.insert(contact);

                }
              //  presenter.getlistContact(contactAdapter);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.view_frame2, getFragmentManager().findFragmentByTag("fragment2"));
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    private void loadGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, Pick_image);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Pick_image:
                if (resultCode == Activity.RESULT_OK) {
                    try {

                        imageUri = data.getData();

                        loadImage(imageUri.toString());

                    } finally {

                    }
                }
        }
    }

    private void loadImage(String imageUri) {
        Glide
                .with(this)
                .load(imageUri)
                // .load("https://i.imgur.com/DvpvklR.png")
                //.load(R.mipmap.ic_launcher)
                .placeholder(R.drawable.ic_baseline_cloud_download_24)
                .fitCenter()
                .into(imageView)
        ;

    }
}
