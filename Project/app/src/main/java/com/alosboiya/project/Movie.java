package com.alosboiya.project;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by HP on 7/19/2017.
 */

public class Movie implements Serializable  , Parcelable {
    private String title;
    private int id;
    private String poster_pass;
    private String over_view;
    private String rate;
    private String pass;

    protected Movie(Parcel in) {
        title = in.readString();
        id = in.readInt();
        poster_pass = in.readString();
        over_view = in.readString();
        pass = in.readString();
        rate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setrate(String rate) {
        this.rate = rate;
    }

    public String getrate() {
        return rate;
    }

    public void setpass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_pass() {
        return poster_pass;
    }

    public void setPoster_pass(String poster_pass) {
        this.poster_pass = poster_pass;
    }

    public String getOver_view() {
        return over_view;
    }

    public void setOver_view(String over_view) {
        this.over_view = over_view;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(id);
        dest.writeString(poster_pass);
        dest.writeString(over_view);
        dest.writeString(pass);
        dest.writeString(rate);
    }
}

