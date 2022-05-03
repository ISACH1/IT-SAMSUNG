package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;

public class Hero extends GameObject{
    MathGenerator mathGenerator;
    Bitmap hero1;
    Bitmap hero2;
    Bitmap lastHeroModel;
    Joystick joystick;
    Context context;


    private static float lastAttack = System.currentTimeMillis();
    private static float  attackSpeed = 3;
    private static float bulletSpeed = 5f;
    private float xSpeed, ySpeed, directionX, directionY, xPosition, yPosition;
    private static float speed = 10f;
    float direction;


    public Hero(Context context, float xPosition, float yPosition, Joystick joystick ) {
        super(xPosition, yPosition);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        hero1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus1), 150,150,false);
        hero2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus2), 150,150,false);
        lastHeroModel = hero1;
        this.joystick = joystick;
        mathGenerator = new MathGenerator();
        this.context = context;
    }




    @Override
    public void update() {
        xSpeed = joystick.getActuatorX()*speed;
        ySpeed = joystick.getActuatorY()*speed;
        xPosition += xSpeed;
        yPosition += ySpeed;

        if (xSpeed !=0 || ySpeed !=0){
            float distance = (float) Math.sqrt(Math.pow(xSpeed, 2) + Math.pow(ySpeed, 2));
            directionX = xSpeed/distance;
            directionY = ySpeed/distance;
        }
    }

    @Override
    public Bullet attack() {
     return null;
    }

    @Override
    public Bullet attack(ArrayList<GameObject> enemies) {
        Log.d("ATTACK HERO", "ATACKUU");
        GameObject target = findEnemy(enemies);
        Log.d("TARGET", String.valueOf(target));
        float distance = mathGenerator.DeltaDistance(target.getxPosition(), xPosition, target.getyPosition(), yPosition);
        float bulletXSpeed = (target.getxPosition() - xPosition) / distance;
        float bulletYSpeed = (target.getyPosition() - yPosition) / distance;
        target = findEnemy(enemies);
        return new HeroBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context);
    }

    @Override
    public void draw(Canvas canvas) {

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

    public float getyPosition() {
        return yPosition;
    }

    public float getxPosition() {
        return xPosition;
    }



}
