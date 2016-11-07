package Inventory;

import java.util.ArrayList;

// hard code some inventory objects
	class Umbrella extends Product {
	        
	        public double getPrice() { return 9.99; }
	        public int getQty() { return 10; };
	        public String getDescription() {
	            return "Protects you from the rain and other light-weight, falling objects.";
	        }
	        public String displayDetails()  { // Return String here may be incorrect, how 
	                                            // do we want to handle this?
	            String allDetails = "";
	            
	            allDetails += String.valueOf(getPrice() + ", ");
	            allDetails += String.valueOf(getQty() + ", ");
	            allDetails += String.valueOf(getDescription() + ".");
	            
	            return allDetails; 
	        }
	    }
	    
	    class LegalPad extends Product {
	        
	        public double getPrice() { return 4.99; }
	        public int getQty() { return 18; };
	        public String getDescription() {
	            return "For writing on. Legally.";
	        }
	        public String displayDetails()  { // Return String here may be incorrect, how 
	                                            // do we want to handle this?
	            String allDetails = "";
	            
	            allDetails += String.valueOf(getPrice() + ", ");
	            allDetails += String.valueOf(getQty() + ", ");
	            allDetails += String.valueOf(getDescription() + ", ");
	            
	            return allDetails;
	        }
	    }
	    
	    class CoffeeMug extends Product {
	        
	        public double getPrice() { return 7.99; }
	        public int getQty() { return 36; };
	        public String getDescription() {
	            return "Dispenses sanity.";
	        }
	        /* (non-Javadoc)
	         * @see Inventory.Product#displayDetails()
	         */
	        public String displayDetails()  { // Return String here may be incorrect, how 
	                                            // do we want to handle this?
	            String allDetails = "";
	            
	            allDetails += String.valueOf(getPrice() + ", ");
	            allDetails += String.valueOf(getQty() + ", ");
	            allDetails += String.valueOf(getDescription() + ", ");
	            
	            return allDetails;
	        }
	    }

	/**
	 *
	 * @author Paul
	 */
	public class Inventory extends Product {
	    
	    private ArrayList<Product> prodList;
	    
	    public Inventory() {
	        prodList = new ArrayList<>();
	    }
	    
	    public void addProduct(Product key) {
	        prodList.add(key);
	    }
	    
	    public void removeProduct(Product key) {
	        prodList.remove(key);
	    }
	    
	    public Product findProduct(Product key)    {
	       int loc = prodList.indexOf(key);
	       return prodList.get(loc);
	    }
	        
	    public void updateQty(Product key)   {
	        int loc = prodList.indexOf(key);
	        
	        prodList.get(loc).qty = 0; // CHANGE THIS. update value getting passed in through MVC interaction?   
	                                    // is there a way to get around qty needing to be public here
	                                    // as an instance variable of the Product class?
	    }
	    
	    public static void main(String[] args) {
	     
	       Inventory mainInventory = new Inventory();
	       
	       mainInventory.addProduct(new Umbrella());
	       mainInventory.addProduct(new LegalPad());
	       mainInventory.addProduct(new CoffeeMug());
	       
	       
	    }
	}
