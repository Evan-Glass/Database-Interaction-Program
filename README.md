# SQL-Java-programming
***View raw


Repository for a current project I am working on.
Creating a Java program with a GUI, which will be able to connect to SQL server either on a local machine or database hosted over
the internet. 

I would put line numbers, but I am updating the program often

SQLServer_1 first creates an instance with microsoft JDBC server.
As of right now, the program takes a static connection string with sql server name, port number, database name, and login method (SQL authentication or windows integrated).
  (will update to accept a string which is built from input from GUI)

ResultSet is then used to store the object retrieved from the database via custom SQL query, and instantiates another object for metadata.

Output is parsed through an initial for loop, which prints the column names of the database through meta data object.

The next for loop is used to parse the actual data from resultset, and formats it under the column names. 
  (mostly used for select statements, will add seperate methods for manipulative SQL commands)
  
each step has exception handling.

TroubleShooting: 


"JDBC error: ..."
1) Check that JDBC is installed in your JDE classpath, and you are running the most recent version of JDK.
2) An issue with the code itself, probably because SQL is returning an invalid object that can't be stored in ResultSet.
    Or the object could not be parsed correctly.
    
"could not get JDBC connection: ..."
1) Check to make sure all of the information is correct in the Connection string.
   Port number is default 1433, check your IP settings for your SQL port.
   Check Server name.
   Check Database name.
   
2) Authentication.
For windows auth
  1) unzip the JDBC file and go to sqljdbc_version\fra\auth\x86 or \x64
  2) copy the sqljdbc_auth.dll to C:\Program Files\Java\jre_Version\bin
  3) Finally restart JDE*/
      Syntax: "integratedSecurity=true"

For SQL authentication.
  1) Be sure the account is set up correctly and username / password are typed correctly.
      Syntax= "user=----password=----"

"Could not load JDBC driver: ..."
  1) This error is most likely because you do not have the correct version installed in the correct location.
  
ScreenShots:
(current output example)
https://gyazo.com/5d83146ac3e5de629fdc71f57339a330

  

