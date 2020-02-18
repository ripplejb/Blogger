# Blogger

### Development Environment
- **OS:** Ubuntu 19.10
- **Java:** OpenJDK 11.0.6
- **Language:** Java
- **Frameworks:**
  - Micronaut
  - Hibernate
- **UI Framework:** Angular 9
- **Database:** PostgreSQL

### How to run?

1. Install OpenJDK 11.0.6.
2. Install PostgreSQL.
3. Download/Clone the code in a folder.
4. Create an empty database in PostgreSQL. Setup the values mentioned below according to your database setup. 
  ```
  datasources:
  default:
    url: "jdbc:postgresql://localhost:5432/<DATABASE_NAME>"
    username: <USER_NAME>
    password: <PASSWORD>
    driverClassName: org.postgresql.Driver
  ```
5. Go to the app folder on the terminal.
6. If working on linux, make sure the **gradlew** file has the execute permissoin.
7. 
