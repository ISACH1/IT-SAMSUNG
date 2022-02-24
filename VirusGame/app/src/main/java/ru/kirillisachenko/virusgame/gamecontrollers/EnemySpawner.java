package ru.kirillisachenko.virusgame.gamecontrollers;

import android.content.Context;

import java.util.ArrayList;

import ru.kirillisachenko.virusgame.MathGenerator;
import ru.kirillisachenko.virusgame.gameobjects.Enemy;
import ru.kirillisachenko.virusgame.gameobjects.doctor_package.Doctor;
import ru.kirillisachenko.virusgame.gameobjects.heropackage.Hero;
import ru.kirillisachenko.virusgame.gameobjects.pane_doctor_package.PaneDoctor;

public class EnemySpawner {
    MathGenerator mathGenerator;
    ArrayList<Enemy> enemyArrayList;
    private int waveNumber = 1;
    private int numberOfEnemies = 4;
    Context context;
    Hero hero;
    public EnemySpawner(ArrayList<Enemy> enemyArrayList, Context context, Hero hero){
        this.enemyArrayList = enemyArrayList;
        this.context = context;
        this.hero = hero;
        mathGenerator = new MathGenerator();
    }


    public void update(){
        if (enemyArrayList.isEmpty()){
            spawn();
        }
    }

    public void spawn(){
        for (int i = 0; i < numberOfEnemies ; i++) {
            int type = mathGenerator.getRandom(101, -1);
            if( type > 40) enemyArrayList.add(new PaneDoctor(getRandomCoordinate(hero.getxPosition()), getRandomCoordinate(hero.getyPosition()), context, hero));
            if( type <= 40) enemyArrayList.add(new Doctor(getRandomCoordinate(hero.getxPosition()), getRandomCoordinate(hero.getyPosition()), context, hero));
        }
        waveNumber++;
        numberOfEnemies*=1.5;
    }

    public float getRandomCoordinate(float coordinate){
        char sign = mathGenerator.getRandomSign();
        if ( sign == '+') return coordinate + mathGenerator.getRandom(799, 1200);
        return coordinate - mathGenerator.getRandom(799, 1200);
    }



    public int getWaveNumber() {
        return waveNumber;
    }
}
