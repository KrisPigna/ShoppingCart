package Inventory;

interface GenericProduct {
	public String getName();
	public double getSellPrice();
	public double getWholesalePrice();
    public int getQty();
    public void updateProduct(String n, int q, double p1, double p2, String d);
    public void setQty(int key);
    public String getDescription();
    public String displayDetails(); // do we need this? check instructor comments
    public void hideDetails(); // do we need this? check instructor comments
}
