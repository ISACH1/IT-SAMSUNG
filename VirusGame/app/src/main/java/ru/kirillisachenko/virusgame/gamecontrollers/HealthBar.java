package ru.kirillisachenko.virusgame.gamecontrollers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class HealthBar {
    Hero hero;
    private int fullCount, emptyCount;
    private Bitmap healthBar, emptyHealthBar;
    private float xPosition, yPosition, lastXPosition, newLastXPosition;
    public HealthBar(float xPosition, float yPosition, Context context, Hero hero){
        this.hero = hero;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        healthBar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.health_bar), 75, 75,  false);
        emptyHealthBar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.empty_health_bar), 75, 75,  false);
    }

    public void update(){
        fullCount =  hero.getHealthPoint();
        emptyCount = hero.getMaxHealthPoint() - hero.getHealthPoint();
    }

    public void draw(Canvas canvas){
        for (int i = 0; i < fullCount; i++) {
            lastXPosition = xPosition + (i) * 75;
            canvas.drawBitmap(healthBar, lastXPosition, yPosition, null);
        }
        if(fullCount != 0) {
            for (int i = 0; i < emptyCount + 1; i++) {
                newLastXPosition = lastXPosition + (i) * 75;
                canvas.drawBitmap(emptyHealthBar, newLastXPosition, yPosition, null);
            }
        } if(fullCount == 0){
            for (int i = 0; i < emptyCount ; i++) {
                newLastXPosition = lastXPosition + (i) * 75;
                canvas.drawBitmap(emptyHealthBar, newLastXPosition, yPosition, null);
            }
        }
    }
}
