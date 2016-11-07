package Inventory;

public class Product implements Item {

	private double price;
    public int qty;
    private String description;
    private String details;
    
    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getQty() {
        return qty;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String displayDetails() {
        return details;
    }

    @Override
    public void hideDetails() {
        // do something
    }
}
