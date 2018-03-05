package servlet1.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servlet1.webshop.Product;
import util.ConnectionManager;

public class ProductDAO {
	public List<Product> getAll(){
		String query = "select id, name, price from Product";
		Statement stmt;
		List<Product> retVal = null;
		try {
			stmt = ConnectionManager.getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(query);
			retVal = new ArrayList<Product>();
			while (rset.next()) {
				int id = rset.getInt(1);
				String name = rset.getString(2);
				int price= rset.getInt(3);
				Product p = new Product(id, name, price);
				retVal.add(p);
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
