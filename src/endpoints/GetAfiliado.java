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

import api.Mysql_jwt_users;
import modelos.Afiliado;
import modelos.EndpointObrasocial;
import modelos.Facturacion;
import security.Auth;
import security.CodificarHashPass;
import security.JsonWebToken;
import security.VerifyLogin;
import util.LectorJson;
/**
 * Servlet implementation class test2
 */
@WebServlet("/GetAfiliado")

public class GetAfiliado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAfiliado() {
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
		*/
		response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080/CMSJApi/GetAfiliado");
		response.addHeader("Vary", "Origin");
		
		//EndpointObrasocial consulta = new EndPointObraSocial();
		
		String token = request.getParameter("token");
		String obrasocial = request.getParameter("obrasocial");
		String fecha_realizacion = request.getParameter("fecha_realizacion");
		String cantidad = request.getParameter("cantidad");
		String documento = request.getParameter("documento");
		String practica = request.getParameter("practica");
		String afiliado = request.getParameter("nroafi");
		consulta.ValidaRedSeguroMedico(obrasocial, cantidad, practica, afiliado);
		
		
		Auth auth = new Auth();
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String error = "";
        String agrupacion =auth.VerifyToken(token);
		System.out.println("imprimo agrupacion :"+agrupacion);
		JSONObject AfiliadoByDocument = null;

		
		if(Objects.equals(agrupacion, new String("99999")))  /*CODIGO UNICO DE HORUS */
			{		
		      //JSONObject facByAgr = null;
				if (documento == null || documento == "" || documento == "null") {
					
					String error_print = "{\"status\": 400,\"results\": \"Error: No se recibio documento como parámetro de entrada\"}";
						response.setStatus(200);
						out.print(error_print);
					}
					else {
						Afiliado afi = new Afiliado();
				        afi.setParams(documento);
				        AfiliadoByDocument = afi.getAfiliadoByDocumento();
				        response.setContentType("application/json");
				        response.setCharacterEncoding("UTF-8");
				        response.setStatus(200);
				        out.print(AfiliadoByDocument);
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
