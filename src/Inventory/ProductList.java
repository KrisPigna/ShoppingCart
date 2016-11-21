package Inventory;

import java.util.Iterator;

public interface ProductList {

	public void addProduct(Product key);
	
	public void removeProduct(Product key);
	
	public Product findProduct(Product key);
	
	public void updateQty(Product key, int newQty); // newQty may or may not be right, just thinking about rebuilding that
														//dropdown
	
	public Iterator<Product> getIterator();
	
	public double getSellTotal();
	
	public double getWholesaleTotal();
}
