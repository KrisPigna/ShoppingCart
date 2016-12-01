package Inventory;

import java.io.Serializable;

public class Product implements GenericProduct, Serializable, Cloneable {
	
	@Override
	public GenericProduct clone() throws CloneNotSupportedException {
		return (GenericProduct) super.clone();
	}

	public Product(){
		name = null;
		qty = 0;
		sellPrice = 0;
		wholesalePrice = 0;
		description = null;
	}
	
	public Product(String n, int q, double p1, double p2, String d){
		name = n;
		qty = q;
		sellPrice = p1;
		wholesalePrice = p2;
		description = d;
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
		description = d;
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
		return description;
	}
	
	@Override
	public void setName(String n) {
		name = n;
	}

	@Override
	public void setSellPrice(double p) {
		sellPrice = p;
	}

	@Override
	public void setWholesalePrice(double p) {
		wholesalePrice = p;
	}

	@Override
	public void setDescription(String d) {
		description = d;
	}
	
	private String name;
	private int qty;
	private double sellPrice;
	private double wholesalePrice;
	private String description;
	private static final long serialVersionUID = 1L;

}