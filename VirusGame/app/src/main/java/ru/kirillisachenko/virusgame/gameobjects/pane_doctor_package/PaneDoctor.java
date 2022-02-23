package ru.kirillisachenko.virusgame.gameobjects.pane_doctor_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.GameObject;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class PaneDoctor extends GameObject {
    MathGenerator mathGenerator = new MathGenerator();
    Hero hero;
    Bitmap Model1, Model2, lastModel;
    Context context;
    private  long  lastAttack = 0;
    private long attackSpeed = 5000; // mills
    private float bulletSpeed = 4.5f;
    private  float speed = 1.5F;
    private float xSpeed, ySpeed;


    public PaneDoctor(float xPosition, float yPosition, Context context, Hero hero){
        super(xPosition, yPosition);
        this.context = context;
        Model1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor1), 150, 150,  false);
        Model2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor2), 150, 150,  false);
        lastModel = Model1;
        this.hero = hero;
        healthPoint = maxHealthPoint = 3;
    }




    @Override
    public void update() {
    float distance = mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition);
    float deltaX = hero.getxPosition() - xPosition;
    float deltaY = hero.getyPosition() - yPosition;
    if (distance > 0) {
        xSpeed = deltaX / distance * speed;
        ySpeed = deltaY / distance * speed;
    } else{
        xSpeed = ySpeed = 0;
    }
    xPosition += xSpeed;
    yPosition += ySpeed;
    }

    @Override
    public Bullet attack() {
        float distance = mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition);
        float bulletXSpeed = (hero.getxPosition() - xPosition) / distance;
        float bulletYSpeed = (hero.getyPosition() - yPosition) / distance;
        lastAttack = System.currentTimeMillis();
        return new PaneDoctorBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context);
    }

    @Override
    public Bullet attack(ArrayList<GameObject> enemies) {
        return null;
    }





    @Override
    public int getSize() {
        return 150;
    }

    @Override
    public boolean canAttack(){
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed && mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition) <= 1000 ;
    }


    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        if (xSpeed > 0) { canvas.drawBitmap(Model1, gameDisplay.gameToDisplayCoordinatesX(xPosition - Model1.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model1.getHeight()/2), null); lastModel = Model1;}
        if (xSpeed < 0)  {canvas.drawBitmap(Model2, gameDisplay.gameToDisplayCoordinatesX(xPosition - Model2.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - Model2.getHeight()/2), null); lastModel = Model2;}
        if (xSpeed == 0)  {canvas.drawBitmap(lastModel, gameDisplay.gameToDisplayCoordinatesX(xPosition - lastModel.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - lastModel.getHeight()/2), null);}
    }


}
