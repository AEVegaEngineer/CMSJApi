package endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import api.Mysql_jwt_users;
import modelos.Afiliado;
import modelos.Obrasociales;
import modelos.Prestadores;
import modelos.Facturacion;
import security.Auth;
import security.CodificarHashPass;
import security.JsonWebToken;
import security.VerifyLogin;
import util.LectorJson;
/**
 * Servlet implementation class test2
 */
@WebServlet("/GetInfoPrestador")

public class GetInfoPrestador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetInfoPrestador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		LectorJson lector = new LectorJson();
		JSONObject jsonObject = lector.leerJson(request);
		String token = (String) jsonObject.get("token");
		String documento = (String) jsonObject.get("documento");
		String obrasocial = (String) jsonObject.get("obrasocial");
		*//*
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080/CMSJApi/GetAfiliado");
		response.addHeader("Vary", "Origin");
		*/
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");
		
		
		Obrasociales consulta = new Obrasociales();
		
		String token = request.getParameter("token");
		String documento = request.getParameter("documento");
		String respuesta = null;
			
		
		Auth auth = new Auth();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String error = "";
        String agrupacion =auth.VerifyToken(token);
		System.out.println("imprimo agrupacion :"+agrupacion);
		JSONObject Prestador = null;

		
		if(Objects.equals(agrupacion, new String("99998")))  /*CODIGO UNICO DE HORUS */
			{		
		      //JSONObject facByAgr = null;
				if (documento == null || documento == "" || documento == "null") {
					
					String error_print = "{\"status\": 400,\"results\": \"Error: No se recibio documento como parámetro de entrada\"}";
						response.setStatus(400);
						out.print(error_print);
					}
					else {
						System.out.println("Va correcto");
						int doc =Integer.parseInt(documento);
						Prestadores pre = new Prestadores();
						pre.setNumDocumento(doc);
						System.out.println("Hace el integer correctamente");
				        Prestador = pre.getPrestadorByDocument();
				        response.setContentType("application/json");
				        response.setStatus(200);
				        out.print(Prestador);
					}
			      //  out.print(facByAgr);
			}
			else
			{
				error = " {\"status\": \"400\",\"mensaje\": \"Error: Token invalido\"}";
				response.setStatus(400);
		        out.print(error);
			}
		

		//CodificarHashPass codificar = new CodificarHashPass();
		
		//ArrayList<String> array = new ArrayList<String>();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
