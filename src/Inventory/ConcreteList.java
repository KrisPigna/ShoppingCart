package Inventory;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class ConcreteList implements ProductList {

	@Override
	public void addProduct(Product key) {
		prodList.add(key);
	}

	@Override
	public void removeProduct(Product key) {
		prodList.remove(key);
	}

	@Override
	public Product findProduct(Product key) {
		int loc = prodList.indexOf(key);
		return prodList.get(loc);
	}

	@Override
	public void updateQty(Product key) {
		 int loc = prodList.indexOf(key);
	        
	        //this.getQty(); // CHANGE THIS. update value getting passed in through MVC interaction?   
	                                    // is there a way to get around qty needing to be public here
	                                    // as an instance variable of the Product class?
	}
	
	 public Iterator<Product> getIterator()
	 {
		 return new
				 Iterator<Product>() {
			 		public boolean hasNext() {
			 			return current < prodList.size();
			 		}

			 		public Product next() {
			 			return prodList.get(current++);
			 		}

			 		public void remove() {
			 			throw new UnsupportedOperationException();
			 		}

			 		private int current = 0;
		 };
	 }

	@Override
	public double getTotal() {
		double total = 0;
		for (Product i : prodList){
			total = total + (i.getPrice() * i.getQty());
		}
		return total;
	}
	
	protected ArrayList<Product> prodList = new ArrayList<Product>();
}
