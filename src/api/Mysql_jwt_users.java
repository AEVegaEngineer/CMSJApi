package api;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Mysql_jwt_users {
	public ArrayList<String> getTestById() throws ClassNotFoundException 
	{
		
		try (Connection con = getConnection())
	    {
			/*ARRAY LIST SINTAXIS
			 * USUARIO:USER(DB)
			 * CONTRASEÑA:PASS(DB)
			 * PERMISOS:PERMISOS_AGRUPACION(DB)
			 * JSONVALIDATIONPREXISTENTE:JWT(DB) NOT YET
			 * */
			java.sql.ResultSetMetaData md;
			ArrayList<String> arr = new ArrayList<String>();
			Statement st = con.createStatement();
			String sql = ("SELECT user,pass,permisos_agrupacion FROM api_users_os");
			ResultSet rs = st.executeQuery(sql);
			md = rs.getMetaData();
			int columnas = md.getColumnCount();
				if (columnas != 0)
				{
					while (rs.next()) {
						arr.add(rs.getString("user"));
						arr.add(rs.getString("pass"));
						arr.add(rs.getString("permisos_agrupacion"));
					}
				con.close();
				return arr;	
				}
			return null;
	    } catch (SQLException e1) {
	    	System.out.println("Excepción en la conexión!");
			e1.printStackTrace();
		}
		return null;
	  
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}
}
