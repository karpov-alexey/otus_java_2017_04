#!/usr/bin/env bash

appPath="target/gc_statistics.jar"


options=(
    "-Xms512m -Xms512m -XX:+UseSerialGC"
    "-Xms512m -Xms512m -XX:+UseParallelGC"
    "-Xms512m -Xms512m -XX:+UseParallelGC -XX:+UseParallelOldGC"
    "-Xms512m -Xms512m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC"
    "-Xms512m -Xms512m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark"
    "-Xms512m -Xms512m -XX:+UseG1GC"
)

if [ ! -f $appPath ]; then
    mvn clean package
fi

for opts in "${options[@]}"; do
    echo "Running with opts: $opts"
    java $opts -jar $appPath
    echo "===================================================="
done;