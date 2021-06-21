package com.dshliazhko.android.test22;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface ContactDao {


    @Query("SELECT * From Contact")
    List<Contact> getAll();

    @Query("SELECT * From Contact where name = :name")
    Contact getContact(String name);

    @Query("SELECT * From Contact where name like '%'||:name||'%'")
    List<Contact> search(String name);


    @Insert
    void insert(Contact contact);

    @Update
    void update(Contact contact);

    @Delete
    void delete(Contact contact);

}
