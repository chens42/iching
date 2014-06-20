package com.example.iching.app.activity;

import android.app.Activity;

import com.example.iching.app.service.MusicControl;

public abstract class IChingBaseActivity extends Activity {
    @Override
    protected void onPause() {
        super.onPause();
//        MusicControl.pause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MusicControl.resume(this, R.raw.bg);

    }


}
