package com.dshliazhko.android.test22;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements Contract.ViewActivity {
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private FragmentAddContact fragmentAddContact;
    private FragmentTransaction fragmentTransaction;
    private Intent intent;    private MyBroadcast myBroadcast;

    private ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment2 = new Fragment2();
        fragmentAddContact = new FragmentAddContact();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.view_frame2, fragment2, "fragment2");
        addBackStack();

        myBroadcast = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        this.registerReceiver(myBroadcast,intentFilter);

    }

    private void addBackStack() {

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }



    @Override
    public void showAddContactFragment(ContactAdapter contactAdapter) {
        fragmentAddContact = new FragmentAddContact(contactAdapter);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_frame2, fragmentAddContact, "TAG");
        // fragmentTransaction.add(R.id.view_frame2, fragmentAddContact);
        Log.d("Log", "открыть новый фрагмент");

        addBackStack();

        // fragmentTransaction.commit();
    }

    @Override
    public void showAddContactFragment(Contact contact) {
        fragmentAddContact = new FragmentAddContact(contact);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.view_frame2, fragmentAddContact, "TAG");
        //  fragmentTransaction.add(R.id.viewMainConteiner, fragmentAddContact);

        addBackStack();

        // fragmentTransaction.commit();


    }

    @Override
    public void callNumber(String number) {
        Log.d("Log", "запуск номера");
        intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+number));

        startActivity(intent);
    }


}