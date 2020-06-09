package security;

import java.util.ArrayList;

import api.Mysql_jwt_users;

public class GetTokenByUser {
	public String GenerateToken(String user, String pass) throws ClassNotFoundException {		
		
		JsonWebToken token = new JsonWebToken();
		String tokenRetornado = null;
		Mysql_jwt_users usuarios = new Mysql_jwt_users();
		ArrayList<String> consultas	= usuarios.GetUser(user);
		try {
			
			CodificarHashPass cod = new CodificarHashPass();
			String passdb = usuarios.GetUser(user).get(1);//CONTRASEÑA OBTENIDA POR LA DB
			String passget = cod.hashPassword(pass);//CONTRASEÑA ENVIADA POR USUARIO HASHED
			
			
			System.out.println(passdb);
			System.out.println(passget);
			//Boolean auth = cod.verifyPassword(hashPass, pass);
			if(passdb.contentEquals(passget)) {
				System.out.println("Entre al if");
				tokenRetornado = token.codificar(consultas);
			}
			else {
				System.out.println("no se validó correctamente");
				
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("Error generando token");
			e1.printStackTrace();
		}			
		return tokenRetornado;		
	}
}
