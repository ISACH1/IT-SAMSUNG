package ru.kirillisachenko.awesomebird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BirdModel {
    public Bitmap bird1;
    public Bitmap bird2;
    public Bitmap bird3;
    public float x, y;
    private float speed;
    private final float acceleration = 3;
    public BirdModel(Context context, int x){
        this.x = x;
        bird1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluebird_1);
        bird2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluebird_2);
        bird3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.bluebird_3);
    }
    public void fly(){
        speed = -40;
    }

    public void updatePosition(){
        y+=speed;
        speed+=acceleration;
    }

    public float getSpeed() {
        return speed;
    }
}
