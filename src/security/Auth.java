package security;

import java.security.NoSuchAlgorithmException;

public class Auth {
	public String VerifyToken(String JsonWebToken) {		
		VerifyLogin verificar = new VerifyLogin();
		//CodificarHashPass codificar = new CodificarHashPass();
		try {
			String verificacion /* viene codigo agrupacion*/ = verificar.VerificarUsuario(JsonWebToken);
			if(verificacion != "" || verificacion != null) {
				System.out.println("Se verificó correctamente");
				return verificacion;				
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
