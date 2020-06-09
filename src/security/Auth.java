package security;

import java.security.NoSuchAlgorithmException;

public class Auth {
	public Boolean VerifyToken(String JsonWebToken) {		
		VerifyLogin verificar = new VerifyLogin();
		//CodificarHashPass codificar = new CodificarHashPass();
		try {
			String verificacion = verificar.VerificarUsuario(JsonWebToken);
			if(verificacion != "" || verificacion != null) {
				return true;				
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}	
}
