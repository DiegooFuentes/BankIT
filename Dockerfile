FROM openjdk:17-jdk-slim
COPY . /app
WORKDIR /app
RUN chmod +x gradlew
RUN ./gradlew build
EXPOSE 0.0.0.0
CMD ["java", "-jar", "build/libs/homebanking.jar"]
