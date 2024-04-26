package hu.leone.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hu.leone.datatypes.Product;
import hu.leone.ejbs.CatalogEJB;
import hu.leone.utils.Utils;

/**
 * Servlet implementation class ProductHandlingServlet
 */
@WebServlet("/ProductHandlingServlet")
public class ProductHandlingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CatalogEJB catalogEjb;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductHandlingServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//if(request.getParameter("buttonPressed").equals("DeleteProduct")) {
		
		String buttonPressed = request.getParameter("buttonPressed");
		
		if("DeleteProduct".equals(buttonPressed)) {
			/* termek torlese */
			deleteProduct(request, response);
		} else {
			/* termek beszurasa */
			insertProduct(request, response);
		}
		
	}

	private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*termek torles ID alapjan */
		int productId = Integer.parseInt(request.getParameter("selectedProductId"));
		
		catalogEjb.deleteProduct(productId);
		
		/* forward a hivo catalog.jsp-re es a  catalog.jsp frissitese(???) */
		RequestDispatcher rd = request.getRequestDispatcher("catalog.jsp");
		//request.setAttribute(deleteProductMessage, "Sikeres művelet!");
		rd.forward(request, response);
	}

	private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* formbol product object letrehozasa */
		String productName = request.getParameter("productName");
		String productDescription = request.getParameter("productDescription");
		int productPrice = Integer.parseInt(request.getParameter("productPrice"));
		String productImage = request.getParameter("productImage");
		
		Product product = new Product(0, productName, productImage, productPrice, productDescription);
		
		/*CatalogEJB.insertProduct() */
		catalogEjb.insertProduct(product);
		
		/*popup ablak bezarasa */
		Utils.closePopup(response, "catalog.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
