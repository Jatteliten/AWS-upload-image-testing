FROM gradle:8.9-alpine as gradle-build

COPY ./ /app/

WORKDIR /app

RUN ./gradlew clean build

FROM amazoncorretto:21-alpine

COPY --from=gradle-build /app/build/libs/awsTesting-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app.jar"]