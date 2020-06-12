package modelos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.mysql.cj.log.Log;

public class EndpointObrasocial {
	
	public String ValidaRedSeguroMedico(String obrasocial,String cantidad, String practica, String nroafi) throws IOException, ParseException {
		/*PARAMETROS DE CONEXION CUIT+CONTRASEÑA+AFILIADO+NOMENCLADOR+CODIGOS SEPARADOS POR COMA*/
		System.out.println(nroafi);
		URL url = new URL("http://ws1.rsmprestadores.com/validar.php?cuit=20-27650928-5&clave=1234&afil="+nroafi+"&nomen=2&codigos="+practica+"-"+cantidad+"");
		System.out.println(url);
		BufferedReader in = null;
		String inputLine;
		String inputText = "";
		String respuesta = "";
		String jsonString = "";

		try {
			  // Volcamos lo recibido al buffer
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			} catch(Throwable t){
				System.out.println("NO SE PUEDE RECIBIR LA WEBPAGE");
			}
		while ((inputLine = in.readLine()) != null) {
		     inputText = inputText + inputLine;
		}
		char a = ' ';
		char b = inputText.charAt(0);
		System.out.println(a);
		System.out.println(b);
		
		int diff = a-b;
		System.out.println(diff);
		if ( diff == 0) {
				jsonString = "{ \"autorizacion\" : \"codigo no válido\", \"estado\":\"No Autorizado\"}";
			
		} else {
			String [] arr = inputText.split(":");
			if (arr[0].equals("ERROR") || arr[0].equals("")) {	
				System.out.println("Erorr1 : " +arr[0]);
				System.out.println("Erorr2 : " +arr[1]);
				jsonString = "{ \"autorizacion\" : \""+arr[1]+"\", \"estado\":\"No Autorizado\"}";
			} else {
				String [] dividido = arr[0].split(",");				
				//SERIALIZAR JHONSON
				jsonString = "{ \"autorizacion\" : \""+dividido[0]+"\", \"practica\" : \""+dividido[1]+"\", \"estado\":\"Autorizado\"}";
			}

		}

		return jsonString;
	}

	
	
	
	
	
	
	
	
	
}
