#!/bin/sh

numero=0
while inotifywait -r -e modify ./backend/src;
do
  ./gradlew clean build --debug
done >/dev/null 2>&1 &

./gradlew bootRun