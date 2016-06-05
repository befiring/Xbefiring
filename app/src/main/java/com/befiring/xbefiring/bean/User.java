package com.befiring.xbefiring.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/3.
 */
public class User implements Parcelable,Serializable{

    public String name;
    public String password;

    public User() {
    }
    public User(String name,String password){
        this.name=name;
        this.password=password;

    }

    public User(Parcel in) {
        this.name = in.readString();
        this.password= in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(password);
    }

    public static final Parcelable.Creator<User> CREATOR=new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
