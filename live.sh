#!/bin/sh

while inotifywait -r -e modify ./backend/src;
do
  ./gradlew build --debug
done >/dev/null 2>&1 &

./gradlew bootRun