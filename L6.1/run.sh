#!/usr/bin/env bash

mvn clean package
java -jar target/test_framework.jar ru.otus.example.ExampleClassTest
