package Inventory;

import java.util.Iterator;

public interface ProductList {

	public void addProduct(GenericProduct key);
	
	public void removeProduct(GenericProduct key);
	
	public GenericProduct findProduct(GenericProduct key) throws CloneNotSupportedException;
	
	public void updateQty(GenericProduct key, int newQty); // newQty may or may not be right, just thinking about rebuilding that
														//dropdown
	
	public Iterator<GenericProduct> getIterator();
	
	public double getSellTotal();
	
	public double getWholesaleTotal();
	
	public boolean isEmpty();
}
