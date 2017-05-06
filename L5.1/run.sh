#!/usr/bin/env bash

mvn clean package
java -jar target/test_framework.jar classes ru.otus.example.ExampleClassTest
java -jar target/test_framework.jar packages ru.otus.example
