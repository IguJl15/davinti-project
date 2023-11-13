# syntax=docker/dockerfile:experimental

FROM gradle:7.4.2-jdk17 AS deps

WORKDIR /app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY backend backend

RUN --mount=type=cache,target=/root/.gradle ./gradlew build -x test
RUN mkdir -p ./build/libs/dependency
WORKDIR /app/backend/build/libs/dependency
RUN jar -xf ../*.jar


FROM gradle:7.4.2-jdk17 AS development

WORKDIR /app
COPY ./live.sh ./

# RUN apk add inotify-tools
RUN apt update
RUN apt -y install inotify-tools
RUN chmod +x ./live.sh

ENTRYPOINT [ "./live.sh" ]


FROM openjdk:8-jdk as release

VOLUME /tmp



ARG DEPENDENCY=/workspace/app/backend/build/libs/dependency
COPY --from=deps ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=deps ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=deps ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.davintiproject.backend.DaVintiBackendApplication"]