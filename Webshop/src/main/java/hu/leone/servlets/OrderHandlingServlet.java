package hu.leone.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.persistence.jpa.jpql.parser.OrderSiblingsByClause;

import hu.leone.datatypes.Account;
import hu.leone.ejbs.AccountEJB;
import hu.leone.ejbs.CartEJB;
import hu.leone.ejbs.OrderEJB;
import hu.leone.utils.Utils;

/**
 * Servlet implementation class OrderHandlingServlet
 */
@WebServlet("/OrderHandlingServlet")
public class OrderHandlingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	AccountEJB accountEJB;

	@EJB
	OrderEJB orderEJB;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderHandlingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String buttonPressed = request.getParameter("buttonPressed");
		System.out.println("String buttonPressed: " + buttonPressed);
		CartEJB cart = (CartEJB) session.getAttribute("cart");

		if (!"DeleteOrder".equals(buttonPressed)) {

			// createNewAccountPopup.jsp-ből jönnek
			String name = request.getParameter("accountName");
			String email = request.getParameter("accountEmail");

			int accountId = accountEJB.insertAccount(new Account(name, email));

			// CartEJB cart = (CartEJB) session.getAttribute("cart");

			cart.setAccountId(accountId); // új account ID-ja

			cart.saveCart(); // kosár mentése: ORDER táblába bekerül a megrendelés az account IUD-hoz

			// kosár EJB törlése a session-ből
			session.setAttribute("cart", null);

			Utils.closePopup(response, "catalog.jsp");
		}

		// itt kezdődik adott rendelés törlése

		if ("DeleteOrder".equals(buttonPressed)) {
			
			this.deleteOrder(request, response);
		}

	}
	
	// adott megrendelés törlése
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("selectedOrderId"));
		System.out.println("Adott rendeles id torolve a megrendelesekbol: " + orderId);
		orderEJB.deleteOrder(orderId);
		RequestDispatcher rd = request.getRequestDispatcher("orders.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
