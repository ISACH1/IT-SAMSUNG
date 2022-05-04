package ru.kirillisachenko.virusgame.gameobjects.heropackage.Classic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import ru.kirillisachenko.virusgame.GameDisplay;
import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class ClassicVirus extends Hero {
    public ClassicVirus(Context context, float xPosition, float yPosition, int size, Joystick joystick) {
        super(context,xPosition, yPosition, size, joystick);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus1), size, size,false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus_state_015), size,size,false);
        Model[2] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus2), size,size,false);
        Model[3] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus_state_l), size,size,false);
        Model[4] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus_walk_r_1), size,size,false);
        Model[5] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus_walk_r_2), size,size,false);
        Model[6] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus_walk_l_1), size,size,false);
        Model[7] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus_walk_l_2), size,size,false);
        bulletSpeed = 17f;
        speed = 10f;
        AbilityKD = 20 * 1000;
        attackSpeed = 2000;
        setMaxHealthPoint(5);
        setHealthPoint(5);
        armor = 0;
        damage = 10;
    }

    @Override
    public void castAbility() {
        setHealthPoint(getHealthPoint() + 2);
        lastCast = System.currentTimeMillis();
    }


}
