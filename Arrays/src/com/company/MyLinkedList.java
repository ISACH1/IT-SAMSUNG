package com.company;

import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    private int size;
    private Node first;
    private Node last;

    public int getSize() {
        return size;
    }

    public void add(T data){
        if (!isEmpty()){
            Node newNode = new Node(data);
            last.next = newNode;
            last = last.next;
        } else{
            first = new Node(data);
            last = first;
        }
        size++;
    }

    public void remove(int index){
        if (index < 0 || index >=size){ throw new IndexOutOfBoundsException(index);}
        size--;
        if (index == 0){
            first = first.next;
            return;
        }
        Node cursor = first;
        Node previous = cursor;
        for (int i = 0; i < index ; i++) {
            previous = cursor;
            cursor = cursor.next;
        }
        if (index == size-1){
            previous.next = null;
            return;
        }
        previous.next = cursor.next;
    }


    public T get(int index){
        if (index < 0 || index >=size){ throw new IndexOutOfBoundsException(index);}
        Node cursor = first;
        for (int i = 0; i < index ; i++) {
            cursor = cursor.next;
        }
        return cursor.data;
    }

    public void addFirst(T data){
        if (!isEmpty()){
            Node newNode = new Node(data);
            newNode.next = first;
            first = newNode;
        } else{
            first = new Node(data);
            last = first;
        }
        size++;
    }

    public void add(int index, T data){
        if (index < 0 || index >=size){ throw new IndexOutOfBoundsException(index);}
        Node cursor = first;
        Node previous = first;
        if (index == 0){
            addFirst(data);
            return;
        }
        if (index == size -1){
            add(data);
            return;
        }
        for (int i = 0; i < index ; i++) {
            previous = cursor;
            cursor = cursor.next;
        }
        Node newNode = new Node(data);
        previous.next = newNode;
        newNode.next = cursor;
        size++;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{

        Node cursor = first;

        @Override
        public boolean hasNext() {
            return !isEmpty() && cursor.next != null;
        }

        @Override
        public T next() {
            Node nextNode = cursor.next;
            cursor = cursor.next;
            return nextNode.data;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }

    public void addAll(Collection<T> collection){
        for (T t : collection){
            add(t);
        }
    }

    public void addAll(int index, Collection<T> collection){
        for (T t : collection){
            add(index, t);
            index++;
        }
    }

    public int indexOf(T data){
        Node cursor = first;
        for (int i = 0; i < size; i++) {
            if (cursor.equals(data)) {return i;}
            cursor = cursor.next;
        }
        return -1;
    }

    public boolean contains(T data){
        return indexOf(data) >=0;
    }

    private class Node{
        T data;
        Node next = null;
        public Node(T data){
            this.data = data;
        }
    }

}
