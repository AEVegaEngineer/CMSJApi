package security;

import api.Mysql_jwt_users;

public class GetTokenByUser {
	public String GenerateToken(String user, String pass) {		
		
		JsonWebToken token = new JsonWebToken();
		String tokenRetornado = null;
		Mysql_jwt_users usuarios = new Mysql_jwt_users();
		try {
			String hashPass = usuarios.GetUser(user).get(1);
			CodificarHashPass asd = new CodificarHashPass();
			Boolean auth = asd.checkPassword(pass, hashPass);
			if(auth == true) {
						
				tokenRetornado = token.codificar(usuarios.GetUser(user));
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Error generando token");
			e1.printStackTrace();
		}			
		return tokenRetornado;		
	}
}
