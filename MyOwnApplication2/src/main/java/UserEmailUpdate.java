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
public class UserEmailUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String user_email= request.getParameter("user_email");
			String user_email2= request.getParameter("user_email2");
			
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","surendra","surendra");
				String query1="update users set user_email=? where user_email=?";
				PreparedStatement ps=con.prepareStatement(query1);
				ps.setString(1, user_email2);
				ps.setString(2, user_email);
				ResultSet rs1=ps.executeQuery();
					if(rs1.next())
					{
						out.println("Your User_Email Updated SuccessFully");
					}
					else
					{
						out.println("Your User_Email not Updated");
					}
				con.close();
			}
			catch(Exception e)
			{
				out.println("Your User_Email not Updated/Record Not Found");
				out.println("Something is Fishy...........");
			}
	}

}
