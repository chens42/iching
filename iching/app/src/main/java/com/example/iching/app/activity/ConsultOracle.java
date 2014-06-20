package com.example.iching.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.iching.app.R;
import com.example.iching.app.db.DatabaseHelper;
import com.example.iching.app.db.DivinationDatabaseHelper;
import com.example.iching.app.model.DivinationObject;
import com.example.iching.app.model.Hex;

public class ConsultOracle extends IChingBaseActivity {
    private int counter = 0;
    private Button tossButton;
    private Button saveButton;
    private DivinationDatabaseHelper divinationDatabaseHelper = new DivinationDatabaseHelper(this);
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private String findHexagrams = "";
    private ImageView originalImageView = null;
    private ImageView relativeImageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consult_oracle_layout);
        tossButton = (Button) findViewById(R.id.tossButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        tossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < 6) {
                    tossButton.setText("toss");
                    tossAction();
                    counter++;
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.consultOracleEditTextView)).getText().toString();

                if (!question.isEmpty()) {
                    Hex hex = (Hex) databaseHelper.getPostDAO().queryForEq("component", findHexagrams).get(0);
                    DivinationObject divinationObject = new DivinationObject(hex.getImageSource(), question);
                    divinationDatabaseHelper.getPostDAO().create(divinationObject);
                    saveButton.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(getApplicationContext(), "enter the question to save", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void tossAction() {
        int randomNum = 0 + (int) (Math.random() * 4);
        int imageSource = 0;
        int relativeImageSource = 0;
        switch (randomNum) {
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
                relativeImageSource = R.drawable.yin_relating;
                break;
            case 3:
                imageSource = R.drawable.yang_changing;
                relativeImageSource = R.drawable.yang_relating;
                break;
        }
        findHexagrams = findHexagrams + randomNum % 2;
        switch (counter) {
            case 0:
                saveButton.setVisibility(View.INVISIBLE);
                setRelatingVisibility(View.INVISIBLE);
                setOriginalInvisible();

                originalImageView = (ImageView) findViewById(R.id.cast1);
                relativeImageView = (ImageView) findViewById(R.id.relative1);
                break;
            case 1:
                originalImageView = (ImageView) findViewById(R.id.cast2);
                relativeImageView = (ImageView) findViewById(R.id.relative2);
                break;
            case 2:
                originalImageView = (ImageView) findViewById(R.id.cast3);
                relativeImageView = (ImageView) findViewById(R.id.relative3);
                break;
            case 3:
                originalImageView = (ImageView) findViewById(R.id.cast4);
                relativeImageView = (ImageView) findViewById(R.id.relative4);
                break;
            case 4:
                originalImageView = (ImageView) findViewById(R.id.cast5);
                relativeImageView = (ImageView) findViewById(R.id.relative5);
                break;
            case 5:
                originalImageView = (ImageView) findViewById(R.id.cast6);
                relativeImageView = (ImageView) findViewById(R.id.relative6);
                counter = -1;
                tossButton.setText("another toss");
                saveButton.setVisibility(View.VISIBLE);
                break;
        }

        originalImageView.setVisibility(View.VISIBLE);
        originalImageView.setImageResource(imageSource);
        relativeImageView.setImageResource(relativeImageSource);
        if (counter == -1) {
            setRelatingVisibility(View.VISIBLE);
        }

    }

    private void setOriginalInvisible() {
        originalImageView = (ImageView) findViewById(R.id.cast2);
        originalImageView.setVisibility(View.INVISIBLE);
        originalImageView = (ImageView) findViewById(R.id.cast3);
        originalImageView.setVisibility(View.INVISIBLE);
        originalImageView = (ImageView) findViewById(R.id.cast4);
        originalImageView.setVisibility(View.INVISIBLE);
        originalImageView = (ImageView) findViewById(R.id.cast5);
        originalImageView.setVisibility(View.INVISIBLE);
        originalImageView = (ImageView) findViewById(R.id.cast6);
        originalImageView.setVisibility(View.INVISIBLE);
    }

    private void setRelatingVisibility(int visibility) {
        relativeImageView = (ImageView) findViewById(R.id.relative1);
        relativeImageView.setVisibility(visibility);
        relativeImageView = (ImageView) findViewById(R.id.relative2);
        relativeImageView.setVisibility(visibility);
        relativeImageView = (ImageView) findViewById(R.id.relative3);
        relativeImageView.setVisibility(visibility);
        relativeImageView = (ImageView) findViewById(R.id.relative4);
        relativeImageView.setVisibility(visibility);
        relativeImageView = (ImageView) findViewById(R.id.relative5);
        relativeImageView.setVisibility(visibility);
        relativeImageView = (ImageView) findViewById(R.id.relative6);
        relativeImageView.setVisibility(visibility);
    }

}
