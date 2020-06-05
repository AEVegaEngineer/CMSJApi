package security;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.Instant;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;


public class JsonWebToken {
	public void codificar() {
		
		try {
			String s = Jwts.builder()
					.setSubject("1234567890")
					.setId("d9531212-511b-4443-8ca2-8a0859d8f7e1")
					.setIssuedAt(Date.from(Instant.ofEpochSecond(1591366380)))
					.setExpiration(Date.from(Instant.ofEpochSecond(1591369980)))
					.claim("name", "John Doe") //NOMBRE DE USUARIO
					.claim("admin", true)		//PRIVILEGIO
					.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
					.compact();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public void decodificar() throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, IllegalArgumentException, UnsupportedEncodingException {
		
		try {
		    Jwts.parser()
			.setSigningKey("secret".getBytes("UTF-8"))
			.parseClaimsJws("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImp0aSI6ImQ5NTMxMjEyLTUxMWItNDQ0My04Y2EyLThhMDg1OWQ4ZjdlMSIsImlhdCI6MTU5MTM2NjM4MCwiZXhwIjoxNTkxMzY5OTgwfQ.lKYIGuptDRvtM3MRzXjPsnJF1qQwWYO72HTolTXQhz8");
		    //OK, we can trust this JWT
		} catch (SignatureException e) {
	
		    //don't trust the JWT!
		}
	}

}
