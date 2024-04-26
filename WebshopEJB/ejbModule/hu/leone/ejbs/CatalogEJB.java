package hu.leone.ejbs;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import hu.leone.datatypes.Product;
import hu.leone.utils.DBOperation;

/**
 * Session Bean implementation class CatalogEJB
 */
@Stateless
@LocalBean
public class CatalogEJB {

    /**
     * Default constructor. 
     */
    public CatalogEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public Product[] getProductList() {
    	try {
    		return DBOperation.getProductList();
    	} catch(SQLException | NamingException  e) {
    		e.printStackTrace();
    	}
    	return null;
 	
    }
    
    public void insertProduct(Product product) {
    	/* product validalasa */
    	/* komplex uzleti logika */
    	try {
    		DBOperation.insertProduct(product);
    	} catch(SQLException | NamingException  e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void deleteProduct(int productId) {
    	try {
    		DBOperation.deleteProduct(productId);
    	} catch(SQLException | NamingException  e) {
    		e.printStackTrace();
    	}
    }
    
    public Product getproductById(int productId) {
    	Product[] products = getProductList();
    	for(Product p: products) {
    		if(p.getId() == productId)
    			return p;
    		
    	}
    	return null;
    }
}
