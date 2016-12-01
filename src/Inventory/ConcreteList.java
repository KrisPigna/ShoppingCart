package Inventory;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class ConcreteList implements ProductList {
	@Override
	public void addProduct(GenericProduct selected) {
		prodList.add(selected);
	}

	@Override
	public void removeProduct(GenericProduct key) {
		prodList.remove(key);
	}

	@Override
	public GenericProduct findProduct(GenericProduct temp2) throws CloneNotSupportedException {
		GenericProduct temp = null;
		temp = temp2.clone();
		
		return temp;
	}

	@Override
	public void updateQty(GenericProduct key, int newQty) {
		 key.setQty(newQty);
	}
	
	 public Iterator<GenericProduct> getIterator()
	 {
		 return new
				 Iterator<GenericProduct>() {
			 		public boolean hasNext() {
			 			return current < prodList.size();
			 		}

			 		public GenericProduct next() {
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
		for (GenericProduct i : prodList){
			total = total + (i.getSellPrice() * i.getQty());
		}
		return total;
	}
	
	@Override
	public double getWholesaleTotal() {
		double total = 0;
		for (GenericProduct i : prodList){
			total = total + (i.getWholesalePrice() * i.getQty());
		}
		return total;
	}
	
	protected ArrayList<GenericProduct> prodList = new ArrayList<GenericProduct>();
}