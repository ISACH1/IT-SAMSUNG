package ru.kirillisachenko.virusgame.gameobjects;

public abstract class GameObject {
    public float xPosition;
    public float yPosition;
    public float xSpeed;
    public float ySpeed;

    public GameObject(float  xPosition, float yPosition){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
    public void draw(){}
    public void update(){}
}
