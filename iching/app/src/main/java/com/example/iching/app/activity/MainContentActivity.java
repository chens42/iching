package com.example.iching.app.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.iching.app.R;
import com.example.iching.app.service.MusicControl;


public class MainContentActivity extends IChingBaseActivity implements Animation.AnimationListener {
    private Animation animRotate;
    private ImageView icon;
    private LinearLayout mainContent;
    private Button hexagramsButton;
    private Button consultOracle;
    private Button divination;
    private Button exitButton;
    private Button about;
    private PopupWindow popupWindow ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContent = (LinearLayout) findViewById(R.id.mainContent);
        MusicControl.play(getApplicationContext(), R.raw.bg);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_spin);
        icon = (ImageView) findViewById(R.id.icon2);
        icon.setAnimation(animRotate);
        hexagramsButton = (Button) findViewById(R.id.hexagrams);
        hexagramsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainContentActivity.this, HexagramsActivity.class);
                startActivity(intent);
            }
        });
        consultOracle = (Button) findViewById(R.id.consultOracle);
        consultOracle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainContentActivity.this, ConsultOracle.class);
                startActivity(intent);
            }
        });
        divination = (Button) findViewById(R.id.divinationShow);
        divination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainContentActivity.this, DivinationActivity.class);
                startActivity(intent);
            }
        });
        about = (Button) findViewById(R.id.aboutButton);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonsClickable(false);
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(MainContentActivity.this.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.about_layout, null, false);
                popupWindow = new PopupWindow(MainContentActivity.this);
                popupWindow.setContentView(linearLayout);
                popupWindow.setBackgroundDrawable(null);
                popupWindow.showAtLocation(mainContent, Gravity.CENTER, 0, 0);
                popupWindow.update(0, 0, 500, 500);
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        setButtonsClickable(true);
                    }
                });
            }
        });
        exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitWarning();
            }
        });
    }

    private void exitWarning() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainContentActivity.this);
        alertDialogBuilder.setTitle(null);
        alertDialogBuilder
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void setButtonsClickable(boolean condition) {
        about.setClickable(condition);
        hexagramsButton.setClickable(condition);
        consultOracle.setClickable(condition);
        divination.setClickable(condition);
        exitButton.setClickable(condition);
    }

    @Override
    public void onBackPressed() {
        if(popupWindow != null ){
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
                setButtonsClickable(true);
            }
        }else {
            exitWarning();
        }
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
