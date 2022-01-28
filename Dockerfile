FROM maven:3.8.2-jdk-8
WORKDIR /real-estate-crud-application
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run
