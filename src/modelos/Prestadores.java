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

public class Prestadores {
private static int numAfiliado;
private static int numDocumento;
private String getPrestador = "SELECT * FROM afiliado where NRODOC =  ";
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
	Prestadores.numAfiliado = numAfiliado;
}
public static int getNumDocumento() {
	return numDocumento;
}
public void setNumDocumento(int numDocumento) {
	Prestadores.numDocumento = numDocumento;
}
	
	public	ArrayList<String> GetAfiliadoByDocumento() throws ClassNotFoundException {

		ArrayList<String> resultado = new ArrayList<String>();
		
		try (Connection con = getConnection())
	    {
			String sql = "Select * from socio where nro_documento = "+numDocumento;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int doc = rs.getInt("nro_documento");
				int matricula = rs.getInt("matricula");
				String nombre = rs.getString("apellido");
				String fecha_nac = rs.getString("fecha_nac");
				String especialidad = rs.getString("esp_principal");
				String estado = rs.getString("tipo_socio");
				String motivo = rs.getString("categoria");
				String email = rs.getString("email");
				int telefono = rs.getInt("part_telefono");
				
				resultado.add(String.valueOf(doc));
				resultado.add(String.valueOf(matricula));
				resultado.add(nombre);
				resultado.add(fecha_nac);
				resultado.add(especialidad);
				resultado.add(estado);
				resultado.add(motivo);
				resultado.add(email);
				resultado.add(String.valueOf(telefono));
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
				return a.consultar(getPrestador+params);
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
