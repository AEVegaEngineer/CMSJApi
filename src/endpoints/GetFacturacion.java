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
import util.LectorJson;

/**
 * Servlet implementation class GetFacturacionByAgrupacion
 */

@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON) 
@WebServlet("/GetFacturacion")
public class GetFacturacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFacturacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		LectorJson lector = new LectorJson();
		JSONObject jsonObject = lector.leerJson(request);
		
		
		String token = (String) jsonObject.get("token");
		
		
		Auth auth = new Auth();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String error = "";
		
			String agrupacion = auth.VerifyToken(token);
			//System.out.println("imprimo agrupacion :"+agrupacion);
			
			if(agrupacion == "null" || agrupacion == null || agrupacion == "") 
			{	
				response.setStatus(401);
				error = " {\"status\": 401,\"mensaje\": \"Error: Token invalido\"}";
		        out.print(error);
			}
			else
			{
				JSONObject facByAgr = null;
			Facturacion a = new Facturacion();
			facByAgr = a.getFacturacionByAgrupacion(agrupacion);
			response.setStatus(200);
	        out.print(facByAgr);
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
