# github-stats
[![build](https://github.com/schambeck/github-stats/actions/workflows/gradle.yml/badge.svg)](https://github.com/schambeck/github-stats/actions/workflows/gradle.yml)

## Description

GitHub Stats API.

## Tech Stack

- Java 11
- Spring Boot
- RabbitMQ
- Quartz
- PostgreSQL, Flyway
- Swagger
- JUnit 5, Mockito, JaCoCo

## Project Setup

```bash
$ ./gradlew clean build
```

### Running the app locally

```bash
# PostgreSQL and RabbitMQ
$ docker-compose up -d

# Starting up
$ java -jar build/libs/github-stats-1.0.0.jar
```

## Stay in touch

- Swagger UI - http://localhost:8080
- Stats API - http://localhost:8080/stats
