package com.example.iching.app.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iching.app.db.DatabaseHelper;
import com.example.iching.app.model.Hex;
import com.example.iching.app.R;

import java.util.ArrayList;
import java.util.List;

public class HexagramsDisplayActivity extends IChingBaseActivity  {
    public static final String ID_IN = "id";
    private List<Hex> hexList;
    private DatabaseHelper helper;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hexgrams_display);

        helper = new DatabaseHelper(getApplicationContext());
        hexList = helper.getPostDAO().queryForAll();

        itemId = getIntent().getIntExtra(ID_IN, -1);
        ImageView hexImageView = (ImageView) findViewById(R.id.image_source);
        hexImageView.setImageResource(hexList.get(itemId).getImageSource());
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(hexList.get(itemId).getDescriptionInEnglish());
    }
}
