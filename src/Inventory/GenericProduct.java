package Inventory;

public interface GenericProduct {
	public String getName();
	public double getSellPrice();
	public double getWholesalePrice();
	public String getDescription();
    public int getQty();
    public void updateProduct(String n, int q, double p1, double p2, String d);
    public void setName(String n);
    public void setSellPrice(double p);
    public void setWholesalePrice(double p);
    public void setDescription(String d);
    public void setQty(int key);
	public GenericProduct clone() throws CloneNotSupportedException;
}
