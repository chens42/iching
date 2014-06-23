package com.example.iching.app.service;


import android.content.Context;
import android.media.MediaPlayer;

public class MusicControl {
    //singleton only one mediaPlayer
    private static MediaPlayer mediaPlayer = null;

    public static void play(Context context, int resource) {
        if (mediaPlayer == null) {
            playMusic(context, resource);
        }
    }

    public static void pause(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public static void resume(Context context, int resource) {
        if (mediaPlayer == null) {
            playMusic(context, resource);
        } else {
            mediaPlayer.start();
        }
    }

    public static void stop(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private static void playMusic(Context context, int resource) {
        mediaPlayer = MediaPlayer.create(context, resource);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

}