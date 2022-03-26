package ru.kirillisachenko.virusgame.gameobjects.Jam_package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;

public class Super_Jam_Bullet extends Bullet {
    public Super_Jam_Bullet(float xPosition, float yPosition, float xSpeed, float ySpeed, float bulletSpeed, Context context, double damage) {
        super(xPosition, yPosition, xSpeed, ySpeed, bulletSpeed, context, damage);
        Model = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.jam_bullet_1), 100, 100, false );
    }
}
