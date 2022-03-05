

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
public class ShowUserDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
      	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String user_name=request.getParameter("user_name");
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","surendra","surendra");
			String query="select * from users where user_name=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, user_name);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rss=rs.getMetaData();
			int n=rss.getColumnCount();
			out.println("<table border=1>");
			out.println("<tr>");
			for(int i=1;i<=n;i++)
			{	
				out.println("<td>"+rss.getColumnName(i)+"</td>");  
			}
		    out.println("</tr>");
			while(rs.next())
			{
				out.println("<tr>");
				for(int i=1;i<=n;i++)
				{
					out.println("<td>"+rs.getString(i)+"</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");	
			con.close();
		}
		catch(Exception e)
		{
			out.println("Invalid details");
			out.println("Something is Fishy...........");
		}
		
	}
}


