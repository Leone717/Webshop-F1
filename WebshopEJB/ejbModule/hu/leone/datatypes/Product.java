package hu.leone.datatypes;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode		//rendezesnel
@ToString
public class Product {
	
	private int id;
	private String name;
	private String imagePath;
	private int price;
	private String description;
}
