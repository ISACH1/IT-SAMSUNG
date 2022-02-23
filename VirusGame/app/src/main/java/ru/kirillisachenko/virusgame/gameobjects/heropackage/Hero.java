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
import ru.kirillisachenko.virusgame.gameobjects.GameObject;

public class Hero extends GameObject {
    MathGenerator mathGenerator;
    Bitmap hero1;
    Bitmap hero2;
    Bitmap lastHeroModel;
    Joystick joystick;
    Context context;



    private  long lastAttack = 0;
    private  long  attackSpeed = 2000; // mills
    private static float bulletSpeed = 5f;
    private float xSpeed, ySpeed;
    private static float speed = 10f;



    public Hero(Context context, float xPosition, float yPosition, Joystick joystick ) {
        super(xPosition, yPosition);
        hero1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus1), 150,150,false);
        hero2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus2), 150,150,false);
        lastHeroModel = hero1;
        this.joystick = joystick;
        mathGenerator = new MathGenerator();
        this.context = context;
        setMaxHealthPoint(5);
        setHealthPoint(5);
    }




    @Override
    public void update() {
        xSpeed = joystick.getActuatorX()*speed;
        ySpeed = joystick.getActuatorY()*speed;
        xPosition += xSpeed;
        yPosition += ySpeed;
    }



    @Override
    public Bullet attack() {
     return null;
    }

    @Override
    public Bullet attack(ArrayList<GameObject> enemies) {

        GameObject target = findEnemy(enemies);
        Log.d("TARGET", String.valueOf(target));
        float distance = mathGenerator.DeltaDistance(target.getxPosition(), xPosition, target.getyPosition(), yPosition);
        float bulletXSpeed = (target.getxPosition() - xPosition) / distance;
        float bulletYSpeed = (target.getyPosition() - yPosition) / distance;
        lastAttack = System.currentTimeMillis();
        Log.d("ATTACKER",  String.valueOf(System.currentTimeMillis()));
        Log.d("ATTACKER",  String.valueOf(lastAttack));
        return new HeroBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context);
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        if (xSpeed > 0){ canvas.drawBitmap(hero1, gameDisplay.gameToDisplayCoordinatesX(xPosition - hero1.getWidth()/2) , gameDisplay.gameToDisplayCoordinatesY(yPosition - hero1.getHeight()/2) , null ); lastHeroModel = hero1;}
        if ((xSpeed < 0)){ canvas.drawBitmap(hero2, gameDisplay.gameToDisplayCoordinatesX(xPosition- hero2.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - hero2.getHeight()/2), null); lastHeroModel = hero2;}
        if(xSpeed == 0) canvas.drawBitmap(lastHeroModel,  gameDisplay.gameToDisplayCoordinatesX(xPosition - lastHeroModel.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - lastHeroModel.getHeight()/2), null);
    }

    public GameObject findEnemy(ArrayList<GameObject> enemies){
        GameObject target = enemies.get(0);
        for (GameObject enemy: enemies) {
            if (mathGenerator.DeltaDistance( enemy.getxPosition(),xPosition,  enemy.getyPosition(), yPosition)
                    < mathGenerator.DeltaDistance( target.getxPosition(), xPosition,  target.getyPosition(),yPosition) ) target = enemy;
        }
        return target;
    }



    @Override
    public int getSize() {
        return 150;
    }

    public boolean canAttack(){
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }



}
