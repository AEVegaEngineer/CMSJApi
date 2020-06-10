package endpoints;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import io.github.mattvass.resultsetmapper.JsonResultSet;
import modelos.Afiliado;
import modelos.Facturacion;
import security.Auth;
import security.VerifyLogin;

/**
 * Servlet implementation class GetFacturacionByAgrupacion
 */

@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON) 
@WebServlet("/GetFacturacionByAgrupacion")
public class GetFacturacionByAgrupacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFacturacionByAgrupacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		StringBuffer jb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;
		try {
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		    	jb.append(line);
		} catch (Exception e) { /*report an error*/ }
		try {
			JSONParser parser = new JSONParser();
			jsonObject = (JSONObject)parser.parse(jb.toString());// new JsonResultSet().toJson(rs);
			System.out.println(jb.toString());
		} catch (Exception e) {
			// crash and burn
			throw new IOException("Error parsing JSON request string");
		}
		
		String agrupacion = (String) jsonObject.get("agrupacion");
		String token = (String) jsonObject.get("token");
		//String agrupacion = request.getParameter("agrupacion");
		//String token = request.getParameter("token");
		Auth auth = new Auth();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String error = "";
		if (agrupacion == null || agrupacion == "") 
		{			
			error = " {\"results\": \" No se recibio un parámetro de entrada.\"}";			
	        out.print(error);
		}
		else
		{
			if(auth.VerifyToken(token)) 
			{						
				JSONObject facByAgr = null;
				Facturacion a = new Facturacion();
				facByAgr = a.getFacturacionByAgrupacion(agrupacion);		  
				response.setStatus(301);
		        out.print(facByAgr);
			}
			else
			{
				error = " {\"status\": \"401\",\"mensaje\": \"Error: credenciales incorrectas\"}";				
		        out.print(error);
			}	
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
