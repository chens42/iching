package com.example.iching.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
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

/*    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeInt(firstElement);
        dest.writeInt(secondElement);
        dest.writeInt(thirdElement);
        dest.writeInt(fourthElement);
        dest.writeInt(fifthElement);
        dest.writeInt(sixthElement);
    }*/
}
