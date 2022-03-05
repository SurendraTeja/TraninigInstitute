import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserMobileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			Long user_mobile=Long.parseLong(request.getParameter("user_mobile"));
			Long user_mobile2=Long.parseLong(request.getParameter("user_mobile2"));
			
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","surendra","surendra");
				String query1="update users set user_mobile=? where user_mobile=?";
				PreparedStatement ps=con.prepareStatement(query1);
				ps.setLong(1, user_mobile2);
				ps.setLong(2, user_mobile);
				ResultSet rs1=ps.executeQuery();
					if(rs1.next())
					{
						out.println("Your User_Mobile Updated SuccessFully");
					}
					else
					{
						out.println("Your User_Mobile not Updated");
					}
				con.close();
			}
			catch(Exception e)
			{
				out.println("Your User_Mobile not Updated/Record Not Found");
				out.println("Something is Fishy...........");
			}
	}

}
