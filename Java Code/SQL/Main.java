//prints the records in order, must change number in for loop to num of columns in table you are selecting
// https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
package SQL;
import java.sql.* ;

public class Main
{
    public static void main( String[] args )
    {
        String username = "";
        String password = "";
        String server = "";
        String Database = "";
        String WinAuth = "integratedSecurity=True";
        String port = "";

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
        try
        {
            Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" ).newInstance();
            try
            {
                Connection con = DriverManager.getConnection( "jdbc:sqlserver://EVAN-PC;databaseName=HW2;integratedSecurity=True" );
                //school "jdbc:sqlserver://APL-SQL12;databaseName=zjeFINAL;user=zje520;password=zje520123"
                //home   "jdbc:sqlserver://EVAN-PC;databaseName=HW2;integratedSecurity=True"

                try
                {
                    Statement statement = con.createStatement();

                    String SQL = "select * from MOVIE";

                    ResultSet rs = statement.executeQuery(SQL);
                    ResultSetMetaData meta = rs.getMetaData();
                    int colCount = meta.getColumnCount();
                    String colName = meta.getColumnName(1);


                    for(int i = 1 ; i <= colCount; i++)
                    {
                        System.out.print(meta.getColumnName(i) + " , ");
                    }
                    System.out.println();

                    while ( rs.next() )
                    {
                        for (int i = 1; i <= colCount ; i++) {
                            if (i > 1) System.out.print(",  ");
                            String columnValue = rs.getString(i);
                            System.out.print(columnValue + " ");
                        }
                        System.out.println();
                    }
                    /*
                        {
                        //System.out.println( rs.getString("1") );
                        for(int i = 1 ; i <= 4; i++)
                        {
                            System.out.print(rs.getString(i) + " ");
                        }

                    System.out.println();
                    }
                    */
                    rs.close();
                    statement.close();
                }
                catch ( SQLException e )
                {
                    System.out.println( "JDBC error: " + e );
                }
                finally
                {
                    con.close();
                }
            }
            catch( SQLException e )
            {
                System.out.println( "could not get JDBC connection: " + e );
            }
        }
        catch( Exception e )
        {
            System.out.println( "could not load JDBC driver: " + e );
        }
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