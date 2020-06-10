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
		if (c == null) {
			
			return null;
		} else {
			TokenClaimsToString conversor = new TokenClaimsToString(); 
			Map<String, Object> mapstring  = conversor.FormatearString(c);
			
			
			
			//OBTENEMOS USUARIO Y CONTRASEÑA DE LA BASE DE DATOS FILTRANDO POR USUARIO
			ArrayList<String> db = usuarios.GetUser(mapstring.get("name").toString());//PASS HASHED
			//COMPARAMOS EL PASS DE LA BD CON EL PASS DE TOKEN
			Boolean validacion = codifica.verifyPassword(mapstring.get("pass").toString(), db.get(1));
			
			try {
				if (validacion == true)
				{
					//System.out.println("se logueo correctamente");
					return db.get(2);
				}
				else 
				{
					//System.out.println("No se logueo correctamente");
					return null;
				}	
			}
			catch (Exception e){
				//System.out.println("No se logueo correctamente, sale por catch\n ");
				return null;
			}
		}
		//pasar a string el claims
		//OBTENEMOS USUARIO Y CONTRASEÑA DE TOKEN
		
	}
}
