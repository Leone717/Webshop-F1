package hu.leone.ejbs;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.naming.NamingException;

import hu.leone.datatypes.Order;
import hu.leone.datatypes.Product;
import hu.leone.utils.DBOperation;

/**
 * Session Bean implementation class OrderEJB
 */
@Stateful
@LocalBean
public class OrderEJB {

    /**
     * Default constructor. 
     */
    public OrderEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public Order[] getOrderList() {
    	try {
    		return DBOperation.getOrderList();
    	} catch(SQLException | NamingException  e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public void deleteOrder(int orderId) {
    	try {
    		DBOperation.deleteOrder(orderId);
    	} catch(SQLException | NamingException  e) {
    		e.printStackTrace();
    	}
    }

}
