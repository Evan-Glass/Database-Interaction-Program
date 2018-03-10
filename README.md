# SQL-Java-programming
***View raw


Repository for a current project I am working on.
Creating a Java program with a GUI, which will be able to connect to SQL server either on a local machine or database hosted over
the internet. 

The program first creates an instance with microsoft JDBC server.
As of right now, the program takes a static connection string with sql server name, port number, database name, and login method.
  (will update to accept a string which is built from GUI string objects)

ResultSet is then used to store the object retrieved from the database via custom SQL query.

Output is parsed through an initial for loop, which prints the column names of the database through meta data object.

The next for loop is used to parse the actual data from resultset, and formats it under the column names. 
  (mostly used for select statements, will add seperate methods for manipulative SQL commands)
  
each step has exception handling.


going to dinner, will update later tonight
