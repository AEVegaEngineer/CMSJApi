package security;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import api.Mysql_jwt_users;
import io.jsonwebtoken.Claims;

public class VerifyLogin {
	public boolean VerificarUsuario(String JsonWebToken) throws NoSuchAlgorithmException, ClassNotFoundException{
		
		JsonWebToken jwt = new JsonWebToken();
		Mysql_jwt_users usuarios = new Mysql_jwt_users();
		ArrayList<String> db = usuarios.GetUser();
		db.get(0);//USUARIO
		db.get(1);//PASSWORD
		db.get(2);//PERMISOS AGRUPACION
		
		
		Claims clm = jwt.decodificar(JsonWebToken, "ColegioAPIJWT");
		//clm.get("name", "String");
		
		
		
		
		String codificadoJWT = jwt.codificar(db);
		
		//System.out.println("Hash de codificacion: "+codificacion);

		
		
		return true;
	}
}
