package Inventory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import Database.AppendToDB;

	/**
	 *
	 * @author Paul
	 */
public class Inventory extends ConcreteList implements Serializable {
	    
    public Inventory() {
    	//loadDB(); 
    	//************
        // Hard coded products to initially populate inventory; remove comments
    	// to add them at first, but comment out again to avoid adding them
    	// over and over each time the program runs - Kris
    	//************
    	prodList.add(new Product("Umbrella", 5, 15.99, 10.00, "Defeats the rain"));
    	prodList.add(new Product("Coffee Mug", 10, 7.99, 4.00, "Holds precious, precious coffee"));
    	prodList.add(new Product("Legal Pad", 20, 4.99, 2.00, "For writing on, legally"));
        saveToDB();
    }
    
    /**
     * Un-serializes the stored prodList to repopulate the list.
     */
    // NOTE: prodList never empties itself, I don't think? so this isn't actually doing anything when we call it.
    //			However, we'll keep it because it should be here. Call in the default constructor is not needed.
    public void loadDB(){
    	try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Paul/git/ShoppingCart/Inventory.ser");
			
			if(path.exists()) {
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream objIn = new ObjectInputStream(fileIn);
				prodList = (ArrayList<Product>) objIn.readObject();
				objIn.close();
				fileIn.close();
			}
			
		} catch(IOException i) {
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Serializes the current prodList for persistent storing.
     */
    public void saveToDB(){
        try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Paul/git/ShoppingCart/Inventory.ser");
			/*
			 * "!" got removed from path.exists(). If block is specifically for when file doesn't exist yet.
			 * The if block creates it. Subsequent calls will then always land in the else block.
			 * The two are different due to my "AppendToDB" class, used only in else block, replacing
			 * ObjectOutputStream, 
			 *  which ignores headers of subsequent
			 * items added. The purpose of this was realized when building this code for verify login; 
			 * it ensures the deserialized object has the same hashcode when it comes out as when it went in, 
			 * and allows "appending" to the .ser file on separate occassions without overwriting it. 
			 * We're now only adding the prodList, a single object, so while this isn't entirely necessary,
			 * It's in place if we in fact wanted to add something else. 
			 * It's also somewhat unnecessary for prodList, since we're not comparing anything, however
			 * I felt it was good to use the same code for code and object consistency.  
			 * - Paul
			 */
			if(!path.exists()) {
				FileOutputStream fileOut = new FileOutputStream(path, false);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(prodList);
				objOut.close();
				fileOut.close();
			}
			else {
				FileOutputStream fileOut = new FileOutputStream(path, true);
				AppendToDB objOut = new AppendToDB(fileOut);
				objOut.writeObject(prodList);
				objOut.close();
				fileOut.close();
			}
		} catch(IOException i) {
			
		}
    }

    private static final long serialVersionUID = 1L;
}
