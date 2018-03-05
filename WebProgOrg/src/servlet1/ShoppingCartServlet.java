package servlet1;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.List;

import servlet1.dao.ShoppingCartItemDAO;
import servlet1.webshop.*;

/**
 * Klasa koja obavlja listanje stavki u korpi, a ako je pozvana iz forme, dodaje
 * jednu stavku u korpu, pa onda lista).
 */
public class ShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = -8628509500586512294L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")==null){
			resp.sendRedirect("login.html");
			return;
		}

		int userId = ((User)session.getAttribute("user")).getId();
		ShoppingCartItemDAO sciDAO = new ShoppingCartItemDAO();
		List<ShoppingCartItem> items = sciDAO.getAll(userId);
		ObjectMapper mapper = new ObjectMapper();
		String sItems = mapper.writeValueAsString(items);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(sItems);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null){
			response.sendRedirect("login.html");
			return;
		}

		int userId = ((User)session.getAttribute("user")).getId();
		ShoppingCartItemDAO sciDAO = new ShoppingCartItemDAO();
		
		if ( request.getParameter("itemId") != null ) {
			int productId = Integer.parseInt(request.getParameter("itemId"));
			int count = Integer.parseInt(request.getParameter("itemCount"));
			System.out.println("data: "+productId +":" + count+ ":" + userId);
			sciDAO.insert(productId, userId, count);
		}
		response.sendRedirect("ShoppingCart.html");
		
//		ShoppingCart sc = ((User) session.getAttribute("user")).getShoppingCart();

/*		response.setContentType("text/html");
		
		PrintWriter pout = response.getWriter();
		
		pout.println("<html>");
		pout.println("<head>");
		pout.println("</head>");
		pout.println("<body>");

		
		pout.println("Proizvodi u korpi:");
		pout.println("<table><tr bgcolor=\"lightgrey\"><th>Naziv</th><th>Jedinicna cena</th><th>Komada</th><th>Ukupna cena</th><th>&nbsp;</th></tr>");
		double total = 0;
		List<ShoppingCartItem> items = sciDAO.getAll(userId);
		for ( ShoppingCartItem i : items ) {
			pout.println("<tr>");
			pout.println("<td>" + i.getProduct().getName() + "</td>");
			pout.println("<td>" + i.getProduct().getPrice() + "</td>");
			pout.println("<td>" + i.getCount() + "</td>");
			double price = i.getProduct().getPrice() * i.getCount();
			pout.println("<td>" + price + "</td>");
			pout.println("<td><form action=\"DeleteServlet\" method=\"post\">"
					+ "<input type=\"hidden\" name=\"id\" value=\"" + i.getId() + "\">"
							+ "<input type=\"submit\" value=\"delete\"></form></td>");
			pout.println("</tr>");
			total += price;
		}
		pout.println("</table>");

		pout.println("<p>");
		pout.println("Ukupno: " + total + " dinara.");
		pout.println("</p>");

		pout.println("<p>");
		pout.println("<a href=\"WebShopServlet\">Povratak</a>");
		pout.println("</p>");

		pout.println("</body>");
		pout.println("</html>");
*/	}
}
