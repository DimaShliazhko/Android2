package com.dshliazhko.android.test22;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey (autoGenerate = true)

    private int anInt = 0;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "number")
    private String number;
    @ColumnInfo(name = "pathImage")
    private String pathImage;
/*
    public Contact(int anInt,String name, String number) {
        this.anInt = anInt;
        this.name = name;
        this.number = number;
    }
*/
    public Contact(String name, String number, String pathImage) {
        // ++anInt;
        this.name = name;
        this.number = number;
        this.pathImage =  pathImage;
    }
    public Contact() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public int getAnInt() {
        return anInt;
    }

    public void setAnInt(int anInt) {
        this.anInt = anInt;
    }
}
