package security;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import org.mindrot.jbcrypt.BCrypt;
public class CodificarHashPass {
	
	
	public String hashPassword(String texto) throws NoSuchAlgorithmException
	{		
		try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 byte[] messageDigest = md.digest(texto.getBytes());
			 BigInteger number = new BigInteger(1, messageDigest);
			 String hashtext = number.toString(16);

			 while (hashtext.length() < 32) {
			 hashtext = "0" + hashtext;
			 }
			 return hashtext;
			 }
			 catch (NoSuchAlgorithmException e) {
			 throw new RuntimeException(e);
			 }

	}
	
	
	public Boolean verifyPassword(String hash, String plane_text) 
			  throws NoSuchAlgorithmException {
			   
			    //String password = "ILoveJava";
			         
			    MessageDigest md = MessageDigest.getInstance("MD5");
			    md.update(plane_text.getBytes());
			    byte[] digest = md.digest();
			    String myHash = DatatypeConverter
			      .printHexBinary(digest).toUpperCase();
			         
			    Boolean estado = myHash.equals(hash);
			    return estado;
			}
	
	
	
	
	
}





