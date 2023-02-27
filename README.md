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

### Starting infra (PostgreSQL and RabbitMQ)

```bash
$ docker-compose up -d
```

### Building project

```bash
$ ./gradlew clean build
```

### Running the app locally

```bash
$ java -jar build/libs/github-stats-1.0.0.jar
```

### Generate GitHub Stats

    curl -X POST http://localhost:8080/stats

### Webhook Site

    https://webhook.site/afd6052e-5ef8-4a0a-b82f-d54ae4ed9a7b

## Tests

### Running unit tests

```bash
$ ./gradlew clean test testCodeCoverageReport
```

### Unit report

    build/reports/tests/test/index.html

### Coverage report

    build/reports/jacoco/testCodeCoverageReport/html/index.html

## Stay in touch

### Swagger

    http://localhost:8080

### RabbitMQ Web Interface

    URL : http://localhost:15672
    User: guest
    Pass: guest
