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



    public PaneDoctor(float xPosition, float yPosition, Context context, Hero hero){
        super(xPosition, yPosition, context, hero);
        Model1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor1), 150, 150,  false);
        Model2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor2), 150, 150,  false);
        lastModel = Model1;
        healthPoint = maxHealthPoint = 3;
        speed = 1.5f;
        this.attackSpeed = 5000;
        attackRange = 900;
    }
    @Override
    public Bullet attack() {
        float distance = mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition);
        float bulletXSpeed = (hero.getxPosition() - xPosition) / distance;
        float bulletYSpeed = (hero.getyPosition() - yPosition) / distance;
        lastAttack = System.currentTimeMillis();
        return new PaneDoctorBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context);
    }


}
