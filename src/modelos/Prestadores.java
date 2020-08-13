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
private static int numDocumento;
private String getPrestador = "SELECT nro_documento,matricula,apellido,fecha_nac,esp_principal,tipo_socio,categoria,email,part_telefono FROM import_tps_socio WHERE nro_documento =  ";

public static int getNumDocumento() {
	return numDocumento;
}
public void setNumDocumento(int numDocumento) {
	Prestadores.numDocumento = numDocumento;
}
	
	public	ArrayList<String> GetPrestadorByDocument() throws ClassNotFoundException {

		ArrayList<String> resultado = new ArrayList<String>();
		
		try (Connection con = getConnection())
	    {
			String sql = "Select nro_documento,matricula,apellido,fecha_nac,esp_principal,tipo_socio,categoria,email,part_telefono from import_tps_socio where nro_documento = "+numDocumento;
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
	public JSONObject getPrestadorByDocument() {
			Consultas a = new Consultas();
			try {
				return a.consultar(getPrestador+getNumDocumento());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Error consultando getAfiliadoByDocument");
				e.printStackTrace();
			}
		
		return null;
	}
	
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
