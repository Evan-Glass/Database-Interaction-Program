//prints the records in order, must change number in for loop to num of columns in table you are selecting
// https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
package JavaFX_Testing;
import java.sql.* ;

public class DBConnect
{
    private static Connection conn;
    private static String url = "jdbc:sqlserver://EVAN-PC;databaseName=final database;integratedSecurity=True";
                //school "jdbc:sqlserver://APL-SQL12;databaseName=zjeFINAL;user=zje520;password=zje520123"
                //home   "jdbc:sqlserver://EVAN-PC;databaseName=final database;integratedSecurity=True"
    public static String getURL(String url)
    {
        String sendURL = url;
        return sendURL;
    }
    
    public static Connection connect() throws SQLException
    {
        try
        {
            Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" ).newInstance();
        }catch(ClassNotFoundException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }catch(InstantiationException ie){
            System.err.println("Error: "+ie.getMessage());
        }catch(IllegalAccessException iae){
            System.err.println("Error: "+iae.getMessage());
        }
       
                conn = DriverManager.getConnection( getURL(url) );
                return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;
    }

}

    



/*For windows auth
2) unzip the file and go to sqljdbc_version\fra\auth\x86 or \x64
3) copy the sqljdbc_auth.dll to C:\Program Files\Java\jre_Version\bin
4) Finally restart eclipse

make sure tcp/ip is enabled in SQL configuration manager


for SQL authentication, either running into an invalid port (default is 1433) or TCP IP is disabled (unlikely). That, or firewall issue.

syntax for sending sql commands that dont return anything (loop ResultSet is useless)

GUI using JavaFx
	start page with a few decision buttons such as Database location configuration, run SQL commands
	Database location editor: a list with type boxes to update string values, which build a larger string based on results then added to DriverManager
	run SQL commands: a set of a few embedded sql commands for security reasons (to prevent full access to database)
		parameterized sql injection code to prevent injections

Alert pop up for error handling, put scene pop up (confirmbox) in exception handlers


*/

        

        /*System.out.print("Are you using SQL Authentication or Windows Authentication?"); //create button for each? or type 1 or 2 for their respective choices?
        System.out.print("SQL Server name:");
        System.out.print("Enter port number:");   //leave blank for default 1433.
        System.out.print("Database name:");
        if Windows Authentication: String = "integratedSecurity=True"
        if SQL Authentication:
            System.out.print("Enter username:");
            System.out.print("Enter password:");   //leave blank if none



        replace DriverManager.getConnection( contents ) with a link to a string that is build from previous questions.

        */