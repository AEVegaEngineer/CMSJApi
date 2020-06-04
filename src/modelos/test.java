package modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.gson.Gson;

import api.MysqlConnection;

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
	
	public	String getTestAll() throws ClassNotFoundException {
		String testJsonString = "";
		try (Connection con = getConnection())
	    {
			String sql = "Select * from tablatest";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				this.testId = rs.getInt("testId");
				this.testDato1 = rs.getInt("testDato1");
				this.testDato2 = rs.getInt("testDato2");
				this.testDato3 = rs.getInt("testDato3");
				final Gson gson = new Gson();
				testJsonString = gson.toJson(this);
			}
			con.close();
			return testJsonString;
	    } catch (SQLException e1) {
	    	System.out.println("Salio por el catch"); 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "Error. La consulta está mal formada.";
	}
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
