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

spring.mvc.hiddenmethod.filter.enabled=true

server.error.whitelabel.enabled=false
```
5. Run the application with `./gradlew bootRun`
6. Visit `localhost:8080` in your browser
7. Create a new user by visiting `localhost:8080/signup`
8. Log in with your new user by visiting `localhost:8080/login`
9. Visit `localhost:8080/users/{id}` to see a specific user's profile
10. Visit `localhost:8080/myprofile` to see your own profile

- You can follow another user by pressing the "Follow" button on their profile page
- You can post by navigating to your own profile and filling out the form at the bottom of the page
- You can see all users by visiting `localhost:8080/users`
- You can see all posts by visiting `localhost:8080/feed`