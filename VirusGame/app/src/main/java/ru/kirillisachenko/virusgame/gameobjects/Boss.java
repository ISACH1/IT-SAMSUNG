package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class Boss extends Enemy {

    protected ArrayList<Bullet> bullets;

    public Boss(float xPosition, float yPosition, Context context, Hero hero,ArrayList<Bullet> bullets, boolean dropItem) {
        super(xPosition, yPosition, context, hero, dropItem);
        this.bullets = bullets;
    }


    @Override
    public Bullet attack() {
        return null;
    }

    public abstract void Attack();

    @Override
    public boolean canAttack() {
        return (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }
}
