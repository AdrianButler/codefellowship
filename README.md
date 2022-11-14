# Codefellowship

## Setup

1. Clone this repository to your local machine
2. Create a new database called `codefellowship`
3. Create a new file called `application.properties` in the `src/main/resources` directory
4. Add the following to `application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/codefellowship
spring.datasource.username=ENTER YOUR DB USERNAME HERE
spring.datasource.password=ENTER YOUR PASSWORD HERE

spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
```
5. Run the application with `./gradlew bootRun`