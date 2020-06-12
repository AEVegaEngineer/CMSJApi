package endpoints;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import security.GetTokenByUser;
import util.LectorJson;

/**
 * Servlet implementation class GetToken
 */
@WebServlet("/Validar")
public class Validar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String error = " {\"results\": \"Método Erróneo (llamar por POST).\"}";
		PrintWriter out = response.getWriter();
		
        out.print(error);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		String user = null;
		String pass = null;
		
		
		
		try {
			JSONObject jsonObject = new LectorJson().leerJson(request);
			user = (String) jsonObject.get("user");
			pass = (String) jsonObject.get("pass");
				
		
		
		GetTokenByUser token = new GetTokenByUser();
		String nuevoToken = null;
		//String user = request.getParameter("user");
		//String pass = request.getParameter("pass");
		
		
		if (user == null || user == "" || pass == null || pass == "") {			
			response.setStatus(400);
			String error = " {\"status\":400,\"resultado\": \"No se recibio un parámetro de entrada\"}";
			out.print(error);
		}
		else
		{		
			try {
				nuevoToken = token.GenerateToken(user, pass);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	        
	        out.print(nuevoToken);
		}
	
	} catch (Exception e) {
		response.setStatus(400);
		String error = " {\"status\":400,\"resultado\": \"Error en el formato de archivo JSON\"}";
		out.print(error);
	}
	}
}
