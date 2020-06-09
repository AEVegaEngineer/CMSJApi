package security;

import java.security.NoSuchAlgorithmException;

import api.Mysql_jwt_users;

public class GetTokenByUser {
	public String GenerateToken(String user, String pass) {		
		
		JsonWebToken token = new JsonWebToken();
		Mysql_jwt_users usuarios = new Mysql_jwt_users();
		String tokenn = null;
		try {
			tokenn = token.codificar(usuarios.GetUser(user,pass));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tokenn;
		
	}
}
