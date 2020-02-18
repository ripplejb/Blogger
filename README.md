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

### How to setup?

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
9. In order to start writing a blog, you must login with your gmail account. I have created test app on google to use OAuth single sign on functionality. If you are not feeling comfortable using your google account. I understand. All you have to do is, [create your free OAuth 2.0 client credentials](https://support.google.com/cloud/answer/6158849?hl=en). Then change the settings in the **application.yml** as mentioned below.
  ```
    oauth2:
      enabled: true
      clients:
        google:
          client-id: '393123747080-cgu69mq0plfbff1aimheoa52jn32qbuh.apps.googleusercontent.com'
          client-secret: 'jxQaD9Y_KYH8G9mk-7obAxB8'
          openid:
            issuer: 'https://accounts.google.com'    
  ```
  10. If port conflicts, change the port in the **application.yml**. My google OAuth is set up to use the port **8080**. I recommend you to create your own google client credential as mentioned in **step 9** and set up the port in the google app too.
  ```
      server:
        port: 8080
  ```

  ## How to run?
  
  1. Go to the project root folder on terminal.
  2. Run **gradlew run**.
  3. Go to http://localhost:8080 in the browser. If you have changed the port number, change it here too.
  4. On the Top Right corner, there is a **Sign in** button. Use it to sign in with your gmail account.
  5. **New Article** button will let you navigate to the article editor.
  6. **Home** button will take you back to the landing page.
  7. On the **Home** page, only the parent articles/blogs will be displayed. For example, if some one write an article/blog and then some one type comment on the article. The comments will not be displayed on the front page.
  8. Any one will be able to **view** on the blog. However, only signed in person will be able to comment.
  
