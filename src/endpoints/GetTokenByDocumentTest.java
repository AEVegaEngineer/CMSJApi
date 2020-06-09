package endpoints;

import java.security.NoSuchAlgorithmException;

import api.Mysql_jwt_users;
import security.CodificarHashPass;
import security.JsonWebToken;
import security.VerifyLogin;

public class GetTokenByDocumentTest {
	public void TakeToken() throws NoSuchAlgorithmException {
		VerifyLogin boolverifica = new VerifyLogin();
		//String JsonWebToken = request.getParameter("token");
		JsonWebToken token = new JsonWebToken();
		Mysql_jwt_users usuarios = new Mysql_jwt_users();
		String tokenn = null;
		try {
			tokenn = token.codificar(usuarios.GetUser("test"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(tokenn);
		
	}
}
