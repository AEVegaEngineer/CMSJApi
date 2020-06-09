package endpoints;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelos.Facturacion;
import security.GetTokenByUser;

/**
 * Servlet implementation class GetToken
 */
@WebServlet("/GetToken")
public class GetToken extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetToken() {
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
		GetTokenByUser token = new GetTokenByUser();
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		if (user == null || user == "" || pass == null || pass == "") {			
			String error = " {\"results\": \" No se recibio un parámetro de entrada.\"}";
			PrintWriter out = response.getWriter();
	        out.print(error);
		}
		else
		{			
			String nuevoToken = token.GenerateToken(user, pass);
	        PrintWriter out = response.getWriter();
	        out.print(nuevoToken);
		}
		
		
		//doGet(request, response);
	}

}
