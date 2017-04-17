package ru.otus;


import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by Alexey on 01.04.2017.
 */

public class Main  {

    public static void main(String[] args) {

        ArrayList<Integer> integers = new ArrayList<>();
        MyArrayList<Integer> myIntegers = new MyArrayList<>();

        integers.toString();


        System.out.println(integers);
        System.out.println(myIntegers);

        for (int i = 0; i < 5; ++i) {
            integers.add(i);
            myIntegers.add(i);
        }

        for (int val : integers)
        {
            System.out.print(val + " ");
        }
        System.out.println();

        for (int val : myIntegers)
        {
            System.out.print(val + " ");
        }
        System.out.println();

        checkIterator(integers.listIterator());
        System.out.println();
        checkIterator(myIntegers.listIterator());
        System.out.println();


        System.out.println(integers);
        System.out.println(myIntegers);

        checkCollection();


    }

    private static  <E> void checkIterator(ListIterator<E> it)
    {
        for (int i = 0; i < 3; ++i) {
            if (it.hasNext()) {
                System.out.print(it.next() + " ");
            }
        }

        if (it.hasPrevious()) {
            System.out.print(it.previous() + " ");
        }

        if (it.hasPrevious()) {
            System.out.print(it.previous() + " ");
        }

        if (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        if (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }

    private static void checkCollection()
    {
        ArrayList<Integer> integers = new ArrayList<>();
        MyArrayList<Integer> myIntegers = new MyArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; ++i)
        {
            int val = random.nextInt(100);
            integers.add(val);
            myIntegers.add(val);
        }

        System.out.println(integers);
        System.out.println(myIntegers);

        Collections.addAll(integers, 1, 2, 3, 4, 5);
        Collections.addAll(myIntegers, 1, 2, 3, 4, 5);

        System.out.println(integers);
        System.out.println(myIntegers);

        ArrayList<Integer> tempIntegers = new ArrayList<>();
        tempIntegers.add(6);
        tempIntegers.add(7);
        tempIntegers.add(8);
        tempIntegers.add(9);
        tempIntegers.add(10);

        Collections.copy(integers, tempIntegers);
        Collections.copy(myIntegers, tempIntegers);

        System.out.println(integers);
        System.out.println(myIntegers);

        Collections.sort(integers);
        Collections.sort(myIntegers);
        //Collections.copy(tempIntegers, myIntegers);


        System.out.println(integers);
        System.out.println(myIntegers);

    }
}
