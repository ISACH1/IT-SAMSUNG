package ru.kirillisachenko.virusgame.gameobjects.heropackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;

public class HeroBullet extends Bullet {
    Bitmap bullet;


    public HeroBullet(float xPosition, float yPosition, float xSpeed, float ySpeed, float bulletSpeed, Context context) {
        super(xPosition, yPosition, xSpeed, ySpeed, bulletSpeed, context);
        bullet = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.hero_classic_bullet ), 50, 50, false);
    }


    @Override
    public void update() {
        xPosition += xSpeed;
        yPosition += ySpeed;
    }

    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(bullet, gameDisplay.gameToDisplayCoordinatesX(xPosition - bullet.getWidth()/2), gameDisplay.gameToDisplayCoordinatesY(yPosition - bullet.getHeight()/2), null);
    }

    @Override
    public int getSize() {
        return 50;
    }



}
