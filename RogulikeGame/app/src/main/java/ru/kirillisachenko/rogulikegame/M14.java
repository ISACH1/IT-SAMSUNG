package ru.kirillisachenko.rogulikegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class M14 extends Weapon{
    Bitmap weapon1;
    Bitmap weapon2;
    Bitmap weapon3;
    Bitmap weapon4;
    Context context;
    private boolean attackable;
    Bullet bullet;


    public M14(Context context, int damage, int ammo, int bulletSpeed, float x, float y, float rotateAngle) {
        super(context, damage, ammo, bulletSpeed, x ,y, rotateAngle);

        weapon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.m14_1);
        weapon2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.m14_2);
        weapon3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.m14_3);
        weapon4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.m14_4);
    }


    public void attack(){
         bullet = new Bullet(context.getApplicationContext(), damage, ammo, bulletSpeed, x, y, rotateAngle);
    }


    public void draw(Canvas canvas){
        if(!attackable){
            canvas.rotate(-rotateAngle);
            canvas.drawBitmap(weapon1, x , y, null);
            canvas.rotate(rotateAngle);
        } else{
            canvas.rotate(-rotateAngle);
            bullet.draw(canvas);
            canvas.drawBitmap(weapon2, x, y, null);
            canvas.drawBitmap(weapon3, x, y, null);
            canvas.drawBitmap(weapon4, x, y, null);
            canvas.rotate(rotateAngle);
        }
    }


}
