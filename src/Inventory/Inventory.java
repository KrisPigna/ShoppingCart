package Inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import Database.AppendToDB;

	/**
	 *
	 * @author Paul
	 */
	public class Inventory {
	    
	    private ArrayList<Item> prodList;
	    
	    public Inventory() {
	        prodList = new ArrayList<>();
	        
	        // hard code some inventory objects
	        prodList.add(new Item("Umbrella", 15, 7.99));
	        prodList.add(new Item("Coffee Mug", 20, 3.99));
	        prodList.add(new Item("Legal Pad", 8, 2.99));
	        
	        try { // Insert your own directory to avoid errors. Filename extension must be .ser
				File path = new File("/Users/Paul/git/ShoppingCart/Inventory.ser");
				
				if(!path.exists()) {
					FileOutputStream fileOut = new FileOutputStream(path, true);
					ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
					
					for(Iterator<Item> itr = prodList.iterator(); itr.hasNext();) {
						Item temp = itr.next();
						objOut.writeObject(temp);
					}
					objOut.close();
					fileOut.close();
				}
				else {
					FileOutputStream fileOut = new FileOutputStream(path, true);
					AppendToDB objOut = new AppendToDB(fileOut);
					
					for(Iterator<Item> itr = prodList.iterator(); itr.hasNext();) {
						Item temp = itr.next();
						objOut.writeObject(temp);
					}
					objOut.close();
					fileOut.close();
				}
			} catch(IOException i) {
				
			}
	    }
	    
	    public void addProduct(Item key) {
	        prodList.add(key);
	    }
	    
	    public void removeProduct(Item key) {
	        prodList.remove(key);
	    }
	    
	    public Product findProduct(Item key)    {
	       int loc = prodList.indexOf(key);
	       return prodList.get(loc);
	    }
	        
	    public void updateQty(Item key)   {
	        int loc = prodList.indexOf(key);
	        
	        //this.getQty(); // CHANGE THIS. update value getting passed in through MVC interaction?   
	                                    // is there a way to get around qty needing to be public here
	                                    // as an instance variable of the Product class?
	    }
	    

	}
