package Inventory;

import java.util.Iterator;

public interface ProductList {

	public void addProduct(Product key);
	
	public void removeProduct(Product key);
	
	public Product findProduct(Product key);
	
	public void updateQty(Product key);
	
	public Iterator<Product> getIterator();
	
	public double getTotal();
}
