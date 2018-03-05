package servlet1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet1.dao.UserDAO;
import servlet1.webshop.User;

/**
 * Servlet implementation class ListUsersServlet
 */
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect("login.html");
			return;
		}
		UserDAO userDAO = new UserDAO();
		List<User> users = userDAO.getAllUsers();
		response.setContentType("text/html");
		
		PrintWriter pout = response.getWriter();
		
		pout.println("<html>");
		pout.println("<head>");
		pout.println("</head>");
		pout.println("<body>");
		pout.println("Korisnici:");
		
		pout.println("<table border=\"1\"><tr bgcolor=\"lightgrey\"><th>Korisnicko ime</th><th>Lozinka</th><th>&nbsp;</th></tr>");
		for ( User user : users ) {
			pout.println("<tr>");
			pout.println("<td>" + user.getUsername() + "</td>");
			pout.println("<td>" + user.getPassword() + "</td>");
			pout.println("<td><a href=\"DeleteUserServlet?id=" +user.getId()+ "\">del</a></td>");
			pout.println("</tr>");
		}
		pout.println("</table>");

		pout.println("</body>");
		pout.println("</html>");	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
