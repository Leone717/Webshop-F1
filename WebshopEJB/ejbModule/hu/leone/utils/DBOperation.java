package hu.leone.utils;

import static hu.leone.utils.StaticNames.DERBY_DS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import hu.leone.datatypes.Account;
import hu.leone.datatypes.Order;
import hu.leone.datatypes.Product;

public class DBOperation {

	//private static String dbURL = "jdbc:derby://localhost:1527/webshop;create=false";
	
	private static String SQL_GET_PRODUCT_LIST = "SELECT ID, NAME, IMAGE, PRICE, DESCRIPTION FROM APP.PRODUCT ORDER BY ID";
	private static String SQL_INSERT_PRODUCT = "INSERT INTO APP.PRODUCT (ID, NAME, IMAGE, PRICE, DESCRIPTION) VALUES ";
	private static String SQL_DELETE_PRODUCT = "DELETE FROM APP.PRODUCT WHERE ID = ";
	private static String SQL_INSERT_ORDERS = "INSERT INTO APP.ORDERS (ID, ACCOUNT_ID, PRODUCT_ID, AMOUNT) VALUES ";
	private static String SQL_DELETE_ORDERS = "DELETE APP.ORDERS";
	private static String SQL_INSERT_ACCOUNT = "INSERT INTO APP.ACCOUNT (ID, NAME, EMAIL) VALUES ";
	private static String SQL_GET_NEXT_ACCOUNT_ID = "VALUES ( NEXT VALUE FOR APP.ACCOUNT_ID)";
	private static String SQL_GET_NEXT_ORDER_ID = "VALUES ( NEXT VALUE FOR APP.ORDERS_ID)";
	private static String SQL_GET_ORDER_LIST = "SELECT O.ID, O.AMOUNT, A.NAME, A.EMAIL, P.NAME, P.PRICE FROM APP.ACCOUNT A, APP.ORDERS O, APP.PRODUCT P WHERE " +
								"O.ACCOUNT_ID = A.ID AND O.PRODUCT_ID = P.ID ORDER BY O.ID";
	private static String SQL_DELETE_ORDER = "DELETE FROM APP.ORDERS WHERE ID = ";
	
	public static Product[] getProductList() throws SQLException, NamingException {
		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}

		try (Connection conn = ds.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(SQL_GET_PRODUCT_LIST);

			ArrayList<Product> l = new ArrayList<>();
			while (results.next()) {
				int id = results.getInt(1);
				String name = results.getString(2);
				String image = results.getString(3);
				int price = results.getInt(4);
				String description = results.getString(5);

				l.add(new Product(id, name, image, price, description));
			}
			results.close();
			stmt.close();

			if (l.isEmpty())
				return new Product[0];

			return (Product[]) l.toArray(new Product[l.size()]);
		}
	}

	public static void insertProduct(Product product) throws SQLException, NamingException {
		String sql = SQL_INSERT_PRODUCT + "(NEXT VALUE FOR APP.PRODUCT_ID, " + "'" + product.getName() + "'," + "'"
				+ product.getImagePath() + "'," + "" + product.getPrice() + "," + "'" + product.getDescription() + "'"
				+ ")";
		System.out.println(sql);
		
		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}

		try (Connection conn = ds.getConnection()) {

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

			stmt.close();
		}
	}

	public static void deleteProduct(int id) throws SQLException, NamingException {
		String sql = SQL_DELETE_PRODUCT + id;
		System.out.println(sql);

		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		try (Connection conn = ds.getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
	}
	
	private static int getNextOrderId() throws SQLException, NamingException {
		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		int id = -1;

		try (Connection conn = ds.getConnection()) {
			ResultSet rs = conn.prepareStatement(SQL_GET_NEXT_ORDER_ID).executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			rs.close();
		}
		return id;
	}

	public static void saveCart(int accountId, Map<Product, Integer> products) throws SQLException, NamingException {
		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		if (products == null || products.size() == 0)
			return;

		int orderId = getNextOrderId();
		
		try (Connection conn = ds.getConnection()) {
			for (Map.Entry<Product, Integer> entry : products.entrySet()) {
				
				Product product = entry.getKey();
				int amount = entry.getValue();
				
				String sql = SQL_INSERT_ORDERS + "(" + orderId + "," + accountId + "," +
						+ product.getId() + "," + "" + amount + ")";

				System.out.println(sql);

				Statement stmt = conn.createStatement();
				stmt.execute(sql);
				stmt.close();
			}
		}
	}
	
	
	public static void deleteAllProductFromCart(Map<Product, Integer> products) throws SQLException, NamingException {
		DataSource ds = null;
		Context ctx = null;
		String sql = SQL_DELETE_ORDERS;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		if (products == null || products.size() == 0)
			return;

		try (Connection conn = ds.getConnection()) {
						
				System.out.println(sql);

				Statement stmt = conn.createStatement();
				stmt.execute(sql);
				stmt.close();
			}
	}

	private static int getNextAccountId() throws SQLException, NamingException {
		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		int id = -1;

		try (Connection conn = ds.getConnection()) {
			ResultSet rs = conn.prepareStatement(SQL_GET_NEXT_ACCOUNT_ID).executeQuery();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			rs.close();
		}
		return id;
	}

	public static int insertAccount(Account account) throws SQLException, NamingException {
		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		int accountId = getNextAccountId();

		String sql = SQL_INSERT_ACCOUNT + "(" + accountId + ", " + "'" + account.getName() + "'," + "'"
				+ account.getEmail() + "'" + ")";
		System.out.println(sql);

		try (Connection conn = ds.getConnection()) {

			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
		return accountId;
	}

	public static Order[] getOrderList() throws SQLException, NamingException {
		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		try (Connection conn = ds.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(SQL_GET_ORDER_LIST);

			int prevOrderId = -1;
			
			ArrayList<Order> l = new ArrayList<>();
			Map<Product, Integer> productList = null;
			
			boolean hasNext = false;
			while (hasNext = results.next()) {
				int orderId = results.getInt(1);
				int amount = results.getInt(2);
				
				Account account = new Account(results.getString(3), results.getString(4));
				Product product = new Product(0, results.getString(5),null,results.getInt(6),null);
				
				if(prevOrderId == orderId) {
					productList.put(product, amount);
					if(!hasNext)
						l.add(new Order(orderId, account, productList));
				} else {
					productList = new HashMap<>();
					productList.put(product, amount);
					l.add(new Order(orderId, account, productList));
				}
				
				prevOrderId = orderId;
			}
			results.close();
			stmt.close();

			if (l.isEmpty())
				return new Order[0];

			return (Order[]) l.toArray(new Order[l.size()]);
		}
		
	}
	
	public static void deleteOrder(int id) throws SQLException, NamingException {
		String sql = SQL_DELETE_ORDER + id;
		System.out.println(sql);

		DataSource ds = null;
		Context ctx = null;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup (DERBY_DS);
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
		
		try (Connection conn = ds.getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
		}
	}
}
