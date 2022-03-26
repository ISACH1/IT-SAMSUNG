package ru.kirillisachenko.virusgame.gameobjects.pane_doctor_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.GameObject;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class PaneDoctor extends Enemy {
    protected float bulletSpeed = 8f;

    public PaneDoctor(float xPosition, float yPosition, Context context, Hero hero, boolean dropItem) {
        super(xPosition, yPosition, context, hero, dropItem);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor1), 150, 150,  false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor2), 150, 150,  false);
        Model[2] = Model[0];
        healthPoint = maxHealthPoint = 3;
        speed = 2f;
        this.attackSpeed = 5000;
        attackRange = 900;
        minDistance = 400;
        damage = 1;
    }


    @Override
    public Bullet attack() {
        float bulletXSpeed = getBulletXSpeed();
        float bulletYSpeed = getBulletYSpeed();
        lastAttack = System.currentTimeMillis();
        return new PaneDoctorBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context, damage);
    }


}
