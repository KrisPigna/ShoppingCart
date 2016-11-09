package Inventory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import Database.AppendToDB;



// hard code some inventory objects
	class Umbrella extends Product implements java.io.Serializable {
	        
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
	    
	    class LegalPad extends Product implements java.io.Serializable {
	        
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
	    
	    class CoffeeMug extends Product implements java.io.Serializable {
	        
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
	        
	        prodList.add(new Umbrella());
	        prodList.add(new LegalPad());
	        prodList.add(new CoffeeMug());
	        
	        try { // Insert your own directory to avoid errors. Filename extension must be .ser
				File path = new File("/Users/Robert/git/ShoppingCart/Inventory.ser");
				
				if(!path.exists()) {
					FileOutputStream fileOut = new FileOutputStream(path, true);
					ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
					
					for(Iterator<Product> itr = prodList.iterator(); itr.hasNext();) {
						Product temp = itr.next();
						objOut.writeObject(temp);
					}
					objOut.close();
					fileOut.close();
				}
				else {
					FileOutputStream fileOut = new FileOutputStream(path, true);
					AppendToDB objOut = new AppendToDB(fileOut);
					
					for(Iterator<Product> itr = prodList.iterator(); itr.hasNext();) {
						Product temp = itr.next();
						objOut.writeObject(temp);
					}
					objOut.close();
					fileOut.close();
				}
			}
			catch(IOException i) {
				i.printStackTrace();
			}
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
	    

	}
