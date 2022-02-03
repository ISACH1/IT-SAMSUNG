package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {


    public static <T1 extends Comparable, T2 extends Comparable> boolean IsInArray(T1 generic, T2[] generics) {
        if (generic.equals(generics)) {
            for (int i = 0; i < generics.length; i++) {
                if (generic.compareTo(generics[i]) == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        
    }

}







