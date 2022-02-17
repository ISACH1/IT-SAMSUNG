package ru.kirillisachenko.virusgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import ru.kirillisachenko.virusgame.gamecontrollers.AutoFireButton;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.GameObject;
import ru.kirillisachenko.virusgame.gameobjects.PaneDoctor;
import ru.kirillisachenko.virusgame.gameobjects.Hero;
import ru.kirillisachenko.virusgame.map.Room1;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    AutoFireButton autoFireButton;
    Room1 room1;
    GameDisplay gameDisplay;
    MathGenerator mathGenerator;
    SurfaceHolder holder;
    Hero hero;
    Joystick joystick1;
    ArrayList<GameObject> enemyArrayList;

    private int joystickPointerId1 = 0;
    private int autoFireButtonPointerID = 0;

    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        room1 = new Room1(getWidth() / 2, getHeight() / 2, getContext());
        joystick1 = new Joystick(250, 900 - 200, 175, 200, getContext());
        autoFireButton = new AutoFireButton(getWidth() - 400, 1100 - 225, 185, getContext());
        hero = new Hero(getContext(), room1.getXPoint(1, 1), room1.getYPoint(1, 1), joystick1);
        enemyArrayList = new ArrayList<>();
        mathGenerator = new MathGenerator();
        enemyArrayList.add(new PaneDoctor(0, 0, getContext(), hero));
        enemyArrayList.add(new PaneDoctor(100, 100, getContext(), hero));
        enemyArrayList.add(new PaneDoctor(room1.getXPoint(1, 5), room1.getYPoint(1, 5), getContext(), hero));
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
                if (joystick1.isPressed()) {
                    // Joystick was pressed before this event -> cast spell
                    if (autoFireButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        autoFireButtonPointerID = event.getPointerId(event.getActionIndex());
                        autoFireButton.setPressed(true);
                    }
                } else if (joystick1.isInJoystick(event.getX(event.getActionIndex()), (event.getY(event.getActionIndex())))) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store pointer id
                    joystickPointerId1 = event.getPointerId(event.getActionIndex());
                    joystick1.setPressed(true);
                    joystick1.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                }
                else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                    if (autoFireButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        autoFireButton.setPressed(true);
                        autoFireButtonPointerID = event.getPointerId(event.getActionIndex());
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick1.isPressed()) {
                    // Joystick was pressed previously and is now moved
                    joystick1.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId1 == event.getPointerId(event.getActionIndex())) {
                    // joystick pointer was let go off -> setIsPressed(false) and resetActuator()
                    joystick1.setPressed(false);
                    joystick1.resetActuator();
                }
                if (autoFireButtonPointerID == event.getPointerId(event.getActionIndex())) {
                    autoFireButton.setPressed(false);
                }
                return true;
        }

     return true;
    }

    private  class GameThread extends Thread {
        private volatile boolean running = true;

        @Override
        public void run() {
            while (running){
                Canvas canvas = holder.lockCanvas();

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
        joystick1.update();
        hero.update();
        for (GameObject e: enemyArrayList){
            e.update();
        }
        gameDisplay.update();
    }

    public void drawFrames(Canvas canvas, GameDisplay gameDisplay){
        room1.draw(canvas, gameDisplay);
        hero.draw(canvas, gameDisplay);
        joystick1.draw(canvas);
        autoFireButton.draw(canvas);
        for (GameObject e: enemyArrayList){
            e.draw(canvas, gameDisplay);
        }
        Log.d("RRR", "RISUU FRAMES");
    }


}
