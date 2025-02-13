# Feedify
Your Friendly Neighborhood Social-Media Platform
![image](https://github.com/user-attachments/assets/453d2a1f-5f5f-44e9-8a2b-e107cea969cd)



## Setup
## 1. Backend Setup
1. Clone the repository
2. Install the dependencies
    - Java 23
    - MySQL
    Check if you have Java and MySQL installed by running the following commands:
    ```bash
    java --version
    ```
    ```bash
    mysql --version
    ```
3. Create a database in MySQL
    ```sql
    CREATE DATABASE feedify;
    ```
4. Create the user for the database
    ```sql
    CREATE USER 'feedify_admin'@'localhost' IDENTIFIED BY 'Admin@123';
    ```
5. Grant the user all privileges on the database
    ```sql
    GRANT ALL PRIVILEGES ON feedify.* TO 'feedify_admin'@'localhost';
    ```
6. Apply changes to the database
    ```sql
    FLUSH PRIVILEGES;
    ``` 
7. For maven 
    ```bash
    mvn clean install
    ```
## 2. frontend Setup

1. Open frontend folder
    ```bash
    cd frontend
    ```
2. Install all dependencies
    ```bash
    npm intstall
    ``` 
3. Run frontend server
    ```bash
    npm run dev
    ```    
### IMPORTANT NOTES
- Springboot will automatically create the tables in the database
- If you're creating a new entity, stop the springboot application so it doesn't instantly create the table while you're still working on the entity
- If you made a mistake in the entity, delete the table from the database and restart the springboot application
    ```
    DROP TABLE IF EXISTS <table_name>;
    ```
- To check schema of the database
    ```
    SHOW TABLES;
    ```
- To check the schema of a table
    ```
    DESCRIBE <table_name>;
    ```

## Timeline
### 1. Milestone 1 (Done)
### 2. Milestone 2 (In Progress)
