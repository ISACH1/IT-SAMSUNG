package ru.kirillisachenko.virusgame.gameobjects.Jam_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Boss;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class Jam extends Boss {
    protected float bulletSpeed = 12f;

    public Jam(float xPosition, float yPosition, Context context, Hero hero) {
        super(xPosition, yPosition, context, hero);
        Model1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_1), 400, 400,  false);
        Model2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_2), 400, 400,  false);
        lastModel = Model1;
        xSpeed = ySpeed = speed =0;
        attackSpeed = 3000;
        healthPoint = maxHealthPoint = 20;
    }


    @Override
    public ArrayList<Bullet> Attack() {
        return attack1();
    }

    private ArrayList<Bullet> attack1(){
        ArrayList<Bullet> bullets = new ArrayList<>();
        bullets.add(new Jam_bullet(xPosition, yPosition, 1 ,1, bulletSpeed, context ));
        bullets.add(new Jam_bullet(xPosition, yPosition, -1 ,-1, bulletSpeed, context ));
        bullets.add(new Jam_bullet(xPosition, yPosition, 1 ,0, bulletSpeed, context ));
        lastAttack = System.currentTimeMillis();
        return bullets;
    }

    private ArrayList<Bullet> attack2(){
        return null;
    }

    private ArrayList<Bullet> attack3(){
        return null;
    }

    @Override
    public void update() {
        if (hero.getxPosition() > xPosition) xSpeed = 1;
        if (hero.getxPosition() < xPosition) xSpeed = -1;
        if (hero.getxPosition() == xPosition) xSpeed = 0;
    }

}
