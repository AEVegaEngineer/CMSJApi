package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import api.MysqlConnection;
import io.github.mattvass.resultsetmapper.JsonResultSet;

public class Consultas {
	public	JsonObject consultar(String sql) throws ClassNotFoundException {
		try (Connection con = getConnection())
	    {	
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);	
			JsonObject resultados = new JsonResultSet().toJson(rs);
			JsonObjectBuilder jsonBuilder = Json.createObjectBuilder()
					.add("status",200)
					.add("resultados", resultados);
			/*
			
			JsonObject obj = null;
			//obj = jsonObjectToBuilder(obj).add("status", 200).build();
			
			obj = jsonBuilder.add("resultado",resultados);
			//obj.put("resultado",resultados);
			 */
			//System.out.println(obj);	
			con.close();					
			return (JsonObject) jsonBuilder;			
			
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
	private JsonObjectBuilder jsonObjectToBuilder(JsonObject jo) {
	    JsonObjectBuilder job = Json.createObjectBuilder();

	    for (Entry<String, JsonValue> entry : jo.entrySet()) {
	        job.add(entry.getKey(), entry.getValue());
	    }

	    return job;
	}
}
