package com.example.iching.app.model;

import android.text.Editable;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DivinationObject implements Serializable {

    @DatabaseField(columnName = "setID", generatedId = true)
    private long id;
    @DatabaseField
    private String question;
    @DatabaseField
    private int imageSource;
    @DatabaseField
    private int secondImageSource;
    @DatabaseField
    private Integer firstElement;
    @DatabaseField
    private Integer secondElement;
    @DatabaseField
    private Integer thirdElement;
    @DatabaseField
    private Integer fourthElement;
    @DatabaseField
    private Integer fifthElement;
    @DatabaseField
    private Integer sixthElement;

    public DivinationObject() {
    }

    public DivinationObject(int imageSource, int secondImageSource, String question, List<Integer> original) {
        this.imageSource = imageSource;
        this.secondImageSource = secondImageSource;
        this.question = question;
        if (original.size() == 6) {
            firstElement = original.get(0);
            secondElement = original.get(1);
            thirdElement = original.get(2);
            fourthElement = original.get(3);
            fifthElement = original.get(4);
            sixthElement = original.get(5);
        }
    }

    public Integer getFirstElement() {
        return firstElement;
    }

    public Integer getSecondElement() {
        return secondElement;
    }

    public Integer getFifthElement() {
        return fifthElement;
    }

    public Integer getSixthElement() {
        return sixthElement;
    }

    public Integer getThirdElement() {
        return thirdElement;
    }

    public Integer getFourthElement() {
        return fourthElement;
    }

    public int getSecondImageSource() {
        return secondImageSource;
    }

    public String getQuestion() {
        return question;
    }

    public int getImageSource() {
        return imageSource;
    }

    public long getId() {
        return id;
    }
}
