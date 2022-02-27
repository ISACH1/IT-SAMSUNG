package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class GameObject {
    MathGenerator mathGenerator;
    protected   float xPosition;
    protected float yPosition;
    protected int healthPoint;
    protected int maxHealthPoint;
   protected   long lastAttack  = 0;
   protected   long  attackSpeed ;
    protected Bitmap Model1, Model2, lastModel;
    protected float xSpeed, ySpeed;
    protected   float speed;
    protected boolean collision = false;

    public GameObject(float  xPosition, float yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
         mathGenerator= new MathGenerator();
    }

    public float getxPosition() {
        return xPosition ;
    }

    public  boolean canAttack(){
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }

    public  void draw(Canvas canvas, GameDisplay gameDisplay){
        if (xSpeed > 0) { canvas.drawBitmap(Model1, gameDisplay.gameToDisplayCoordinatesX(xPosition - Model1.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model1.getHeight()/2), null); lastModel = Model1;}
        if (xSpeed < 0)  {canvas.drawBitmap(Model2, gameDisplay.gameToDisplayCoordinatesX(xPosition - Model2.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model2.getHeight()/2), null); lastModel = Model2;}
        if (xSpeed == 0)  {canvas.drawBitmap(lastModel, gameDisplay.gameToDisplayCoordinatesX(xPosition - lastModel.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - lastModel.getHeight()/2), null);}
    }

    public float getyPosition() {
        return yPosition ;
    }

    public  int getSize(){
        return Model1.getWidth();
    }

    public int getHealthPoint(){
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        if ( healthPoint > 0) {
            this.healthPoint = healthPoint;
            return;
        }
        this.healthPoint = 0;
    }

    public int getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(int maxHealthPoint) {
        if (maxHealthPoint > 0) {
            this.maxHealthPoint = maxHealthPoint;
            return;
        }
        this.maxHealthPoint = 0;
    }

    public boolean Collision(GameObject gameObject){
        float distance = mathGenerator.DeltaDistance( xPosition , gameObject.getxPosition() ,  yPosition , gameObject.getyPosition() );
        float distanceToTouch = (getSize() + gameObject.getSize())/2;
        if (distance <= distanceToTouch) {
            collision = true;
            return distance <= distanceToTouch;
        } collision = false;
        return false;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed * speed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed * speed;
    }
}
