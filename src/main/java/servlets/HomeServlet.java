package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public HomeServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().print("<h1> My id is 25490 and my names are Mpeta Elvis</h1>");
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String enteredId = request.getParameter("id");
		if(!enteredId.matches("\\d+")) {
			response.getWriter().print("<h1> Id must be number</h1>");
			return;
		}		
	Integer id = Integer.parseInt(enteredId);
    try {
    	String db_url= "jdbc:postgresql://localhost:5433/best_pro_db";
        String username = "postgres";
        String password = "12345";
		Class.forName("org.postgresql.Driver");
		Connection con = DriverManager.getConnection(db_url,username,password);
		PreparedStatement pst = con.prepareStatement("select *from student where id = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery(); 
		
		if(rs.next()) {
		   String name = rs.getString("name");
		   response.getWriter().print("<h1>Your name is "+name+" and id is "+id+"</h1>");
		   con.close();
		   return;
		}
		
		
	} catch (SQLException e) {
		// TODO: handle exception
	}catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
    response.getWriter().print("<h2>id doesn't exit</h2> "); 
	}

}
