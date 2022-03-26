package ru.kirillisachenko.virusgame.gameobjects;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;

public abstract class GameObject {
    MathGenerator mathGenerator;
    protected   float xPosition;
    protected float yPosition;
    protected double healthPoint;
    protected double maxHealthPoint;
   protected   long lastAttack  = 0;
   protected   long  attackSpeed ;
    protected Bitmap Model [];
    protected float xSpeed, ySpeed;
    protected   float speed;
    protected int armor;
    protected double damage;

    public GameObject(float  xPosition, float yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        mathGenerator= new MathGenerator();
        Model  = new Bitmap[3];
    }

    public float getxPosition() {
        return xPosition ;
    }

    public  boolean canAttack(){
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }

    public  void draw(Canvas canvas, GameDisplay gameDisplay){
        if (xSpeed > 0) { canvas.drawBitmap(Model[0], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[0].getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[0].getHeight()/2), null); Model[2] = Model[0];}
        if (xSpeed < 0)  {canvas.drawBitmap(Model[1], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[1].getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[1].getHeight()/2), null); Model[2] = Model[1];}
        if (xSpeed == 0)  {canvas.drawBitmap(Model[2], gameDisplay.gameToDisplayCoordinatesX(xPosition - Model[2].getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model[2].getHeight()/2), null);}
    }

    public float getyPosition() {
        return yPosition ;
    }

    public  int getSize(){
        return Model[0].getWidth();
    }

    public double getHealthPoint(){
        return healthPoint;
    }

    public void setHealthPoint(double health) {
        if (health >= maxHealthPoint){
            this.healthPoint = maxHealthPoint;
            return;
        }
        if ( health > 0) {
            this.healthPoint = health;
            return;
        }
        this.healthPoint = 0;
    }

    public double getMaxHealthPoint() {
        return maxHealthPoint;
    }

    public void setMaxHealthPoint(double maxHealthPoint) {
        if (maxHealthPoint > 0) {
            this.maxHealthPoint = maxHealthPoint;
            return;
        }
        this.maxHealthPoint = 0;
    }



    public void takeDamage(double damage){
        if(armor == 0) {
            setHealthPoint(getHealthPoint() - damage);
        }
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed * speed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed * speed;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public double getDamage() {
        return damage;
    }

    public void setAttackSpeed(long attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public long getAttackSpeed() {
        return attackSpeed;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
