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

/**
 * Servlet implementation class GetFacturacionByAgrupacion
 */
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String agrupacion = request.getParameter("agrupacion");
		//ArrayList<String> array = new ArrayList<String>();		
		if (agrupacion == null || agrupacion == "") {			
			String error = " {\"results\": \" No se recibio un parámetro de entrada.\"}";
			PrintWriter out = response.getWriter();
	        out.print(error);
		}
		else
		{			
			JsonObject facByAgr = null;
			Facturacion a = new Facturacion();
			facByAgr = a.getFacturacionByAgrupacion(agrupacion);
	        PrintWriter out = response.getWriter();
	        out.print(facByAgr);
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
