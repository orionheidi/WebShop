package servlet1.webshop;

/** Reprezentuje jedan proizvod. Cuva se id, naziv i jedinicna cena. */
public class Product {

	private int id;
	private String name;
	private int price;

	public Product(int id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	
	public void setId(int i) {
		id = i;
	}

	public int getId() {
		return id;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setPrice(int p) {
		price = p;
	}

	public int getPrice() {
		return price;
	}

}
