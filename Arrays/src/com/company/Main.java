package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String>arrayList = new ArrayList<>();
        myLinkedList.add("a");
        myLinkedList.add("b");
        myLinkedList.add("c");
        myLinkedList.add("d");
        myLinkedList.addFirst("zero");
        for (int i = 0; i < myLinkedList.getSize() ; i++) {
            System.out.println(myLinkedList.get(i));
        }

    }
}
