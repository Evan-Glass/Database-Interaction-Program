//prints the records in order, must change number in for loop to num of columns in table you are selecting
// https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
package test;
import java.sql.* ;
  
public class SQLServer
{
    public static void main( String[] args )
    {
        try
        {
            Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" ).newInstance();
            try
            {
                Connection con = DriverManager.getConnection( "jdbc:sqlserver://EVAN-PC;databaseName=Hw2;integratedSecurity=True" ); //integratedSecurity=True
  
                try
                {
                    Statement statement = con.createStatement();
                    
                    String SQL = "SELECT * FROM Movie";
                    
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
                        System.out.println("");
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

how to connect when using authentication other than windows authentication
*/