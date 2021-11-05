package com.company;

public class Programmer extends People {

    @Override
    void work() {
        System.out.println("Нервно пишет код");
    }

    @Override
    void talk() {
        System.out.println("Какой кретин писал этот код?");
    }

    String back = "Плохая осанка";
    String eyes = "Плохое зрение";
}
