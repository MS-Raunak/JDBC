JDBC
=========
JDBC stands for Java Database Connectivity. 
It's an API (Application Programming Interface) provided by Java that allows Java applications to interact with databases. 
JDBC enables Java developers to execute SQL (Structured Query Language) statements, retrieve results, and perform database operations from within their Java code.

Database Vendors
==================
Database vendors are companies or organizations that develop, distribute, and support database management systems (DBMS) or database products. 
These vendors offer software solutions that allow users to store, organize, manage, and retrieve data efficiently.
For example,
Oracle Corporation, Microsoft Corporation, IBM Corporation, MySQL AB (now Oracle), Amazon Web Services (AWS), MongoDB, Inc etc

Steps to Connect Java Application with Database using JDBC
==========================================================
1) Load the Driver: Load the JDBC driver class using Class.forName() method. This step is necessary to register the driver with the DriverManager.
2) Establish Connection: Use DriverManager.getConnection() method to establish a connection to your database by providing the database URL, username, and password.
3) Create Statement/PreparedStatement: Create a Statement or PreparedStatement object to execute SQL queries against the database.
4) Execute SQL Queries: Use the executeQuery() method of Statement or PreparedStatement to execute SELECT queries, or executeUpdate() for INSERT, UPDATE, DELETE, and DDL (Data Definition Language) queries.
5) Process Results: If executing a SELECT query, process the ResultSet object returned by executeQuery() method to retrieve and process the query results.
6) Close Resources: Close the ResultSet, Statement/PreparedStatement, and Connection objects to release database resources and avoid memory leaks.

Example
=========

import java.sql.*;

public class JdbcExample {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish connection
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "username";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);

            // Step 3: Create statement
            statement = connection.createStatement();

            // Step 4: Execute SQL query
            resultSet = statement.executeQuery("SELECT * FROM my_table");

            // Step 5: Process results
            while (resultSet.next()) {
                // Process each row of the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // Process other columns as needed
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 6: Close resources in finally block
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


JDBC Drivers
==============
JDBC (Java Database Connectivity) drivers facilitate communication between Java applications and databases.
There are four types of JDBC drivers, each with its own characteristics and advantages:

1) Type 1: JDBC-ODBC Bridge Driver
=====================================
- This driver uses the JDBC-ODBC bridge to connect to the database via ODBC (Open Database Connectivity).
- It relies on native ODBC libraries provided by the operating system.
- Suitable for connecting to any ODBC-compliant database.
- Platform-dependent and may have performance overhead due to the additional layer.

2) Type 2: Native-API Driver
==================================
- This driver uses a database-specific native library (written in C or C++) to communicate directly with the database.
- The driver translates JDBC calls into database-specific calls using the native library.
- Provides better performance than Type 1 drivers as it bypasses the ODBC layer.
- Platform-dependent and requires a separate driver for each database vendor.

3) Type 3: Network Protocol Driver
==================================
- Also known as middleware driver, this driver uses a middle-tier server to translate JDBC calls into a database-independent protocol.
- The middle-tier server communicates with the database using a database-specific native driver.
- Offers database independence as it doesn't require a native library on the client-side.
- Provides flexibility and scalability but may introduce additional network latency.

4) Type 4: Thin Client Driver
==============================
- Also known as the Direct-to-Database Pure Java Driver, this driver communicates directly with the database server using a vendor-specific protocol.
- It doesn't rely on any native libraries or middleware servers.
- Offers the best performance and platform independence as it's written entirely in Java.
- Suitable for web applications and distributed systems where performance and portability are critical.

Here's a brief summary of the four JDBC driver types
=======================================================
Type 1: Uses ODBC bridge, platform-dependent, performance overhead.
Type 2: Uses native API, better performance, platform-dependent.
Type 3: Uses middleware, database-independent, additional network latency.
Type 4: Pure Java, direct communication, best performance, platform-independent.

Statement in JDBC
==================
In JDBC (Java Database Connectivity), a Statement is an interface provided by the java.sql package that represents a SQL statement to be executed against a database. It's used to send SQL queries, updates, and other SQL commands to the database.

Here are some key aspects of Statement:

Types of Statements:

Statement interface supports three types of SQL statements:
Statement
=============
Used for executing static SQL queries without parameters.

PreparedStatement
====================
Used for executing precompiled SQL queries with parameters, providing better performance and security.

CallableStatement
====================
Used for executing stored procedures in the database.

executeQuery(String sql)
=============================
Executes SQL SELECT queries and returns a ResultSet.

executeUpdate(String sql)
===========================
Executes SQL INSERT, UPDATE, DELETE, or DDL statements and returns the number of rows affected.

execute(String sql)
========================
Executes any SQL statement and returns a boolean indicating whether the first result is a ResultSet.


ResulSet
=============
- A ResultSet in JDBC (Java Database Connectivity) represents the result set of a database query. 
- It encapsulates the data returned by a SELECT query executed against a database. 
- The ResultSet interface provides methods for navigating through the rows of the result set, retrieving column values, and performing other operations on the data.

  Here are some key aspects of ResultSet:

Navigation:

The ResultSet cursor initially points to the position before the first row. You can move the cursor to the next row using the next() method and traverse through the result set sequentially.
Data Retrieval:

You can retrieve column values from the current row of the result set using methods like getInt(), getString(), getDouble(), etc., which correspond to the data types of the columns in the result set.
Positioning:

Methods like first(), last(), absolute(), and relative() allow you to position the cursor at specific rows within the result set.
Metadata:

The ResultSetMetaData interface provides metadata about the columns in the result set, such as column names, data types, and column properties.
Updating:

For result sets that are updatable (e.g., when the query includes the FOR UPDATE clause), you can update column values in the result set using methods like updateInt(), updateString(), etc., and then commit the changes to the database.
Closing:

After processing the result set, it's important to close it using the close() method to release database resources and avoid memory leaks.


Example
===========

import java.sql.*;

public class ResultSetExample {
    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a Statement object
            Statement statement = connection.createStatement();

            // Execute a SQL query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");

            // Process the results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                // Process other columns as needed
                System.out.println("ID: " + id + ", Name: " + name);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


Batch Processing
===================
- Batch processing in JDBC allows you to group multiple SQL statements into a batch and send them to the database in one go, reducing the number of round-trips between your Java application and the database server.
- This can lead to improved performance, especially when executing multiple similar SQL statements.

Example
============

import java.sql.*;

public class BatchProcessingExample {
    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Create a PreparedStatement object for batch processing
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO my_table (name) VALUES (?)");

            // Add multiple SQL statements to the batch
            preparedStatement.setString(1, "Name1");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Name2");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Name3");
            preparedStatement.addBatch();

            // Execute the batch
            int[] updateCounts = preparedStatement.executeBatch();

            // Process the update counts (optional)
            for (int count : updateCounts) {
                System.out.println("Rows affected: " + count);
            }

            // Close resources
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


Stored Procedure
====================
- A stored procedure is a group of SQL statements that are stored in the database and can be called by name from within a database application. 
- Stored procedures offer several benefits, including improved performance, code modularity, and enhanced security. 

Example
=========

import java.sql.*;

public class StoredProcedureExample {
    public static void main(String[] args) {
        try {
            // Establish connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Prepare a CallableStatement for calling the stored procedure
            CallableStatement callableStatement = connection.prepareCall("{CALL my_stored_procedure(?, ?)}");

            // Set input parameters (if any)
            callableStatement.setInt(1, 123);

            // Register output parameters (if any)
            callableStatement.registerOutParameter(2, Types.INTEGER);

            // Execute the stored procedure
            callableStatement.execute();

            // Retrieve output parameters (if any)
            int outputValue = callableStatement.getInt(2);
            System.out.println("Output parameter value: " + outputValue);

            // Close resources
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



