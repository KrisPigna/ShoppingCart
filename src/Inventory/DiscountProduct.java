package Inventory;

import java.io.Serializable;
import java.text.NumberFormat;

public class DiscountProduct implements GenericProduct, Serializable, Cloneable {

	@Override
	public GenericProduct clone() throws CloneNotSupportedException {
		return (GenericProduct) super.clone();
	}

	public DiscountProduct(GenericProduct prod, double disc){
		product = prod;
		discount = disc;
	}

	@Override
	public String getName() {
		return product.getName();
	}
	
	@Override
	public double getSellPrice() {
		return product.getSellPrice() * discount;
	}
	
	@Override
	public double getWholesalePrice() {
		return product.getWholesalePrice();
	}
	
	@Override
	public int getQty() {
		return product.getQty();
	}

	@Override
	public String getDescription() {
		return product.getDescription() + " - " + Double.toString(discount * 100) + "% discount";
	}
	
	public void updateProduct(String n, int q, double p1, double p2, String d) {
		product.setName(n);
		product.setQty(q);
		product.setSellPrice(p1);
		product.setWholesalePrice(p2);
		product.setDescription(d);
	}
	
	@Override
	public void setName(String n) {
		product.setName(n);
	}

	@Override
	public void setSellPrice(double p) {
		product.setSellPrice(p);
	}

	@Override
	public void setWholesalePrice(double p) {
		product.setWholesalePrice(p);
	}

	@Override
	public void setDescription(String d) {
		product.setDescription(d);
	}
	
	@Override
	public void setQty(int key) {
		product.setQty(key);
	}
	
	public void setDiscount(double key) {
		discount = key;
	}
	
	private GenericProduct product;
	private double discount;
	private static final long serialVersionUID = 1L;

}
