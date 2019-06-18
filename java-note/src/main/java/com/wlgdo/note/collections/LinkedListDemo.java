package com.wlgdo.note.collections;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Author: Ligang.Wang[wlgchun@l63.com]
 * Date: 2019/6/19 0:12
 */
public class LinkedListDemo {

    public static LinkedList<Integer> linkedList = new LinkedList<>();
    public static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void main(String[] args) {
        int times = 1000000;

        linkedList(times);
        arrayList(times);
    }

    public static void linkedList(int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            linkedList.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("LinkedList Used time 【%s】 ms", endTime - startTime));
    }

    public static void arrayList(int times) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            arrayList.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("ArrayList Used time 【%s】 ms", endTime - startTime));
    }
}
