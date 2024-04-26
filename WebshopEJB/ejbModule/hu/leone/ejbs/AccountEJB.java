package hu.leone.ejbs;

import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import hu.leone.datatypes.Account;
import hu.leone.utils.DBOperation;

/**
 * Session Bean implementation class AccountEJB
 */
@Stateless
@LocalBean
public class AccountEJB {

    /**
     * Default constructor. 
     */
    public AccountEJB() {
        // TODO Auto-generated constructor stub
    }
    
    public int insertAccount(Account account) {
    	try {
    		return DBOperation.insertAccount(account);
    	
    	} catch (SQLException | NamingException e) {
    		e.printStackTrace();
    	}
    	return 0;
    }

}
