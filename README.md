# Blogger

### Development Environment
- **OS:** Ubuntu 19.10
- **Java:** OpenJDK 11.0.6
- **Language:** Java
- **Frameworks:**
  - Micronaut
  - Hibernate
- **UI Framework:** Angular 9.0.1
- **Database:** PostgreSQL

### How to run?

1. Install OpenJDK 11.0.6.
2. Install PostgreSQL.
3. Install latest node.js, angular CLI 9.0.1
4. Download/Clone the code in a folder.
5. Create an empty database in PostgreSQL. Setup the values mentioned below according to your database setup in the **application.yml**. 
  ```
  datasources:
  default:
    url: "jdbc:postgresql://localhost:5432/<DATABASE_NAME>"
    username: <USER_NAME>
    password: <PASSWORD>
    driverClassName: org.postgresql.Driver
  ```
6. Go to the app folder on the terminal.
7. If working on linux, make sure the **CopyClientApp.sh** and **gradlew** files have the execute permissoin.
8. Run **gradlew** from the terminal. This will first compile the angular client and then the **blogger** project.
9. If port conflicts, change the port in the **application.yml**.
  ```
      server:
      port: 8081
  ```
