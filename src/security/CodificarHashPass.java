package security;
import java.math.BigInteger;
import java.security.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
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
	    Boolean estado = hash.equals(plane_text);
	    return estado;
	}
}





