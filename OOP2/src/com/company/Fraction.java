package com.company;

public class Fraction {

    static int count = 0;

    int numerator;
    int denominator;
    double numd;
    double dend;

    public Fraction(int numerator, int denominator) throws Exception {
        System.out.println("constructor 1");
        if (denominator == 0) {
            throw new Exception("Деления на ноль нет");
        }
        int nod = nod(numerator, denominator);
        this.numerator = numerator;
        this.denominator = denominator;

    }

    public Fraction(double numd, double dend) throws Exception {
        System.out.println("constructor D");
        if (dend == 0) {
            throw new Exception("Деления на ноль нет");
        }
        double nod = nodd(numd, dend);
        this.numd = numd;
        this.dend = dend;
    }

    public Fraction(int numerator) {
        System.out.println("constructor 2");
        this.numerator = numerator;
        this.denominator = 1;
    }

    public void normalization() {
        int nod = nod(numerator, denominator);
        numerator /= nod;
        denominator /= nod;
    }

    public void normalizationD() {
        double nod = nodd(numd, dend);
        numd /= nod;
        dend /= nod;
    }

    private int nod(int a, int b) {
        do {
            if (a > b) a %= b;
            else b %= a;
        } while (a != 0 && b != 0);
        return a + b;
    }

    private double nodd(double a, double b) {
        do {
            if (a > b) a %= b;
            else b %= a;
        } while (a != 0 && b != 0);
        return a + b;
    }

    @Override
    public String toString() {
        count += 1;
        return numerator + "/" + denominator;
    }





}
