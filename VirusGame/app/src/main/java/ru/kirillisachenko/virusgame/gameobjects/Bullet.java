package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;

public abstract class Bullet {
    protected MathGenerator mathGenerator;
    protected Bitmap Model;
    protected   float xPosition, yPosition, xSpeed, ySpeed, bulletSpeed;


    public Bullet(float xPosition, float yPosition, float xSpeed, float ySpeed, float bulletSpeed, Context context){
        mathGenerator = new MathGenerator();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.bulletSpeed = bulletSpeed;
        this.xSpeed = xSpeed * bulletSpeed;
        this.ySpeed = ySpeed * bulletSpeed;

    }


    public  void update(){
        xPosition += xSpeed;
        yPosition += ySpeed;
    }
    public  void draw(Canvas canvas, GameDisplay gameDisplay){
        canvas.drawBitmap(Model, gameDisplay.gameToDisplayCoordinatesX(xPosition - getSize()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - getSize()/2), null);
    }


    public float getyPosition() {
        return yPosition;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getBulletSpeed() {
        return bulletSpeed;
    }
    public  int getSize(){
        return Model.getWidth();
    };

    public void setBulletSpeed(float bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }
    public boolean Collision(Bullet bullet, GameObject gameObject){
        float distance = mathGenerator.DeltaDistance( gameObject.getxPosition() , bullet.getxPosition() ,  gameObject.getyPosition() , bullet.getyPosition() );
        float distanceToTouch = (bullet.getSize() + gameObject.getSize())/2;
        return distance <= distanceToTouch;
    }


}
