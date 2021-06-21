package com.dshliazhko.android.test22;

import java.util.ArrayList;
import java.util.List;

public class ListContact {
    private static ListContact listContact;
    private static List<Contact> contacts  = new ArrayList<>();

    private ListContact() {

    }

    public static ListContact getListContact() {
        if (listContact == null) {
            listContact = new ListContact();
        }
        return listContact;
    }

    public void addToList(Contact contact) {
        contacts.add(contact);
    }
    public List<Contact> getAll() {
        return this.contacts;
    }

}

