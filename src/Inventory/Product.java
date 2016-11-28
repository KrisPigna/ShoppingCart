package Inventory;

import java.io.Serializable;

public class Product implements GenericProduct, Serializable, Cloneable {
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public Product(){
		name = null;
		qty = 0;
		sellPrice = 0;
		wholesalePrice = 0;
		details = null;
	}
	
	public Product(String n, int q, double p1, double p2, String d){
		name = n;
		qty = q;
		sellPrice = p1;
		wholesalePrice = p2;
		details = d;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public double getSellPrice() {
		return sellPrice;
	}
	
	@Override
	public double getWholesalePrice() {
		return wholesalePrice;
	}
	
	public void updateProduct(String n, int q, double p1, double p2, String d) {
		name = n;
		qty = q;
		sellPrice = p1;
		wholesalePrice = p2;
		details = d;
	}

	@Override
	public int getQty() {
		return qty;
	}
	
	@Override
	public void setQty(int key) {
		qty = key;
	}

	@Override
	public String getDescription() {
		return details;
	}

	@Override
	public String displayDetails() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public void hideDetails() {
		// TODO Auto-generated method stub

	}
	
	private String name;
	private int qty;
	private double sellPrice;
	private double wholesalePrice;
	private String details;
	private static final long serialVersionUID = 1L;

}