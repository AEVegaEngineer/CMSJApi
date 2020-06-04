package endpoints;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import modelos.Test;

/**
 * Servlet implementation class test2
 */
@WebServlet("/getAllTests")
public class GetTestAll extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	@Override
    protected void doGet(
      HttpServletRequest request, 
      HttpServletResponse response) throws IOException {    	
        Test test = new Test();
        String TestAll = "";        
        try {
			TestAll = test.getTestAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se encontró la clase Test");
			e.printStackTrace();
		}
        PrintWriter out = response.getWriter();
        out.print(TestAll);
        /*
         * enviar un archivo
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(TestAll);
        out.flush(); 
        */ 
    } 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public Response retornaOK() {     
        return Response.ok("Se ha retornado el recurso correctamente",MediaType.APPLICATION_JSON).build();
        
    }
}