package com.company;

public class Bidlo extends People {
    @Override
    void work() {
        System.out.println("Обчищает детей во дворе");
    }

    @Override
    void talk() {
        System.out.println("Дай мобилу позвонить");
    }
    String behaviour = "Чрезвычайно агрессивное";
}
