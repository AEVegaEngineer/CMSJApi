package endpoints;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

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
        ResultSet rs = null;
		try {
			rs = test.getTestAll();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // = ""; 
        
        try {
			while (rs.next()) {
				System.out.println(rs.getInt("testId"));
				/*
				this.testId = rs.getInt("testId");
				this.testDato1 = rs.getInt("testDato1");
				this.testDato2 = rs.getInt("testDato2");
				this.testDato3 = rs.getInt("testDato3");
				final Gson gson = new Gson();
				System.out.println(this.getTestDato1() + " " + this.getTestDato2() + " " + this.getTestDato3());
				testJsonString = gson.toJson(this);
				resultado += "{\"testId\":"+this.getTestId();
				resultado += ",\"testDato1\":"+this.getTestDato1();
				resultado += ",\"testDato2\":"+this.getTestDato2();
				resultado += ",\"testDato3\":"+this.getTestDato3()+"}";
				if(iterador<contador)
					resultado += ",";
				iterador++;	
				*/			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error llamando a ResultSet");
			e.printStackTrace();
		}
        /*
        try {
			TestAll = test.getTestAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("No se encontró la clase Test");
			e.printStackTrace();
		}
        PrintWriter out = response.getWriter();
        out.print(TestAll);
        */
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