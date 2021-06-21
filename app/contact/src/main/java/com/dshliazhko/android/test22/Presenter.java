package com.dshliazhko.android.test22;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class Presenter extends Activity implements Contract.Presenter {

    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 0;
    private Contract.ViewActivity view;
    private Disposable disposable;
    private ContactAdapter contactAdapter;

    public Presenter(Contract.ViewActivity view) {
        this.view = view;

    }

    public Presenter(Fragment fragment) {
        this.view = view;
    }

    @Override
    public void setContactAdapter(ContactAdapter contactAdapter) {
        this.contactAdapter = contactAdapter;
    }

    @Override
    public void createDb() {
        DataBase db = App.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();


        disposable = Flowable.just(new ObservableOnSubscribe<List<Contact>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Contact>> emitter) throws Exception {

                emitter.onNext(contactDao.getAll());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ObservableOnSubscribe<List<Contact>>>() {
                    @Override
                    public void accept(ObservableOnSubscribe<List<Contact>> listObservableOnSubscribe) throws Exception {

                    }
                })
        ;

    }

    @Override

    public void clickAddButton(ContactAdapter contactAdapter) {
        view.showAddContactFragment(contactAdapter);
    }


    @Override
    public void getlistContact(ContactAdapter contactAdapter) {
        DataBase db = App.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();

        disposable = Observable.create(new ObservableOnSubscribe<List<Contact>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Contact>> emitter) throws Exception {

                emitter.onNext(contactDao.getAll());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> list) throws Exception {
                        contactAdapter.setList(list);
                    }
                });

    }

    @Override
    public void getlistContact() {
        DataBase db = App.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();

        disposable = Observable.create(new ObservableOnSubscribe<List<Contact>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Contact>> emitter) throws Exception {
                emitter.onNext(contactDao.getAll());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> list) throws Exception {
                        contactAdapter.setList(list);

                    }
                });

    }

    @Override
    public void insert(Contact contact) {
        DataBase db = App.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();

        disposable = Observable
                .create(new ObservableOnSubscribe<Contact>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Contact> emitter) throws Exception {
                        contactDao.insert(contact);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Contact>() {
                    @Override
                    public void accept(Contact contact) throws Exception {

                    }
                });

    }

    @Override
    public void update(Contact contact) {
        DataBase db = App.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();

        disposable = Observable
                .create(new ObservableOnSubscribe<Contact>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Contact> emitter) throws Exception {
                        contactDao.update(contact);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Contact>() {
                    @Override
                    public void accept(Contact contact) throws Exception {

                    }
                });
    }

    @Override
    public void remuve(Contact contact) {
        DataBase db = App.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();

        disposable = Observable.create(new ObservableOnSubscribe<Contact>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Contact> emitter) throws Exception {
                contactDao.delete(contact);


            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Contact>() {
                    @Override
                    public void accept(Contact contact) throws Exception {

                    }
                });
    }

    @Override
    public void search(String s, ContactAdapter contactAdapter) {
        DataBase db = App.getInstance().getDatabase();
        ContactDao contactDao = db.contactDao();

        disposable = Observable.create(new ObservableOnSubscribe<List<Contact>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<List<Contact>> emitter) throws Exception {
                emitter.onNext(contactDao.search(s));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contact>>() {
                    @Override
                    public void accept(List<Contact> list) throws Exception {
                        contactAdapter.setList(list);
                    }
                });
    }

    @Override
    public void clickAddButton(Contact contact) {
        view.showAddContactFragment(contact);
    }

    @Override
    public void clickCallButton(String number) {
        view.callNumber(number);
    }

    @Override
    public void clickImagineView(View view) {

    }

    @Override
    public void clickSaveButton() {

    }


}
