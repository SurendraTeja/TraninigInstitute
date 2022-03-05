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
public class UserAddressUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String user_address= request.getParameter("user_address");
			String user_address2= request.getParameter("user_address2");
			
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","surendra","surendra");
				String query1="update users set user_address=? where user_address=?";
				PreparedStatement ps=con.prepareStatement(query1);
				ps.setString(1, user_address2);
				ps.setString(2, user_address);
				ResultSet rs1=ps.executeQuery();
					if(rs1.next())
					{
						out.println("Your User_Address Updated SuccessFully");
					}
					else
					{
						out.println("Your User_Address not Updated");
					}
				con.close();
			}
			catch(Exception e)
			{
				out.println("Your User_Address not Updated/Record Not Found");
				out.println("Something is Fishy...........");
			}
	}

}
