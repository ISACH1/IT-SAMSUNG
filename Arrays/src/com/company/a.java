package com.company;

import java.util.Collection;
import java.util.Iterator;

class MyArrayList<T> implements Iterable<T> {
    private T[] data;
    private int size;

    public int size() {
        return size;
    }

    public MyArrayList(int n) {
        data = (T[])new Object[n];
    }

    public MyArrayList() {
        this(10);
    }

    public T get(int index) {
        if (index >= 0 && index < size)
            return data[index];
        throw new IndexOutOfBoundsException(index);
    }

    public void set(int index, T value) {
        if (index >= 0 && index < size)
            data[index] = value;
        throw new IndexOutOfBoundsException("Индекс за границами!");
    }

    public void add(T value) {
        if (size == data.length) {
            T[] newData = (T[])new Object[size * 2];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
        data[size] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 && index >= size) throw new IndexOutOfBoundsException("Индекс за границами!");
        System.arraycopy(data, index + 1, data, index, size - 1);
        data[size - 1] = null;
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }


    private class MyListIterator implements Iterator<T> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            return data[cursor++]; // data[cursor]; cursor+=1;
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(cursor);
        }
    }

    public void addAll(Collection<T> collection){
        for (T a :collection) {
            add(a);
        }
    }

    public boolean contains(T object){
        return indexOf(object) >=0;
    }

    public int indexOf(T t){
        for (int i = 0; i < size ; i++) {
            if (t.equals(data[i])) return i;
        }
        return -1;
    }
}
