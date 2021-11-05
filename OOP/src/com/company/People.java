package com.company;

abstract class People {
    private int  hand = 2;
    public int getHand() {
        return hand;
    }

    private int leg = 2;
    public int getLeg() {
        return leg;
    }

    private int had = 1;
    public int getHad() {
        return had;
    }

    public void setLeg( int newLeg){
        if(newLeg > 2) System.out.println("Не прокатит");
        else leg = newLeg;
    }

    public void setHand( int newHand){
        if(newHand > 2) System.out.println("Не прокатит");
        else hand = newHand;
    }

    abstract void work();
    abstract void talk();
    public static void move(){
        System.out.println("Шагает");
    }

}
