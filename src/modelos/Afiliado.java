package modelos;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import api.MysqlConnection;

public class Afiliado {
private static int numAfiliado;
private static int numDocumento;


public static int getNumAfiliado() {
	return numAfiliado;
}
public static void setNumAfiliado(int numAfiliado) {
	Afiliado.numAfiliado = numAfiliado;
}
public static int getNumDocumento() {
	return numDocumento;
}
public static void setNumDocumento(int numDocumento) {
	Afiliado.numDocumento = numDocumento;
}
	
	ArrayList<String> GetAfiliadoByDocumento() throws ClassNotFoundException {

		ArrayList<String> resultado = new ArrayList<String>();
		
		try (Connection con = getConnection())
	    {
			String sql = "Select * from tablatest where NROAFI = "+numDocumento;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("testDato1");
				int dato1 = rs.getInt("testDato2");
				resultado.add(String.valueOf(id));
				resultado.add(String.valueOf(dato1));
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
	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
