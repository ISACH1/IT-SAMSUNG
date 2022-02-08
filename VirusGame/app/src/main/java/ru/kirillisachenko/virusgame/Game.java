package ru.kirillisachenko.virusgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.Hero;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    GameDisplay gameDisplay;
    MathGenerator mathGenerator;
    SurfaceHolder holder;
    Hero hero;
    Joystick joystick;
    Bitmap background;
    Enemy enemy;
    List<Enemy> enemyArrayList;
    private int joystickPointerId = 0;
    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.tile1); //TODO: NORMALNIY BSCKGROUND DUREN
        joystick = new Joystick(300, 1000, 70, 120);
        hero = new Hero(getContext(), getWidth() / 2, getHeight() / 2);
        enemyArrayList = new ArrayList<>();
        mathGenerator = new MathGenerator();
        enemyArrayList.add(new Enemy(0, 0, getContext(), hero ));
        enemyArrayList.add( new Enemy( getWidth(), getHeight(), getContext(), hero ));
        this.holder = holder;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, hero);
        GameThread gameThread = new GameThread();
        gameThread.start();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.isPressed()) {
                    // Joystick was pressed before this event -> cast spell
                } else if (joystick.isInJoystick( event.getX(), ( event.getY()))) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store pointer id
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setPressed(true);
                } else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell

                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.isPressed()) {
                    // Joystick was pressed previously and is now moved
                    joystick.setActuator( event.getX(),  event.getY());
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // joystick pointer was let go off -> setIsPressed(false) and resetActuator()
                    joystick.setPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }

        return super.onTouchEvent(event);

    }

    private  class GameThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running){
                Canvas canvas = holder.lockCanvas();
                canvas.drawBitmap(background, null , new Rect(0, 0, getWidth(), getHeight()), null);
                try {
                    drawFrames(canvas, gameDisplay);
                    update();
                }catch (Exception e){}
                finally { holder.unlockCanvasAndPost(canvas); }
            }
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }




    public void update(){
        joystick.update();
        hero.update(joystick);
        for (Enemy e: enemyArrayList){
            e.update();
        }
        gameDisplay.update();
    }

    public void drawFrames(Canvas canvas, GameDisplay gameDisplay){
        hero.draw(canvas, gameDisplay);
        joystick.draw(canvas);
        for (Enemy e: enemyArrayList){
            e.draw(canvas, gameDisplay);
        }
        Log.d("RRR", "RISUU FRAMES");
    }


}
