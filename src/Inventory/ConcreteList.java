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
		Product temp = null;
		try {
			temp = (Product) key.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	@Override
	public void updateQty(Product key, int newQty) {
		 key.setQty(newQty);
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
	public double getSellTotal() {
		double total = 0;
		for (Product i : prodList){
			total = total + (i.getSellPrice() * i.getQty());
		}
		return total;
	}
	
	@Override
	public double getWholesaleTotal() {
		double total = 0;
		for (Product i : prodList){
			total = total + (i.getWholesalePrice() * i.getQty());
		}
		return total;
	}
	
	protected ArrayList<Product> prodList = new ArrayList<Product>();
}