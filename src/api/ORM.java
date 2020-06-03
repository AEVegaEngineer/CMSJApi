package api;
import java.sql.ResultSet;
import java.sql.SQLException;

import api.MysqlQuery;
public class ORM {
	private String testById = "Select * from tablatest where testId = ";
	private String params;
	public String getParams() {
		return params;
	}
	public void getTestById(String testById) throws ClassNotFoundException, SQLException {
		String res = this.getQueryGetTestById() + testById;
		ResultSet rs = MysqlQuery.rs(res);
		while (rs.next()) 
    	{
    		System.out.println(rs.getString("testDato1") + rs.getString("testDato2") + rs.getString("testDato3"));
    	}
	}
	
	
}
