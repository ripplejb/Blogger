#
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
        url: "jdbc:postgresql://localhost:5432/blogger"
        username: ripal
        password: falguni
        driverClassName: org.postgresql.Driver

```

1. Go to the app folder on the terminal.
2. If working on Linux, make sure the **CopyClientApp.sh** and **gradlew** files have the execute permission.
3. Run **gradlew** from the terminal. The **gradlew** will first compile the angular client and then the **blogger** project.
4. To start writing a blog, you must log in with your gmail account. I have created a test app on google to use OAuth single sign-on functionality. If you are not feeling comfortable using your google account. I understand. All you have to do is, [create your free OAuth 2.0 client credentials](https://support.google.com/cloud/answer/6158849?hl=en). Then change the settings in the **application.yml** as mentioned below.

```
    oauth2:
      enabled: true
      clients:
        google:
          client-id: '*******'
          client-secret: '*******'
          openid:
            issuer: 'https://accounts.google.com'

```

1. If port conflicts, change the port in the **application.yml**. I setup the google OAuth to use the port **8080**. I recommend you to create your google client credential as mentioned in **step 9** and set up the port in the google app too.

```
  server:
    port: 8080

```


### How to run?

1. Go to the project root folder on the terminal.
2. Run **gradlew run**.
3. Go to [http://localhost:8080](http://localhost:8080/) in the browser. If you have changed the port number, change it here too.
4. On the Top Right corner, there is a **Sign in** button. Use it to sign in with your **Gmail** account.
5. The **new Article** button will let you navigate to the article editor.
6. The **Home** button will take you back to the landing page.
7. The **Home** page will display only the parent articles/blogs. It will not show the comments on the article/blog on the front page.
8. Anyone will be able to **view** the blog. However, only signed in person will be able to comment.
