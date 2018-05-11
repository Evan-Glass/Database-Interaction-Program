# SQL-Java-programming
***View raw


Repository for a current project I am working on.
Creating a JavaFX program, which connectst to DBMS either on a local machine or database hosted over the internet. 

I would put line numbers, but I am updating the program often

Currently the JAR file may not work on your end, make sure you have the newest version of JDK installed. Connection pane is not set up correctly at this time, so you would only be able to look around the GUI without any connection to a database.

To Do:

-Create incremental queries. i.e. you put information into two places, movie theater and movie price, for example, and you receive a new query.

-regex or input validation preventing the user from spamming the server with false requests

-Further security for SQL injection

-More user-friendly layout

-input default values into connection interface, along with an option to save future default values based on user variables

-implement a troubleshooting / help option into the GUI in case people have issues

	
Future possibilities:

-store a local version of database to pull data from, to decrease amount of processing power needed from the server from user interaction

-create a Login system, where administrators would have access to edit tables, and potentially other elevated priveleges

-option for choosing a connection other than SQL server in the connection scene. This would require new logic in buttons, changing queries behind buttons based on which database connection was given.

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


