
package miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anuj
 */

//Singleton class for database connection ..........................
public class Database
{
        private static Connection con = null;
        static String url="jdbc:mysql://127.0.0.1/students";
        static String user="root";
        static String pass="";
        private Database()   //Private constructor 
        {
            
        }
	public static Connection getConnection() throws ClassNotFoundException, SQLException 
	{ 
                if(con==null)
                {
                    Class.forName("com.mysql.jdbc.Driver"); 
		    con = DriverManager.getConnection(url, user, pass); 
                }
		return con; //It will return the same object every time 
	} 
} 
