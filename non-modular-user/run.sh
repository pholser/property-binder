#!/bin/bash

set JAVA_HOME=/Library/Java/JavaVirtualMachines/adoptopenjdk-11.jdk/Contents/Home

rm -rf target/*

find ./src -name '*.java' > target/sources.txt

javac \
  -source 11 \
  -target 11 \
  --source-path src \
  --class-path /Users/pholser/.m2/repository/com/pholser/com-pholser-util-properties-propertybinder/5.0-SNAPSHOT/com-pholser-util-properties-propertybinder-5.0-SNAPSHOT.jar \
  -d target \
  @target/sources.txt

java \
  --class-path ./conf:./target:/Users/pholser/.m2/repository/com/pholser/com-pholser-util-properties-propertybinder/5.0-SNAPSHOT/com-pholser-util-properties-propertybinder-5.0-SNAPSHOT.jar:/Users/pholser/.m2/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar:/Users/pholser/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/Users/pholser/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar \
  com.pholser.util.properties.propertybinder.nonmodularuser.NonModularUser

