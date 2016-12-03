package Inventory;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductBundle implements GenericProduct, Serializable, Cloneable {

	public ProductBundle(){
		products = new ArrayList<GenericProduct>();
		bundleDescription = "Includes: ";
	}
	
	public void addProduct (GenericProduct p) {
		products.add(p);
		bundleDescription = bundleDescription + p.getName() + " ";
	}
	
	@Override
	public String getName() {
		return bundleName;
	}

	@Override
	public double getSellPrice() {
		double price = 0;
		for (GenericProduct i : products) {
			price = price + i.getSellPrice();
		}
		return price;
	}

	@Override
	public double getWholesalePrice() {
		double price = 0;
		for (GenericProduct i : products) {
			price = price + i.getWholesalePrice();
		}
		return price;
	}

	@Override
	public String getDescription() {
		return bundleDescription;
	}

	@Override
	public int getQty() {
		return bundleQty;
	}

	@Override
	public void updateProduct(String n, int q, double p1, double p2, String d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName(String n) {
		bundleName = n;
	}

	@Override
	public void setSellPrice(double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWholesalePrice(double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescription(String d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setQty(int key) {
		bundleQty = key;
		
	}

	@Override
	public GenericProduct clone() throws CloneNotSupportedException {
		return (GenericProduct) super.clone();
	}
	
	public int getSize() {
		return products.size();
	}
	
	private ArrayList<GenericProduct> products;
	private String bundleName;
	private String bundleDescription;
	private int bundleQty;
	private static final long serialVersionUID = -3501152276219961473L;

}
