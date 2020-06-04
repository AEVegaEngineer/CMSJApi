package api;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Statement;

import api.MysqlConnection;

public class MysqlQuery {
	
	private static String testById = "Select * from tablatest where testId = ";
	private static String params;
	
	public void setTestById(String testById) {
		this.testById = testById;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}	
	
	public ResultSet getTestById() throws ClassNotFoundException 
	{
		
		try (Connection con = getConnection())
	    {
			ArrayList arr = new ArrayList();
			Statement st = con.createStatement();
			String sql = (testById + params);
			ResultSet rs = st.executeQuery(sql);
			con.close();
			return rs;
	    } catch (SQLException e1) {
	    	System.out.println("Excepción en la conexión!");
			e1.printStackTrace();
		}
		return null;
	  
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
