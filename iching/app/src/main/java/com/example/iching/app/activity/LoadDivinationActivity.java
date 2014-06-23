package com.example.iching.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iching.app.R;
import com.example.iching.app.model.DivinationObject;

public class LoadDivinationActivity extends IChingBaseActivity {
    public static final String DIVINATION = "divination";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_divination);
        DivinationObject divinationObject = (DivinationObject) getIntent().getSerializableExtra(DIVINATION);
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(divinationObject.getQuestion());
        ImageView castOne = (ImageView) findViewById(R.id.cast1);
        ImageView castTwo = (ImageView) findViewById(R.id.cast2);
        ImageView castThree = (ImageView) findViewById(R.id.cast3);
        ImageView castFour = (ImageView) findViewById(R.id.cast4);
        ImageView castFive = (ImageView) findViewById(R.id.cast5);
        ImageView castSix = (ImageView) findViewById(R.id.cast6);

        ImageView relatingOne = (ImageView) findViewById(R.id.relative1);
        ImageView relatingTwo = (ImageView) findViewById(R.id.relative2);
        ImageView relatingThree = (ImageView) findViewById(R.id.relative3);
        ImageView relatingFour = (ImageView) findViewById(R.id.relative4);
        ImageView relatingFive = (ImageView) findViewById(R.id.relative5);
        ImageView relatingSix = (ImageView) findViewById(R.id.relative6);

        original(castOne, relatingOne, divinationObject.getFirstElement());
        original(castTwo, relatingTwo, divinationObject.getSecondElement());
        original(castThree, relatingThree, divinationObject.getThirdElement());
        original(castFour, relatingFour, divinationObject.getFourthElement());
        original(castFive, relatingFive, divinationObject.getFifthElement());
        original(castSix, relatingSix, divinationObject.getSixthElement());
    }

    private void original(ImageView cast, ImageView relating, Integer element) {
        int imageSource = 0;
        int relativeImageSource = 0;
        switch (element) {
            case 0:
                imageSource = R.drawable.yin;
                relativeImageSource = R.drawable.yin;
                break;
            case 1:
                imageSource = R.drawable.yang;
                relativeImageSource = R.drawable.yang;
                break;
            case 2:
                imageSource = R.drawable.yin_changing;
                relativeImageSource = R.drawable.yang_relating;
                break;
            case 3:
                imageSource = R.drawable.yang_changing;
                relativeImageSource = R.drawable.yin_relating;
                break;
        }
        cast.setImageResource(imageSource);
        cast.setVisibility(View.VISIBLE);
        relating.setImageResource(relativeImageSource);
        relating.setVisibility(View.VISIBLE);
    }
}
