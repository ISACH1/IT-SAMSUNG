package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.animation.Animation;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;

public class Hero extends GameObject{
    Bitmap hero1;
    Bitmap hero2;
    Bitmap lastHeroModel;
    Context context;


    private float xSpeed, ySpeed, directionX, directionY, xPosition, yPosition;
    private static float speed = 10f;
    float direction;


    public Hero(Context context, float xPosition, float yPosition ) {
        super(xPosition, yPosition);
        hero1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus1), 150,150,false);
        hero2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus2), 150,150,false);
    }


    @Override
    public void update() {

    }

    @Override
    public void update(Joystick joystick) {
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
    public void draw(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        if (xSpeed > 0) canvas.drawBitmap(hero1, gameDisplay.gameToDisplayCoordinatesX(xPosition) , gameDisplay.gameToDisplayCoordinatesY(yPosition) , null ); lastHeroModel = hero1;
        if ((xSpeed < 0)) canvas.drawBitmap(hero2, gameDisplay.gameToDisplayCoordinatesX(xPosition), gameDisplay.gameToDisplayCoordinatesY(yPosition), null); lastHeroModel = hero2;
        if(xSpeed == 0) canvas.drawBitmap(lastHeroModel,  gameDisplay.gameToDisplayCoordinatesX(xPosition), gameDisplay.gameToDisplayCoordinatesY(yPosition), null);
    }

    public float getyPosition() {
        return yPosition;
    }

    public float getxPosition() {
        return xPosition;
    }
}
