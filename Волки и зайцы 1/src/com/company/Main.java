package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int z = 11;
        int v = 2;
        int z1 = 0;
        int s;
        int z2 = 0;
        if (N % 2 == 0){
            for (int n =0; n < N; n = n + 2) {
            z1 = z * 3 - v * 10;
            s = v * 10  + z2;
            if (s >= 70) {
                v = v + s / 70;
                s = 0;
            }
            z = z1;
            z2 = s;

            }
        }else {
            if (N == 1 ) z1 = z * 3;
            for (int n = 1; n < N ; n = n + 2){
                z1 = (z * 3 - v * 10);
                s = v * 10 + z2;
                if (s >= 70) {
                    v = v + s / 70;
                    s = 0;
                }
                z = z1;
                z2 = s;
                z1 = z * 3 ;
            }
        }
        System.out.println( z1 + "Количество зайцев");
        System.out.println(v  + "Количество волков");





    }
}
