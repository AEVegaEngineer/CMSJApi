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
		CodificarHashPass codifica = new CodificarHashPass();
		
		
		
		Claims c = jwt.decodificar(JsonWebToken, "ColegioAPIJWT");
		//pasar a string el claims
		TokenClaimsToString conversor = new TokenClaimsToString(); 
		Map<String, Object> mapstring  = conversor.FormatearString(c);
		System.out.println(mapstring.get("name"));
		System.out.println(mapstring.get("pass"));
		String passcodificado = codifica.codificarmd5((String) mapstring.get("pass"));
		
		
		ArrayList<String> db = usuarios.GetUser(mapstring.get("name").toString(),passcodificado);
		
		
		try {
			if (db.get(0) != "" || db.get(1) != "")
			{
				System.out.println("se logueo correctamente");
			}
			else 
			{
				System.out.println("No se logueo correctamente");
			}	
		}
		catch (Exception e){
			System.out.println("No se logueo correctamente \n "+e);
		}
		
		
		
		return "00005";
	}
}
