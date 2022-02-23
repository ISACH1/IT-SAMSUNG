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
import java.util.ListIterator;

import ru.kirillisachenko.virusgame.gamecontrollers.Button;
import ru.kirillisachenko.virusgame.gamecontrollers.HealthBar;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.GameObject;
import ru.kirillisachenko.virusgame.gameobjects.pane_doctor_package.PaneDoctor;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;
import ru.kirillisachenko.virusgame.map.Room1;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    HealthBar healthBar;
    Button AutoFireButton, AbilityButton;
    Room1 room1;
    GameDisplay gameDisplay;
    MathGenerator mathGenerator;
    SurfaceHolder holder;
    Hero hero;
    Joystick joystick1;
    ArrayList<GameObject> enemyArrayList;
    ArrayList <Bullet> heroBullets;
    ArrayList <Bullet> enemyBullets;


    private int joystickPointerId1 = 0;
    private int autoFireButtonPointerID = 0;
    private int AbilityButtonID = 0;
    private  static int  count = 0;

    public Game(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        room1 = new Room1(getWidth() / 2, getHeight() / 2, getContext());
        joystick1 = new Joystick( getWidth() - (getWidth() - 250), getHeight() - 225, 175, 200, getContext());
        AutoFireButton = new Button(getWidth() - 400, getHeight() - 150, 185, getContext(), 0);
        AbilityButton = new Button(getWidth() - 200, getHeight() - 300, 185, getContext(), 1) ;
        hero = new Hero(getContext(), room1.getXPoint(40, 40), room1.getYPoint(40, 40), joystick1);
         healthBar = new HealthBar(0, 0, getContext(), hero);
        enemyArrayList = new ArrayList<>();
        heroBullets = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        mathGenerator = new MathGenerator();
        for (int i = 0; i <8 ; i++) {
            enemyArrayList.add(new PaneDoctor(room1.getXPoint(mathGenerator.getRandom(0, 100), mathGenerator.getRandom(0, 100)), room1.getYPoint(mathGenerator.getRandom(0, 100), mathGenerator.getRandom(0, 100)), getContext(), hero));
        }
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
                if (AutoFireButton.isPressed()){
                    if (joystick1.isInJoystick(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))){

                        joystickPointerId1 = event.getPointerId(event.getActionIndex());
                        joystick1.setPressed(true);
                        joystick1.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                    }
                }
                if (joystick1.isPressed()) {
                    // Joystick was pressed before this event -> cast spell
                    if (AutoFireButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        autoFireButtonPointerID = event.getPointerId(event.getActionIndex());
                        AutoFireButton.setPressed(true);
                    }
                    if (AbilityButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        AbilityButtonID = event.getPointerId(event.getActionIndex());
                        AbilityButton.setPressed(true);
                    }
                } else if (joystick1.isInJoystick(event.getX(event.getActionIndex()), (event.getY(event.getActionIndex())))) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store pointer id

                    joystickPointerId1 = event.getPointerId(event.getActionIndex());
                    joystick1.setPressed(true);
                    joystick1.setActuator(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                }
                else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                    if (AutoFireButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {

                        AutoFireButton.setPressed(true);
                        autoFireButtonPointerID = event.getPointerId(event.getActionIndex());
                    }
                    if (AbilityButton.isInButton(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()))) {
                        AbilityButtonID = event.getPointerId(event.getActionIndex());
                        AbilityButton.setPressed(true);
                    }
                }
                count++;
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.d("RRR###", "MOVE");
                    if (joystick1.isPressed()) {
                        float x = event.getX();
                        float y = event.getY();
                        if(count>1){
                            int i1 = event.findPointerIndex(event.getPointerId(0));
                            int i2 = event.findPointerIndex(event.getPointerId(1));
                            x = event.getX(i1);
                            y = event.getY(i1);
                            if (x > getWidth()/2){
                                x = event.getX(i2);
                                y = event.getY(i2);
                            }
                        }
                        // Joystick was pressed previously and is now moved
                        joystick1.setActuator(x, y);
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
                    AutoFireButton.setPressed(false);
                }
                if (AbilityButtonID == event.getPointerId(event.getActionIndex())) {
                    AbilityButton.setPressed(false);
                }

                count--;
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

        if (AutoFireButton.isPressed()){
            if(hero.canAttack()) {
                if (!enemyArrayList.isEmpty()){
                heroBullets.add(hero.attack(enemyArrayList));
                }
            }
        }
        for (Bullet bullet : heroBullets ){
            bullet.update();
            if (room1.getXPoint(1, 1) >= bullet.getxPosition() - bullet.getSize() || room1.getYPoint(1, 1) >= bullet.getyPosition() - bullet.getSize()
                    || room1.getXPoint(98, 98) <= bullet.getxPosition() - bullet.getSize() || room1.getYPoint(98, 98) <= bullet.getyPosition() - bullet.getSize()) enemyBullets.remove(bullet);
        }
        for (Bullet bullet : enemyBullets ){
            bullet.update();
            if (room1.getXPoint(1, 1) >= bullet.getxPosition() - bullet.getSize() || room1.getYPoint(1, 1) >= bullet.getyPosition() - bullet.getSize()
                || room1.getXPoint(98, 98) <= bullet.getxPosition() - bullet.getSize() || room1.getYPoint(98, 98) <= bullet.getyPosition() - bullet.getSize()) enemyBullets.remove(bullet);
        }
        for (GameObject e: enemyArrayList){
            e.update();
            if (e.canAttack()){
                enemyBullets.add(e.attack());
            }
        }
        ListIterator<GameObject> EnemyListIterator = enemyArrayList.listIterator();
        while (EnemyListIterator.hasNext()){
            GameObject enemy = EnemyListIterator.next();
            if (enemy.getHealthPoint() == 0){
                enemyArrayList.remove(enemy);
            }
            ListIterator<Bullet> HeroBulletIterator = heroBullets.listIterator();
            while (HeroBulletIterator.hasNext()){
                Bullet heroBullet = HeroBulletIterator.next();
                if (heroBullet.Collision(heroBullet, enemy )){
                    heroBullets.remove(heroBullet);
                    enemy.setHealthPoint(enemy.getHealthPoint() - 1);
                    break;
                }
            }
            ListIterator<Bullet> EnemyBulletIterator = enemyBullets.listIterator();
            while (EnemyBulletIterator.hasNext()){
                Bullet enemyBullet = EnemyBulletIterator.next();
                if (enemyBullet.Collision(enemyBullet, hero )){
                    enemyBullets.remove(enemyBullet);
                    hero.setHealthPoint(hero.getHealthPoint() - 1);
                    break;
                }
            }
        }
        healthBar.update();
        gameDisplay.update();
    }

    public void drawFrames(Canvas canvas, GameDisplay gameDisplay){
        room1.draw(canvas, gameDisplay);
        hero.draw(canvas, gameDisplay);
        joystick1.draw(canvas);
        AutoFireButton.draw(canvas);
        AbilityButton.draw(canvas);
        healthBar.draw(canvas);
        for (GameObject e: enemyArrayList){
            e.draw(canvas, gameDisplay);
        }
        for (Bullet bullet : heroBullets){
            bullet.draw(canvas, gameDisplay);
        }
        for (Bullet bullet : enemyBullets){
            bullet.draw(canvas, gameDisplay);
        }

        Log.d("RRR", "RISUU FRAMES");
    }


}
