package com.example.iching.app.model;

import android.text.Editable;

import com.j256.ormlite.field.DatabaseField;

public class DivinationObject  {

    @DatabaseField(columnName = "setID", generatedId = true)
    private long id;
    @DatabaseField
    private String question;
    @DatabaseField
    private int imageSource;

    public DivinationObject() {
    }

    public DivinationObject(int imageSource, String question) {
        this.imageSource = imageSource;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public int getImageSource() {
        return imageSource;
    }
}
