package api;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import api.MysqlConnection;

/**
 * Servlet implementation class test2
 */
@WebServlet("/getAllTests")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String testById = "Select * from tablatest where testId = ";
	private static String params;
	
	public void setTestById(String testById) {
		this.testById = testById;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try { 			
			setParams("1");
			getTestById();		
	        
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
	
	ResultSet getTestById() throws ClassNotFoundException 
	{
		
		try (Connection con = getConnection())
	    {
			Statement st = con.createStatement();
			String sql = (testById + params);
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) { 
				int id = rs.getInt("testDato1"); 
				int dato1 = rs.getInt("testDato2");
				System.out.println("id: "+id+" - dato1:"+dato1);
			}
			con.close();
	    } catch (SQLException e1) {
	    	System.out.println("cosa2");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	  
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MysqlConnection.initializeDatabase();
	}

}
