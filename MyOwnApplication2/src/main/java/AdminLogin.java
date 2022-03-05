

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String user_name=request.getParameter("user_name");
		String user_password=request.getParameter("user_password");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			String query="select * from admin where user_name=? and user_password=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, user_name);
			ps.setString(2,user_password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				
			{
				out.println("Your Login SuccessFully");
				response.sendRedirect("Mainpage3.html");
				
			}
			else
			{
				out.println("Invalid username/password");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println("Something is Fishy...........");
		}
		
	}
}


