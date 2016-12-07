package Inventory;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for creating a bundle of existing objects
 * @author kpigna
 *
 */
public class ProductBundle implements GenericProduct, Serializable, Cloneable {
	/**
	 * ProductBundle constructor
	 */
	public ProductBundle(){
		products = new ArrayList<GenericProduct>();
		bundleDescription = "Includes: ";
	}
	
	/**
	 * Method for adding a product to the bundle
	 * @param p The product to be added
	 */
	public void addProduct (GenericProduct p) {
		products.add(p);
		bundleDescription = bundleDescription + p.getName() + " ";
	}
	
	/**
	 * Accessor
	 * @return bundleName Name of the bundle
	 */
	@Override
	public String getName() {
		return bundleName;
	}

	/**
	 * Accessor
	 * @return price The total sell price of all products in the bundle
	 */
	@Override
	public double getSellPrice() {
		double price = 0;
		for (GenericProduct i : products) {
			price = price + i.getSellPrice();
		}
		return price;
	}

	/**
	 * Accessor
	 * @return price Total wholesale price of all products in the bundle
	 */
	@Override
	public double getWholesalePrice() {
		double price = 0;
		for (GenericProduct i : products) {
			price = price + i.getWholesalePrice();
		}
		return price;
	}

	/**
	 * Accessor
	 * @return bundleDescription
	 */
	@Override
	public String getDescription() {
		return bundleDescription;
	}

	/**
	 * Accessor
	 * @return bundleQty
	 */
	@Override
	public int getQty() {
		return bundleQty;
	}

	/**
	 * Method to update the name, quantity, and description of a bundle
	 */
	@Override
	public void updateProduct(String n, int q, double p1, double p2, String d) {
		bundleName = n;
		bundleQty = q;
		bundleDescription = d;
	}

	/**
	 * Change name of the bundle
	 */
	@Override
	public void setName(String n) {
		bundleName = n;
	}

	@Override
	public void setSellPrice(double p) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void setWholesalePrice(double p) {
		throw new UnsupportedOperationException();
		
	}

	/**
	 * Set new bundle description
	 */
	@Override
	public void setDescription(String d) {
		bundleDescription = d;
		
	}

	/**
	 * Set new quantity
	 */
	@Override
	public void setQty(int key) {
		bundleQty = key;
		
	}

	/**
	 * Return a clone of a ProductBundle
	 */
	@Override
	public GenericProduct clone() throws CloneNotSupportedException {
		return (GenericProduct) super.clone();
	}
	
	/**
	 * Method to find the number of products in the bundle
	 * @return Size of the products ArrayList
	 */
	public int getSize() {
		return products.size();
	}
	
	private ArrayList<GenericProduct> products;
	private String bundleName;
	private String bundleDescription;
	private int bundleQty;
	private static final long serialVersionUID = -3501152276219961473L;

}
