package security;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

import api.Mysql_jwt_users;
import io.jsonwebtoken.Claims;
import util.TokenClaimsToString;

public class VerifyLogin {
	public String VerificarUsuario(String JsonWebToken) throws NoSuchAlgorithmException, ClassNotFoundException{
		
		JsonWebToken jwt = new JsonWebToken();
		Mysql_jwt_users usuarios = new Mysql_jwt_users();
		ArrayList<String> db = usuarios.GetUser();
		db.get(0);//USUARIO
		db.get(1);//PASSWORD
		db.get(2);//PERMISOS AGRUPACION
		
		
		Claims c = jwt.decodificar(JsonWebToken, "ColegioAPIJWT");
		//pasar a string el claims
		TokenClaimsToString conversor = new TokenClaimsToString(); 
		Map<String, Object> mapstring  = conversor.FormatearString(c);
		System.out.println(mapstring.get("name"));
		System.out.println(mapstring.get("pass"));
		
		
		
		return "00005";
	}
}
