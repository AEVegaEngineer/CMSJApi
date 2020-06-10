package modelos;


import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.json.JsonObject;

import org.json.simple.JSONObject;

import api.MysqlConnection;
import util.Consultas;

public class Afiliado {
private static int numAfiliado;
private static int numDocumento;
private String getAfiliadoByDocumento = "SELECT * FROM afiliado where NRODOC =  ";
private String params = "";

public String getParams() {
	return params;
}
public void setParams(String params) {
	this.params = params;
}
public static int getNumAfiliado() {
	return numAfiliado;
}
public static void setNumAfiliado(int numAfiliado) {
	Afiliado.numAfiliado = numAfiliado;
}
public static int getNumDocumento() {
	return numDocumento;
}
public void setNumDocumento(int numDocumento) {
	Afiliado.numDocumento = numDocumento;
}
	
	public	ArrayList<String> GetAfiliadoByDocumento() throws ClassNotFoundException {

		ArrayList<String> resultado = new ArrayList<String>();
		
		try (Connection con = getConnection())
	    {
			String sql = "Select * from afiliado where nrodoc = "+numDocumento;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int doc = rs.getInt("nrodoc");
				int afi = rs.getInt("nroafi");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				resultado.add(String.valueOf(doc));
				resultado.add(String.valueOf(afi));
				resultado.add(nombre);
				resultado.add(apellido);
			}
			con.close();
			return resultado;
	    } catch (SQLException e1) {
	    	System.out.println("Salio por el catch"); 
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	return resultado;
}
	public JSONObject getAfiliadoByDocumento() {
		if(params != "") {
			Consultas a = new Consultas();
			try {
				return a.consultar(getAfiliadoByDocumento+params);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Error consultando getAfiliadoByDocument");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("ERROR , SIN PARAMETROS");
			
			return null;
			
		}
		return null;
	}
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
