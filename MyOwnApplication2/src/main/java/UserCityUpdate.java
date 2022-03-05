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
public class UserCityUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String user_city= request.getParameter("user_city");
			String user_city2= request.getParameter("user_city2");
			
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","surendra","surendra");
				String query1="update users set user_city=? where user_city=?";
				PreparedStatement ps=con.prepareStatement(query1);
				ps.setString(1, user_city2);
				ps.setString(2, user_city);
				ResultSet rs1=ps.executeQuery();
					if(rs1.next())
					{
						out.println("Your User_City Updated SuccessFully");
					}
					else
					{
						out.println("Your User_City not Updated");
					}
				con.close();
			}
			catch(Exception e)
			{
				out.println("Your User_City not Updated/Record Not Found");
				out.println("Something is Fishy...........");
			}
	}

}
