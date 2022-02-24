package ru.kirillisachenko.virusgame.gameobjects.Jam_package;

import android.content.Context;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public class Jam extends Enemy {
    protected float bulletSpeed = 12f;
    public Jam(float xPosition, float yPosition, Context context, Hero hero) {
        super(xPosition, yPosition, context, hero);
    }

    public ArrayList<Bullet> BossAttack(){

        return
    }

    @Override
    public Bullet attack() {
        return null;
    }

    private ArrayList<Bullet> type1Attack(){

    }
}
