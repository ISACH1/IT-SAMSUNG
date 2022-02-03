package ru.kirillisachenko.rogulikegame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import GamePanel.Joystick;
import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainThread extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder holder;
    private MThread thread;
    public MainThread(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.holder = holder;
        thread = new MThread();
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    private class MThread extends Thread{
        @Override
        public void run() {

        }
    }
}
