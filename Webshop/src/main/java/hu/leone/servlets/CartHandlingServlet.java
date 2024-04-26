package hu.leone.servlets;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hu.leone.datatypes.Product;
import hu.leone.ejbs.CartEJB;

/**
 * Servlet implementation class CartHandlingServlet
 */
@WebServlet("/CartHandlingServlet")
public class CartHandlingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartHandlingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartEJB cartEjb;
		/*
		 * 0. session-ből lekérdezés
		 * 1. alkalommal létrehozás
		 * 2. session-be eltároljuk
		 */
		HttpSession session = request.getSession();
		cartEjb = (CartEJB)session.getAttribute("cart");
		
		if(cartEjb == null) {	//még nincs kosár
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				cartEjb = (CartEJB) ctx.lookup("java:global.WebshopEAR.WebshopEJB.CartEJB");
			} catch(NamingException e) {
				e.printStackTrace();
			}
			session.setAttribute("cart", cartEjb); //session-be eltároljuk
		}
		
		String buttonPressed = request.getParameter("buttonPressed");
		/*
		System.out.println("---------------------------------------------------------------");
		System.out.println("buttonPressed: " + buttonPressed);
		System.out.println("---------------------------------------------------------------");
		*/
		if("AddToCart".equals(buttonPressed)) {
	
			//létező kosár
			int productId = Integer.parseInt(request.getParameter("selectedProductId"));
			cartEjb.addToCart(productId);
		}
		
		if("DeleteCart".equals(buttonPressed)) {
			
			//kosár törlése 
			cartEjb.deleteAllFromCart();
		}
		
		if("DeleteProductFromCart".equals(buttonPressed)) {
			
			//létező kosár, kosár törlése 
			int productId = Integer.parseInt(request.getParameter("selectedProductId"));
			System.out.print("Adott product id törölve a kosarbol: " + productId);
			cartEjb.deleteFromCart(productId);
		}	
		
		/* forward a hivo catalog.jsp-re es a  catalog.jsp frissitese */
		RequestDispatcher rd = request.getRequestDispatcher("catalog.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
