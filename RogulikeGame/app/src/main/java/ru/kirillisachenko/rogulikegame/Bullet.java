package ru.kirillisachenko.rogulikegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Bullet {
    int damage, bulletSpeed;
    float x, y;
    float rotateAngle;
    Bitmap bullet;
    public Bullet(Context context, int damage, int ammo, int bulletSpeed, float x, float y, float rotateAngle){
        this.bulletSpeed = bulletSpeed;
        this.rotateAngle = rotateAngle;
        this.damage = damage;
        this.x = x;
        this.y = y;
        bullet = BitmapFactory.decodeResource(context.getResources(), R.drawable.m14_bullet);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bullet, x, y, null);
    }
}
