package com.company;

public class MyArrayList<T> {
    private Object [] data;
    private int size;

    public MyArrayList(int n){
        data = new Object[n];
    }

    public MyArrayList(){
        this(10);
    }

    public Object get(int index) throws Exception {
        if (index > 0 && index < size )  return data[index];
        throw new Exception();
    }



}
