FROM maven:3.9-eclipse-temurin-8 AS build

COPY ./ ./

RUN mvn clean install

CMD ["java", "-jar", "target/controle-financeiro-0.0.1.jar"]