package security;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;


public class JsonWebToken {
	
	CodificarHashPass codifica = new CodificarHashPass();
	public  String codificar(ArrayList<String> userdb) throws NoSuchAlgorithmException {
		String webtoken = null;
		String md5pass = codifica.codificarmd5(userdb.get(1));
		String signaturecreation = codifica.codificarmd5("ColegioAPIJWT");
		
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
	String signaturevalidation = codifica.codificarmd5(signaturemd5);
	  Claims claims = Jwts.parser()
			  	.setSigningKey(DatatypeConverter.parseString(signaturevalidation))
			  	//.parsePlaintextJwt(webtoken).getBody();
	            .parseClaimsJws(webtoken).getBody();
	 
	    return claims;
	}

}






