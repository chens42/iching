package com.example.iching.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iching.app.R;
import com.example.iching.app.service.MusicControl;


public class MainContentActivity extends IChingBaseActivity implements Animation.AnimationListener {
    private Animation animRotate;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MusicControl.play(getApplicationContext(), R.raw.bg);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_spin);
        icon = (ImageView) findViewById(R.id.icon2);
        icon.setAnimation(animRotate);
        Button hexagramsButton = (Button) findViewById(R.id.hexagrams);
        hexagramsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainContentActivity.this, HexagramsActivity.class);
                startActivity(intent);
            }
        });
        Button consultOracle = (Button) findViewById(R.id.consultOracle);
        consultOracle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainContentActivity.this, ConsultOracle.class);
                startActivity(intent);
            }
        });
        Button divination = (Button) findViewById(R.id.divinationShow);
        divination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainContentActivity.this, DivinationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void finish() {
        MusicControl.stop(this);
        super.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }


}
