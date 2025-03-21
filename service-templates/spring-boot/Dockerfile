FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make the Maven wrapper executable
RUN chmod +x ./mvnw

# Download dependencies as a separate step to leverage Docker cache
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN ./mvnw package -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp

# Add Spring Boot health check
HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
  CMD wget -q --spider http://localhost:8080/actuator/health || exit 1

# Copy the built application from the build stage
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Add a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Set OpenJDK runtime options
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Set Spring Boot profile to production
ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "${JAVA_OPTS}", "-cp", "app:app/lib/*", "com.insurance.service.ServiceApplication"]

# Expose the port the service runs on
EXPOSE 8080 