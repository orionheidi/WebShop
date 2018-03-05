package servlet1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servlet1.webshop.Product;
import servlet1.webshop.ShoppingCartItem;
import util.ConnectionManager;

public class ShoppingCartItemDAO {
	public boolean insert(int productId, int userId, int count){
		boolean retVal = false;
		try {
			String update = "INSERT INTO ShoppingCartItem (count, User_id, Product_id) VALUES (?, ?, ?);";
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(update);
			pstmt.setInt(1, count);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, productId);
			if (pstmt.executeUpdate() == 1) {
				retVal = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retVal;
	}
	
	public List<ShoppingCartItem> getAll(int user_id){
		String query = "SELECT * FROM ShoppingCartItem sci left join Product p on sci.Product_id = p.id where user_id="+user_id+";";
		Statement stmt;
		List<ShoppingCartItem> retVal = null;
		try {
			stmt = ConnectionManager.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			retVal = new ArrayList<ShoppingCartItem>();
			while (rset.next()) {
				int count = rset.getInt(2);
				int p_id = rset.getInt(4);
				String name = rset.getString(6);
				int price = rset.getInt(7);
				Product p = new Product(p_id, name, price);
				ShoppingCartItem sci = new ShoppingCartItem(p, count);
				retVal.add(sci);
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;

			
	}
}
