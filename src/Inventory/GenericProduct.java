package Inventory;

interface GenericProduct {
	public String getName();
	public double getPrice();
    public int getQty();
    public void setQty(int key);
    public String getDescription();
    public String displayDetails(); // do we need this? check instructor comments
    public void hideDetails(); // do we need this? check instructor comments
}
