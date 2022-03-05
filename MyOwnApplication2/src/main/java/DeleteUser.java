

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
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
      	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String user_name=request.getParameter("user_name");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","surendra","surendra");
			String query="delete users where user_name=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, user_name);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				
			{
				
				out.println("You Deleted An User Succefully");
				
				
			}
			else
			{
				out.println("Your Account Not Deleted");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println("Something is Fishy...........");
		}
		
	}
}


