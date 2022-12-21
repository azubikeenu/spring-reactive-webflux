FROM openjdk:11  as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install


FROM openjdk:11
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/*.jar /app/*.jar

RUN sh -c 'touch /app/*.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/*.jar" ]