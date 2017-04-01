package ru.otus;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexey on 01.04.2017.
 */

class Test
{
    void process() throws IOException {
        System.out.println("Helo world");
        CSVReader reader = new CSVReader(new FileReader("emails.csv"));
        List<String> emails = reader.readAll().stream().map(line -> line[0].trim()).collect(Collectors.toList());

        System.out.println("Emails count: " + emails.size());
    }
}

public class Main  {
    public static void main(String[] args) throws IOException {
        new Test().process();
    }
}
