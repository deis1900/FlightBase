FROM openjdk:12-alpine

COPY target/flightbase-*.war /flightbase.war
EXPOSE 8082
CMD ["java", "-jar", "/flightbase.war"]