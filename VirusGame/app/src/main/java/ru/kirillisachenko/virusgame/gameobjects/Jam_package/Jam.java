package ru.kirillisachenko.virusgame.gameobjects.Jam_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Boss;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class Jam extends Boss {
    protected float bulletSpeed = 24f;

    public Jam(float xPosition, float yPosition, Context context, Hero hero, ArrayList<Bullet> bullets, boolean dropItem) {
        super(xPosition, yPosition, context, hero, bullets, dropItem);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_1), 600, 600,  false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_2), 600, 600,  false);
        Model[2] = Model[0];
        xSpeed = ySpeed = speed = 0;
        attackSpeed = 3000;
        healthPoint = maxHealthPoint = 20;
        damage = 1;
    }


    @Override
    public void Attack() {
        int type = mathGenerator.getRandom(3, 0);
        switch (type){
            case (1):
                attack1();
                break;
            case (2):
                attack2();
                break;
        }
    }

    private void attack1(){
        ArrayList<Bullet> bullets1 = new ArrayList<>();
        float bulletXSpeed = getBulletXSpeed();
        float bulletYSpeed = getBulletYSpeed();
        for (int i = 0; i < 5; i++) {
            bullets1.add(new Jam_bullet(xPosition + mathGenerator.getRandom(201, -201), yPosition + mathGenerator.getRandom(201, -201), bulletXSpeed, bulletYSpeed, bulletSpeed , context, 1));
        }
        lastAttack = System.currentTimeMillis();
        bullets.addAll(bullets1);
    }

    private void  attack2(){
        new attack2Thread().start();
        lastAttack = System.currentTimeMillis();
    }

    private void attack3(){
        lastAttack = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (hero.getxPosition() > xPosition) xSpeed = 1;
        if (hero.getxPosition() < xPosition) xSpeed = -1;
        if (hero.getxPosition() == xPosition) xSpeed = 0;
    }



    private class attack2Thread extends Thread{
        private  volatile boolean running = true;
        @Override
        public void run() {
            while (running) {
                try{
                    bullets.add(new Jam_bullet(xPosition, yPosition, getBulletXSpeed(), getBulletYSpeed(), bulletSpeed, context, 1));
                    sleep(1000);
                    bullets.add(new Jam_bullet(xPosition, yPosition, getBulletXSpeed(), getBulletYSpeed(), bulletSpeed, context, 1));
                    sleep(1000);
                    bullets.add(new Super_Jam_Bullet(xPosition, yPosition, getBulletXSpeed(), getBulletYSpeed(), bulletSpeed, context, 2));
                    running = false;
                }catch (Exception ignored){}
            } interrupt();
        }
    }
}
