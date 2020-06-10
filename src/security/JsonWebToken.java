package security;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import java.security.NoSuchAlgorithmException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JsonWebToken {
	
	CodificarHashPass codifica = new CodificarHashPass();
	public  String codificar(ArrayList<String> userdb) throws NoSuchAlgorithmException {
		String webtoken = null;
		String signaturecreation = codifica.hashPassword("ColegioAPIJWT");
		
		webtoken = Jwts.builder()
				.setSubject("1234567890")
				.setId("d9531212-511b-4443-8ca2-8a0859d8f7e1")
				.setIssuedAt(Date.from(Instant.ofEpochSecond(1591366380)))
				.claim("name", userdb.get(0)) //NOMBRE DE USUARIO
				.claim("pass", userdb.get(1)) //CONTRASEÑA DE USUARIO
				.signWith(SignatureAlgorithm.HS256, signaturecreation)
				.compact();
		return webtoken;
	}
	

public Claims decodificar(String webtoken, String signaturemd5) throws NoSuchAlgorithmException  {
	String signaturevalidation = codifica.hashPassword(signaturemd5);
	System.out.println("Decodificando");
	//ARROJA ERROR AL VALIDAR
	try {
		Claims claims = Jwts.parser()
		  	.setSigningKey(DatatypeConverter.parseString(signaturevalidation))
            .parseClaimsJws(webtoken).getBody();
		 System.out.println(claims);
		 return claims;
		
		}
		catch (Exception e)
		{
		System.out.println("Error al decodificar, no coincide la firma");
		 
		return null;
		}
	   
	}

}






