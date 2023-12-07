# syntax=docker/dockerfile:experimental

FROM gradle:7.4.2-jdk17 AS deps

WORKDIR /app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY ./backend ./backend

RUN --mount=type=cache,target=/root/.gradle gradle build -x test
RUN mkdir -p ./build/libs/dependency
WORKDIR /app/backend/build/libs/dependency
RUN jar -xf ../backend-0.0.1.jar


FROM gradle:7.4.2-jdk17 AS development

WORKDIR /app
COPY ./live.sh ./

# RUN apk add inotify-tools
RUN apt update
RUN apt -y install inotify-tools
RUN chmod +x ./live.sh

ENTRYPOINT [ "./live.sh" ]


FROM openjdk:17-jdk as release

VOLUME /tmp



ARG DEPENDENCY=/app/backend/build/libs/dependency
COPY --from=deps ${DEPENDENCY}/BOOT-INF/lib /davinti/lib
COPY --from=deps ${DEPENDENCY}/META-INF /davinti/META-INF
COPY --from=deps ${DEPENDENCY}/BOOT-INF/classes /davinti

ENTRYPOINT ["java","-cp","/davinti:/davinti/lib/*","com.davintiproject.backend.DaVintiBackendApplicationKt"]
# ENTRYPOINT [ "sleep", "infinity" ]