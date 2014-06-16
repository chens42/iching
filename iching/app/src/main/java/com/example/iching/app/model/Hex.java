package com.example.iching.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Hex implements Parcelable,Serializable {

    @DatabaseField(id = true)
    private int id;
    @DatabaseField
    private String descriptionInEnglish;
    @DatabaseField
    private String descriptionInMandarin;
    @DatabaseField
    private int imageSource;
    @DatabaseField
    private String component;

    public Hex(int id, String descriptionInEnglish, String descriptionInMandarin, int imageSource, String component) {
        this.id = id;
        this.descriptionInEnglish = descriptionInEnglish;
        this.descriptionInMandarin = descriptionInMandarin;
        this.imageSource = imageSource;
        this.component = component;
    }

    public int getId() {
        return id;
    }

    public String getDescriptionInEnglish() {
        return descriptionInEnglish;
    }

    public String getDescriptionInMandarin() {
        return descriptionInMandarin;
    }


    public int getImageSource() {
        return imageSource;
    }

    public String getComponent() {
        return component;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descriptionInEnglish);
        dest.writeString(descriptionInMandarin);
        dest.writeInt(imageSource);
        dest.writeInt(id);
        dest.writeString(component);
    }

    public Hex() {

    }
}
