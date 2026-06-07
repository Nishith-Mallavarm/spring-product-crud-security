FROM eclipse-temurin

WORKDIR /eCommerceApp

COPY target/demo-0.0.1-SNAPSHOT.jar /eCommerceApp

EXPOSE 9090

CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]