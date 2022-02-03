package ru.kirillisachenko.rogulikegame;

import android.content.Context;

public class Weapon {
    int damage;
    int ammo;
    int bulletSpeed;
    float x;
    float y;
    float rotateAngle;

    public Weapon(Context context, int damage, int ammo, int bulletSpeed , float x, float y, float rotateAngle){
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.ammo = ammo;
        this.bulletSpeed = bulletSpeed;
        this.rotateAngle = rotateAngle;
    }


    public void attack(){

    }

    public int reload(){
        return ammo;
    }


}
