package ru.otus.example;

import java.util.ArrayList;
import java.util.List;

public class ExampleClass {

    List<String> stringList = new ArrayList<>();

    public int getSize()
    {
        return  stringList.size();
    }

    public boolean add(String string)
    {
        stringList.add(string);
        return true;
    }

}
