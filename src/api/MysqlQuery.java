package api;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	
	ResultSet getTestById() throws ClassNotFoundException 
	{
		
		//ResultSet rs = null;
	   // ArrayList result = new ArrayList();
	    try (Connection con = getConnection())
	    {
	    	String query = testById + params;
	    	
	        Statement stmt = con.createStatement();
	        PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();  
            if(rs.getFetchSize() != 0)
            {
            	while (rs.next()) {
    				System.out.println("entro en el while");
    				//rs.getString("testDato1")
    	        }
            }
            else
            {
            	System.out.println("error en query");
            }
            
        	return null;
        	
	    } catch (SQLException e1) {
	    	System.out.println("cosa2");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    System.out.println("nada");
		return null;
	  
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
