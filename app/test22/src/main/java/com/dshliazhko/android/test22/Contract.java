package com.dshliazhko.android.test22;

import android.view.View;

public interface Contract {
    interface ViewActivity {

        void showAddContactFragment(ContactAdapter contactAdapter);

        void showAddContactFragment(Contact contact);

        void callNumber(String number);


    }

    interface ViewFragment {
        void showContact();


    }

    interface Presenter {
        void createDb();
        void clickAddButton(ContactAdapter contactAdapter);
        void getlistContact(ContactAdapter contactAdapter);
        void getlistContact();
        void insert(Contact contact);
        void update(Contact contact);
        void remuve( Contact contact);
        void search( String s, ContactAdapter contactAdapter);
        void clickAddButton(Contact contact);
        void clickCallButton(String number);

        void setContactAdapter(ContactAdapter contactAdapter);
        void clickImagineView(View view);
        void clickSaveButton();
    }
}
