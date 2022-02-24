package ru.kirillisachenko.virusgame.gameobjects.heropackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.GameObject;

public class Hero extends GameObject {
    MathGenerator mathGenerator;
    Joystick joystick;
    Context context;
    private static float bulletSpeed = 17f;
    private static float speed = 10f;



    public Hero(Context context, float xPosition, float yPosition, Joystick joystick ) {
        super(xPosition, yPosition);
        Model1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus1), 150,150,false);
        Model2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus2), 150,150,false);
        lastModel = Model1;
        this.joystick = joystick;
        mathGenerator = new MathGenerator();
        this.context = context;
        lastAttack = 0;
        attackSpeed = 2000;
        setMaxHealthPoint(5);
        setHealthPoint(5);
    }

    public void update() {
        xSpeed = joystick.getActuatorX()*speed;
        ySpeed = joystick.getActuatorY()*speed;
        xPosition += xSpeed;
        yPosition += ySpeed;
    }





    public Bullet attack(ArrayList<Enemy> enemies) {
        GameObject target = findEnemy(enemies);
        Log.d("TARGET", String.valueOf(target));
        float distance = mathGenerator.DeltaDistance(target.getxPosition(), xPosition, target.getyPosition(), yPosition);
        float bulletXSpeed = (target.getxPosition() - xPosition) / distance;
        float bulletYSpeed = (target.getyPosition() - yPosition) / distance;
        lastAttack = System.currentTimeMillis();
        return new HeroBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context);
    }


    public GameObject findEnemy(ArrayList<Enemy> enemies){
        GameObject target = enemies.get(0);
        for (GameObject enemy: enemies) {
            if (mathGenerator.DeltaDistance( enemy.getxPosition(),xPosition,  enemy.getyPosition(), yPosition)
                    < mathGenerator.DeltaDistance( target.getxPosition(), xPosition,  target.getyPosition(),yPosition) ) target = enemy;
        }
        return target;
    }
}
