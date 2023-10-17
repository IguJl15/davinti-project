FROM gradle:7.4.2-jdk17

WORKDIR /app

COPY backend backend
COPY *.gradle .

RUN gradle bootJar

ENTRYPOINT ["java","-jar","backend/build/libs/backend-0.0.1.jar"]
