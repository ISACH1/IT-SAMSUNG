package ru.kirillisachenko.mathtrainer;

import android.util.Log;

public class MathGenerator {

    private int result;

    public String getProblem() {
        int a = getRandom(50, 0);
        int b = getRandom(50, 0);
        char sign = getRandomSign();
        if (sign == '+') {
            result = a + b;
            return a + " + " + b;
        } else if (sign == '-'){
            result = a - b;
            return a + " - " + b;
        } else if (sign == '*'){
            result = a * b;
            return a + "*" + b;
        } else{
            while (a % b  != 0) {
                a = getRandom(50,0);
                b = getRandom(50,0);
            }
            result = a /b;
            return a + "/" + b;
        }
    }

    public char getRandomSign() {
        double a = getRandom(5, 1); // 1 2
        if (a == 1){
            return '+';
        }else if(a == 2){
            return '-';
        } else if (a == 3) {
            return '*';
        }else return '/';

    }

    public String getResult() {
        return String.valueOf(result);
    }

    public String getNoiseResult() {
        return String.valueOf(result + getRandom(-5, 5));
    }

    public int getRandom(int max, int min) {
        int random = 0;
        while (random == 0)
            random = (int) (Math.random() * (max - min) + min);
        return random;
    }


}
