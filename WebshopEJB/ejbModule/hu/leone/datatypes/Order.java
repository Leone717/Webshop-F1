package hu.leone.datatypes;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Order {

	private int orderId;
	private Account account;
	private Map<Product, Integer> productList;
}
