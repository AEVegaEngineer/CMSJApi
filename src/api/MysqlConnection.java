package api;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

// This class can be used to initialize the database connection 
public class MysqlConnection { 
	protected static Connection initializeDatabase() 
		throws SQLException, ClassNotFoundException 
	{ 
		// Initialize all the information regarding 
		// Database Connection 
		String dbDriver = "com.mysql.cj.jdbc.Driver"; 
		String dbURL = "jdbc:mysql://localhost:3306/"; 
		// Database name to access 
		String dbName = "datostest"; 
		String dbUsername = "root"; 
		String dbPassword = ""; 
		String cosaHorrorosa = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

		Class.forName(dbDriver); 
		Connection con = DriverManager.getConnection(dbURL + dbName + cosaHorrorosa, 
													dbUsername, 
													dbPassword); 
		return con; 
	} 
} 

