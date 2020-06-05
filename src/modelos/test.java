package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.json.JsonObject;

import api.MysqlConnection;
import io.github.mattvass.resultsetmapper.JsonResultSet;

public class Test {
	private int testId;
	private int testDato1;
	private int testDato2;
	private int testDato3;
		
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getTestDato1() {
		return testDato1;
	}
	public void setTestDato1(int testDato1) {
		this.testDato1 = testDato1;
	}
	public int getTestDato2() {
		return testDato2;
	}	
	public void setTestDato2(int testDato2) {
		this.testDato2 = testDato2;
	}
	public int getTestDato3() {
		return testDato3;
	}
	public void setTestDato3(int testDato3) {
		this.testDato3 = testDato3;
	}
	
	public	JsonObject getTestAll() throws ClassNotFoundException {
		try (Connection con = getConnection())
	    {
			String sql = "Select * from tablatest";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			JsonObject a = new JsonResultSet().toJson(rs);				
			con.close();					
			return a;			
			
	    } catch (SQLException e1) {
	    	System.out.println("Salio por el catch"); 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
	
	public ArrayList resultSetToArrayList(ResultSet rs){
		java.sql.ResultSetMetaData md;
		try {
			md = rs.getMetaData();
		
			int columns = md.getColumnCount();
			ArrayList results = new ArrayList();
		  
			while (rs.next()){
				HashMap row = new HashMap();
				results.add(row);
				for(int i=1; i<=columns; i++){
					row.put(md.getColumnName(i),rs.getObject(i));
				}
			}
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error parseando ResultSet a ArrayList");			
			e.printStackTrace();
			return null;
		}
	}
	
}
