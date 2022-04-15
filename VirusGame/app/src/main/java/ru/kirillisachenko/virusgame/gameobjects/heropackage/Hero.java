package ru.kirillisachenko.virusgame.gameobjects.heropackage;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gamecontrollers.Joystick;
import ru.kirillisachenko.virusgame.gameobjects.Boss;
import ru.kirillisachenko.virusgame.gameobjects.Bullet;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.GameObject;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Classic.HeroBullet;

public abstract class Hero extends GameObject {
    private final MathGenerator mathGenerator;
    private final Joystick joystick;
    protected float bulletSpeed;
    protected long lastCast;
    protected long AbilityKD;
    protected float maxAttackSpeed;




    public Hero(Context context, float xPosition, float yPosition, Joystick joystick ) {
        super(xPosition, yPosition);
        this.joystick = joystick;
        mathGenerator = new MathGenerator();
        lastAttack = 0;
        lastCast = 0;
        maxAttackSpeed = 500;
    }

    public void update() {
        xSpeed = joystick.getActuatorX()*speed;
        ySpeed = joystick.getActuatorY()*speed;
        xPosition += xSpeed;
        yPosition += ySpeed;
    }

    public Bullet attack(ArrayList<Enemy> enemies, Context context) {
        GameObject target = findEnemy(enemies);
        float distance = mathGenerator.DeltaDistance(target.getxPosition(), xPosition, target.getyPosition(), yPosition);
        float bulletXSpeed = (target.getxPosition() - xPosition) / distance;
        float bulletYSpeed = (target.getyPosition() - yPosition) / distance;
        lastAttack = System.currentTimeMillis();
        return new HeroBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context, damage, this);
    }

    public Bullet attackBoss(ArrayList<Boss> bosses, Context context) {
        GameObject target = findEnemy(bosses);
        float distance = mathGenerator.DeltaDistance(target.getxPosition(), xPosition, target.getyPosition(), yPosition);
        float bulletXSpeed = (target.getxPosition() - xPosition) / distance;
        float bulletYSpeed = (target.getyPosition() - yPosition) / distance;
        lastAttack = System.currentTimeMillis();
        return new HeroBullet(xPosition, yPosition, bulletXSpeed, bulletYSpeed, bulletSpeed, context, damage, this);
    }

    protected GameObject findEnemy(ArrayList<Enemy> enemies){
        GameObject target = enemies.get(0);
        for (GameObject enemy: enemies) {
            if (mathGenerator.DeltaDistance( enemy.getxPosition(),xPosition,  enemy.getyPosition(), yPosition)
                    < mathGenerator.DeltaDistance( target.getxPosition(), xPosition,  target.getyPosition(),yPosition) ) target = enemy;
        }
        return target;
    }

    protected GameObject findEnemy(List<Boss> enemies){
        GameObject target = enemies.get(0);
        for (GameObject enemy: enemies) {
            if (mathGenerator.DeltaDistance( enemy.getxPosition(),xPosition,  enemy.getyPosition(), yPosition)
                    < mathGenerator.DeltaDistance( target.getxPosition(), xPosition,  target.getyPosition(),yPosition) ) target = enemy;
        }
        return target;
    }


    public boolean canCast(){
        return (System.currentTimeMillis() - lastCast) >= AbilityKD;
    }

    public abstract void castAbility();

}
