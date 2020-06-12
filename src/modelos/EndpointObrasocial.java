package modelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class EndpointObrasocial {
	
	public String ValidaRedSeguroMedico(String obrasocial,String cantidad, String practica, String nroafi) throws IOException {
		/*PARAMETROS DE CONEXION CUIT+CONTRASEÑA+AFILIADO+NOMENCLADOR+CODIGOS SEPARADOS POR COMA*/
		URL url = new URL("http://ws1.rsmprestadores.com/validar.php?cuit=20-27650928-5&clave=1234&"+nroafi+"&nomen=2&codigos="+practica+"-"+cantidad+"");
		BufferedReader in = null;
		String inputLine;
		String inputText = null;
		try {
			  // Volcamos lo recibido al buffer
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			} catch(Throwable t){
				System.out.println("NO SE PUEDE RECIBIR LA WEBPAGE");
			}
		while ((inputLine = in.readLine()) != null) {
			
		     inputText = inputText + inputLine;
		}
		
		
		
		return inputText;
	}

}
