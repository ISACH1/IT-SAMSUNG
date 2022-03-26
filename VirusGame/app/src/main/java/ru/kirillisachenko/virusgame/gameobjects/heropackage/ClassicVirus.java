package ru.kirillisachenko.virusgame.gameobjects.heropackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import ru.kirillisachenko.virusgame.R;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;

public class ClassicVirus extends Hero{
    public ClassicVirus(Context context, float xPosition, float yPosition, Joystick joystick) {
        super(context, xPosition, yPosition, joystick);
        Model[0] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus1), 150,150,false);
        Model[1] = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.virus2), 150,150,false);
        Model[2] = Model[0];
        bulletSpeed = 17f;
        speed = 10f;
        AbilityKD = 20 * 1000;
        attackSpeed = 2000;
        setMaxHealthPoint(5);
        setHealthPoint(5);
        armor = 0;
        damage = 1;
    }

    @Override
    public void castAbility() {
        setHealthPoint(getHealthPoint() + 2);
        lastCast = System.currentTimeMillis();
    }
}
