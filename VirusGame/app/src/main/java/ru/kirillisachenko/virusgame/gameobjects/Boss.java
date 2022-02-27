package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class Boss extends Enemy {


    public Boss(float xPosition, float yPosition, Context context, Hero hero) {
        super(xPosition, yPosition, context, hero);
    }

    @Override
    public Bullet attack() {
        return null;
    }

    public abstract ArrayList<Bullet> Attack();

    @Override
    public boolean canAttack() {
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }
}
