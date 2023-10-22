# syntax=docker/dockerfile:experimental

FROM gradle:7.4.2-jdk17-alpine AS deps

WORKDIR /app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY backend backend

RUN --mount=type=cache,target=/root/.gradle ./gradlew build -x test
RUN mkdir -p build/libs/dependency && (cd build/libs/dependency; jar -xf ../*.jar)


FROM gradle:7.4.2-jdk17-alpine AS development

WORKDIR /app
COPY ./live.sh ./

RUN apk add inotify-tools
RUN chmod +x ./live.sh

ENTRYPOINT [ "./live.sh" ]


FROM openjdk:8-jdk-alpine as release

VOLUME /tmp
ARG DEPENDENCY=/workspace/app/build/libs/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.Application"]