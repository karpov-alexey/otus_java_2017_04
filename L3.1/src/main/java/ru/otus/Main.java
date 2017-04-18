package ru.otus;


import java.util.*;

/**
 * Created by Alexey on 01.04.2017.
 */

public class Main  {

    public static void main(String[] args) {

        System.out.println("===== 1. Check constructors =======");
        MyArrayList<Integer> integers = new MyArrayList<>();
        System.out.println("Default constructor. Capacity = " + integers.capacity());

        integers = new MyArrayList<>(3);
        System.out.println("Constructor with capacity = 3. Capacity = " + integers.capacity());

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers = new MyArrayList<>(integers);
        System.out.println("Copy collection constructor. Capacity = " + integers.capacity());


        //==============================================
        System.out.println();
        System.out.println("===== 2. Check add(), toString(), foreach and reallocation array =======");
        integers = new MyArrayList<>();
        for (int i = 0; i < 8; ++i)
        {
            integers.add(i);
        }
        System.out.println("Added 8 integers. toString() = " + integers.toString());
        System.out.println("Capacity = " + integers.capacity());

        for (int i = 8; i < 14; ++i)
        {
            integers.add(i);
        }

        System.out.print("Added another 6 integers. foreach = ");
        for (int val : integers)
        {
            System.out.print(val + " ");
        }
        System.out.println();
        System.out.println("Capacity = " + integers.capacity());

        //==============================================
        System.out.println();
        System.out.println("===== 3. Check size() and isEmpty() =======");
        System.out.println("Size = " + integers.size() + " isEmpty = " + integers.isEmpty());


        //==============================================
        System.out.println();
        System.out.println("===== 4. Check add(index) and get(index) =======");
        integers.add(7, 99);
        System.out.println("add(7, 99). toString() = " + integers.toString());
        integers.add(15, 99);
        System.out.println("add(15, 99). toString() = " + integers.toString());
        try {
            integers.add(22, 99);
        }
        catch (Exception e)
        {
            System.out.println("add(22, 99). toString() = " + e.toString());
        }
        System.out.println("get(3) = " + integers.get(3));


        //==============================================
        System.out.println();
        System.out.println("===== 5. Check iterators() =======");
        Iterator<Integer> it = integers.iterator();
        System.out.print("next(): ");
        while (it.hasNext())
        {
            System.out.print(it.next() + " ");
        }
        System.out.println();


        //==============================================
        System.out.println();
        System.out.println("===== 6. Check ListIterator(index = 3) =======");
        ListIterator<Integer> lit = integers.listIterator(3);
        System.out.print("next(): ");
        while (lit.hasNext())
        {
            System.out.print(lit.next() + " ");
        }
        System.out.println();

        System.out.println("next() - 3 times, prev() - 2 times, next() - 4 times");
        lit = integers.listIterator(3);
        System.out.print(lit.next() + " ");
        System.out.print(lit.next() + " ");
        System.out.print(lit.next() + " ");

        System.out.print(lit.previous() + " ");
        System.out.print(lit.previous() + " ");

        System.out.print(lit.next() + " ");
        System.out.print(lit.next() + " ");
        System.out.print(lit.next() + " ");
        System.out.print(lit.next() + " ");
        System.out.println();

        System.out.println("it.add(77), it.add(88)");
        lit.add(77);
        lit.add(88);
        System.out.println(integers.toString());

        //==============================================
        System.out.println();
        System.out.println("===== 7. clear() =======");

        integers.clear();
        System.out.println(integers.toString());


        //==============================================
        System.out.println();
        System.out.println("===== 8. check Collections.addAll =======");

        Random random = new Random();

        for (int i = 0; i < 10; ++i)
        {
            int val = random.nextInt(100);
            integers.add(val);
        }
        System.out.println("Collection contain: " + integers.toString());
        Collections.addAll(integers, 1, 2, 3, 4, 5);
        System.out.println("Collection addAll(1,2,3,4,5): " + integers.toString());


        //==============================================
        System.out.println();
        System.out.println("===== 9. check Collections.copy =======");

        System.out.println("Collection contain: " + integers.toString());
        ArrayList<Integer> tempIntegers = new ArrayList<>();
        tempIntegers.add(6);
        tempIntegers.add(7);
        tempIntegers.add(8);
        tempIntegers.add(9);
        tempIntegers.add(10);

        Collections.copy(integers, tempIntegers);
        System.out.println("Collection copy from ArrayList(6,7,8,9,10): " + integers.toString());

        tempIntegers.clear();
        //выравниваем длинну коллекций
        for (int i = 0; i < integers.size(); ++i)
        {
            tempIntegers.add(i);
        }

        Collections.copy(tempIntegers, integers);
        System.out.println("Collection copy to ArrayList(): " + tempIntegers.toString());

        //==============================================
        System.out.println();
        System.out.println("===== 10. check Collections.sort =======");

        Collections.sort(integers);
        System.out.println("Collection.sort: " + integers.toString());


    }



}
