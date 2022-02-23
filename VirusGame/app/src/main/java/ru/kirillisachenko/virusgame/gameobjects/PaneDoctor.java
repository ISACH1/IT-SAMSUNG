package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.R;

public class PaneDoctor extends GameObject{
    MathGenerator mathGenerator = new MathGenerator();
    private int rotation;
    Hero hero;
    Bitmap paneDoctor1, paneDoctor2, lastModel;
    Context context;
    private float xPosition, yPosition;

    private  float speed = 1.5F;
    private float xSpeed, ySpeed;

    public PaneDoctor(float xPosition, float yPosition, Context context, Hero hero){
        super(xPosition, yPosition);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        paneDoctor1 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor1), 150, 150, false);
        paneDoctor2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.pane_doctor2), 150, 150, false);
        lastModel = paneDoctor1;
        this.hero = hero;
    }






    @Override
    public void update() {
        Log.d("RRRR", "OBNOVILSYA");
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
        return null;
    }

    @Override
    public Bullet attack(ArrayList<GameObject> enemies) {
        return null;
    }


    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        Log.d("RRRR", "ENEMY RISUET");
        if (xSpeed > 0) { canvas.drawBitmap(paneDoctor1, gameDisplay.gameToDisplayCoordinatesX(xPosition - paneDoctor1.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - paneDoctor1.getHeight()/2), null); lastModel = paneDoctor1;}
        if (xSpeed < 0)  {canvas.drawBitmap(paneDoctor2, gameDisplay.gameToDisplayCoordinatesX(xPosition - paneDoctor2.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - paneDoctor2.getHeight()/2), null); lastModel = paneDoctor2;}
        if (xSpeed == 0)  {canvas.drawBitmap(lastModel, gameDisplay.gameToDisplayCoordinatesX(xPosition - lastModel.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - lastModel.getHeight()/2), null);}
    }
}
