package ru.kirillisachenko.virusgame.gameobjects.heropackage;

import android.content.Context;

import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;

public class ClassicVirus extends Hero{
    public ClassicVirus(Context context, float xPosition, float yPosition, Joystick joystick) {
        super(context, xPosition, yPosition, joystick);
        bulletSpeed = 17f;
        speed = 10f;
    }

    @Override
    public void castAbility() {

    }
}
