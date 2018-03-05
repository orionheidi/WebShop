package servlet1.webshop;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1769169794415719097L;
	
	public User(String username, String passsword) {
		super();
		this.username = username;
		this.password = passsword;
		this.shoppingCart = new ShoppingCart();
	}
	
	public User(int id, String username, String passsword) {
		super();
		this.username = username;
		this.password = passsword;
		this.shoppingCart = new ShoppingCart();
		this.id = id;
	}

	public User() {
		super();
		this.shoppingCart = new ShoppingCart();
	}
	protected String username;
	protected String password;
	protected int id;
	protected ShoppingCart shoppingCart;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsernameAndPassword(){
		return username+":"+password;
	}
	
	@Override
	public boolean equals(Object obj) {
		User u = (User) obj;
		if(this.username.equals(u.getUsername())&&this.password.equals(u.getPassword())){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", shoppingCart=" + shoppingCart + "]";
	}
	
}
