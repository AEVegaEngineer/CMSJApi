package endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.util.ArrayList;
import api.MysqlQuery;
import modelos.Afiliado;

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
		try { 
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}
