package Inventory;

interface Item {

	public double getPrice();
    public int getQty();
    public String getDescription();
    public String displayDetails(); // do we need this? check instructor comments
    public void hideDetails(); // do we need this? check instructor comments
}