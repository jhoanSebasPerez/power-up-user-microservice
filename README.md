<br />
<div align="center">
<h3 align="center">PRAGMA POWER-UP - USER MICROSERVICE</h3>
  <p align="center">
    This repository contains the files related to the microservice that manages the <i><strong>Users</strong></i> of the ordering platform. 
  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)

### Recommended Tools
* IntelliJ Community [https://www.jetbrains.com/idea/download/](https://www.jetbrains.com/idea/download/)
* Postman [https://www.postman.com/downloads/](https://www.postman.com/downloads/)

### Installation

1. Clone the repository
2. Change directory
   ```sh
   cd power-up-user-microservice
   ```
3. Create a new database in MySQL called 'user-service'
4. Update the database connection settings with your Mysql configuration
   ```yml
   # src/main/resources/application-dev.yml
   spring:
      datasource:
          url: jdbc:mysql://localhost/powerup
          username: <your-username>
          password: <your-password>
   ```
5. After the tables are created execute <i>src/main/resources/data.sql</i> content to populate the database with initial data, 
it creates a <i>user</i>1 and assigns him the role of administrator

<!-- USAGE -->
## Usage

1. Right-click the class PowerUpApplication and choose Run
2. Open [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) in your web browser
3. search the <i>/auth/login</i> endpoint and login with userDni: 123, password: 1234
4. Copy token in authorize to send it each request
5. Test <i>user/create-owner</i> endpoint to validate each field in the request body

<!-- ROADMAP -->
## Tests

- Run Test classes found it <i>src/test/java</i>, These classes implement unit tests:
   _UserRestControllerTest_, _UserUseCaseTest_
- Execute them by right-click and choose Run