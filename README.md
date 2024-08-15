## JDBC GUI Demo Application

### Overview
Simple Java Application that demonstrates the use of **JDBC** (Java Database Connectivity) to connect to a MySQL database. The application also includes a graphical user interface (GUI) built using **Java Swing**. 

### Features
- Connects to a MySQL database using JDBC
- Supports basic database operations such as adding, removing, and searching for users
- Provides a simple and intuitive interface for interacting with the database

### Prerequisites
Before running the application, ensure you have the following:
- Empty MySQL database which you will later use to import `jdbc_demo.sql` schema from this repository
- The JDBC driver required to connect to MySQL 

### Setup Instructions

1. Clone the Repository:
   
   ```bash
   git clone https://github.com/petarmilunovic/jdbc-demo.git
   cd jdbc-demo

3. Add MySQL Connector/J to your project:
- Download the `mysql-connector-java-8.0.22` library  from the [MySQL website](https://downloads.mysql.com/archives/c-j/).
- Add the JAR file to your project’s classpath.

3. Configure Database Connection:
- Import the provided database schema `jdbc_demo.sql` into your **empty** MySQL database. The schema is ****essential**** as it contains the specific columns and data types required by the application:
  
    ```bash
    mysql -u your-username -p your-empty-database < "absolute_path/to/jdbc_demo.sql"
- Modify the `connect()` method in `Database.java` file to use your database’s details and credentials.

4. Run the Application:
- Compile and run the application from your IDE.
- Use the GUI to perform CRUD operations on the database.
