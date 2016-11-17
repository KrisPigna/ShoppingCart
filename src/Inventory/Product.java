package Inventory;

import java.io.Serializable;

public class Product implements GenericProduct, Serializable {
	/**
	 * 
	 */
	public Product(){
		name = null;
		sellPrice = 0;
		wholesalePrice = 0;
		details = null;
	}
	
	public Product(String n, double p1, double p2, String d){
		name = n;
		sellPrice = p1;
		wholesalePrice = p2;
		details = d;
	}


	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public double getPrice() {
		return sellPrice;
	}

	@Override
	public int getQty() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String displayDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hideDetails() {
		// TODO Auto-generated method stub

	}
	
	private String name;
	private double sellPrice;
	private double wholesalePrice;
	private String details;
	private static final long serialVersionUID = 1L;

}
