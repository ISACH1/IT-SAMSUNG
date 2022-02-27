package ru.kirillisachenko.virusgame.gameobjects;

import android.content.Context;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;

public abstract class Enemy extends GameObject{
    protected MathGenerator mathGenerator;
    protected  Hero hero;
    protected  Context context;
    protected float attackRange;
    protected float minDistance;

    public Enemy(float xPosition, float yPosition, Context context, Hero hero) {
        super(xPosition, yPosition);
        this.hero = hero;
        this.context = context;
        mathGenerator = new MathGenerator();
    }

    public void update(){
        if(!collision) {
            float distance = mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition);
            float deltaX = hero.getxPosition() - xPosition;
            float deltaY = hero.getyPosition() - yPosition;
            if (distance > minDistance) {
                xSpeed = deltaX / distance * speed;
                ySpeed = deltaY / distance * speed;
            } else {
                xSpeed = ySpeed = 0;
            }
        }
        xPosition += xSpeed;
        yPosition += ySpeed;

    }
    public abstract Bullet attack();

    @Override
    public boolean canAttack() {
        return mathGenerator.DeltaDistance(hero.getxPosition(), xPosition, hero.getyPosition(), yPosition) <= attackRange && (System.currentTimeMillis() - lastAttack) >= attackSpeed;
    }
}
