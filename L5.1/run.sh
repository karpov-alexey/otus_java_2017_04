#!/usr/bin/env bash

mvn clean package
java -jar target/my_test_framework.jar
