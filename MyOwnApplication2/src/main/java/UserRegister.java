import java.io.IOException;



import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			String user_name= request.getParameter("user_name");
			String user_password=request.getParameter("user_password");
			String user_email=request.getParameter("user_email");
			String user_gender=request.getParameter("gender");
			String user_dob=request.getParameter("user_dob");
			long user_mobile=Long.parseLong(request.getParameter("user_mobile"));
			String user_address=request.getParameter("user_address");
			String user_city=request.getParameter("user_city");
			String user_state=request.getParameter("user_state");
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","surendra","surendra");
				String query="insert into users values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, user_name);
				ps.setString(2,user_password);
				ps.setString(3,user_email);
				ps.setString(4, user_gender);
				ps.setString(5,user_dob);
				ps.setLong(6, user_mobile);
				ps.setString(7,user_address);
				ps.setString(8,user_city);
				ps.setString(9,user_state);
				ps.executeUpdate();
				out.println("Registerd Successfully");
				con.close();
			}
			catch(Exception e)
			{
				out.println("Something is Fishy...........");
			}
	}

}
