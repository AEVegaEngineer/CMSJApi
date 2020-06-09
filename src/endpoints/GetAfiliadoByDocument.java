package endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.Mysql_jwt_users;
import modelos.Afiliado;
import security.JsonWebToken;
import security.VerifyLogin;
/**
 * Servlet implementation class test2
 */
@WebServlet("/GetAfiliadoByDocument")

public class GetAfiliadoByDocument extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAfiliadoByDocument() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String JsonWebToken = request.getParameter("token");
		VerifyLogin boolverifica = new VerifyLogin();
		
		try {
			if(boolverifica.VerificarUsuario(JsonWebToken) != "") {
				try { 
					//VERIFY VALIDATED 
					//TEST GENERANDO TOKEN VALIDADO
					String documento = request.getParameter("documento");
					JsonObject AfiliadoByDocument = null;
					//ArrayList<String> array = new ArrayList<String>();
					
					if (documento == null || documento == "") {
						
						String error = " {\"results\": \" No se recibio un parámetro de entrada.\"}";
						 PrintWriter out = response.getWriter();
					        out.print(error);

					}
					else {
						Afiliado a = new Afiliado();
				        a.setParams(documento);
				        AfiliadoByDocument = a.getAfiliadoByDocumento();
				        PrintWriter out = response.getWriter();
				        out.print(AfiliadoByDocument);
					}
			    }		
			    catch (Exception e) { 
			    	System.out.println("Retorna el catch");
			        e.printStackTrace(); 
			    } 				
			}
			else {
				//LOGIN FAILED
				String error = " {\"results\": \" Error al realizar login.\"}";
				 PrintWriter out = response.getWriter();
				    out.print(error);
				
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
