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

import org.json.simple.JSONObject;

import api.MysqlConnection;
import io.github.mattvass.resultsetmapper.JsonResultSet;

public class Consultas {
	public	JSONObject consultar(String sql) throws ClassNotFoundException {
		try (Connection con = getConnection())
	    {	
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);	
			//JsonObject resultados = new JsonResultSet().toJson(rs);
			//JsonObject statuscode = new JsonObject("status", "200");
			//statuscode.put;
			JSONObject main = new JSONObject();
			main.put("status", "200 OK");
			JsonObject result = new JsonResultSet().toJson(rs);
			if (result.get("results").equals(new ArrayList<>()))
			{
				main.put("resultado", "No se han obtenido resultados");
			}
			else
			{
				main.put("resultado", result.get("results"));
			}
			
			
			/*
			
			JsonObject obj = null;
			//obj = jsonObjectToBuilder(obj).add("status", 200).build();
			
			obj = jsonBuilder.add("resultado",resultados);
			//obj.put("resultado",resultados);
			 */
			//System.out.println(obj);	
			con.close();					
			return main;			
			
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
