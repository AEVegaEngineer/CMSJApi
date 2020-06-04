package endpoints;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			ArrayList<String> datos = new ArrayList<String>();
			String documento = request.getParameter("documento");
	        Afiliado afi = new Afiliado();
	        afi.setNumDocumento(Integer.parseInt(documento));
	        datos = afi.GetAfiliadoByDocumento();
	        for (int i = 0; i < datos.size(); i++) {
				System.out.println(datos.get(i));
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
