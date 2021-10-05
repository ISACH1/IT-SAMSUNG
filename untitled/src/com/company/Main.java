package com.company;

import javax.swing.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int a = 1;
        int b = 1;
        int c;
        for (int n = 2; n < N;  n++) {
            c = a + b;
            a = b;
            b = c;

        }
        System.out.println(b);







    }

}





