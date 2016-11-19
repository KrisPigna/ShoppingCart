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
			* My previous long explanation here may be further inapplicable. When something gets sold out,
			* we'll update prodList and save it to DB again. I don't believe there's any point of appending
			* different versions of the DB, so I'm changing this code back to the way you had it :). Turns out
			* the AppendToDB class is good for the username/password only. If I'm not mistaken, the file will now
			* just overwrite itself each time we serialize prodList, recreating a single updated database each time.
			*
			 */
				FileOutputStream fileOut = new FileOutputStream(path, false);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(prodList);
				objOut.close();
				fileOut.close();

		} catch(IOException i) {
			
		}
    }

    private static final long serialVersionUID = 1L;
}
