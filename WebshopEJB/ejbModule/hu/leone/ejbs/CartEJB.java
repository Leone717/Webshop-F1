package hu.leone.ejbs;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import hu.leone.datatypes.Product;
import hu.leone.utils.DBOperation;

/**
 * Session Bean implementation class CartEJB
 */
@Stateful
@LocalBean
public class CartEJB {

	private int accountId;
	private Map<Product, Integer> products = new HashMap<>();
	
	@EJB
	CatalogEJB catEjb;
    /**
     * Default constructor. 
     */
    public CartEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
     
    public void addToCart(Product product) {
    	
    	if(products.containsKey(product)) { // termék darabszám növelése, ha már hozzáadtuk a kosárhoz
    		products.put(product, products.get(product) + 1);
    	} else {
    		products.put(product, 1); // még nincs a kosárban, 1 darabot adunk hozzá
    	}
    }
    
    public void deleteFromCart(int productId) {
    	Product product = catEjb.getproductById(productId);
    	products.remove(product);
    }
    
    public void deleteAllFromCart() {
    	products.clear();
    }
    
    public Map<Product, Integer> getProductList() {
    	return this.products;
    }
    
    @Remove
    public void saveCart() {
    	try {
    		DBOperation.saveCart(accountId, products);
    	} catch(SQLException | NamingException e) {
    		e.printStackTrace();
    	}
    	products = null;
    }

	public void addToCart(int productId) {
		// Product kikeresése productId alapján a CatalogEJB-ből*/
		Product product = catEjb.getproductById(productId);
		
		addToCart(product);
		
	}
	
	/*
    public void deleteOrder(int orderId) {
    	try {
    		DBOperation.deleteOrder(orderId);
    	} catch(SQLException | NamingException  e) {
    		e.printStackTrace();
    	}
    }*/

}
