package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import api.MysqlConnection;

public class MysqlQuery {
	static ResultSet rs(String query) throws ClassNotFoundException 
	{
	    ArrayList result = new ArrayList();
	    try (Connection con = getConnection())
	    {
	        try(PreparedStatement stmt = con.prepareStatement(query))
	        {
	            try(ResultSet rs = stmt.executeQuery())
	            {	            	
	            	return rs;
	            } 
	            catch (SQLException e) 
	            {	            	
	                e.printStackTrace();
	            }
	        }
	    } catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    return null;
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
