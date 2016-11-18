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
    	loadDB();
    	//************
        // Hard coded products to initially populate inventory; remove comments
    	// to add them at first, but comment out again to avoid adding them
    	// over and over each time the program runs - Kris
    	//************
    	/*prodList.add(new Product("Umbrella", 5, 15.99, 10.00, "Defeats the rain"));
    	prodList.add(new Product("Coffee Mug", 10, 7.99, 4.00, "Holds precious, precious coffee"));
    	prodList.add(new Product("Legal Pad", 20, 4.99, 2.00, "For writing on, legally"));*/
        saveToDB();
    }
    
    /**
     * Un-serializes the stored prodList to repopulate the list.
     */
    public void loadDB(){
    	try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Mario/git/ShoppingCart/Inventory.ser");
			
			if(path.exists()) {
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream objIn = new ObjectInputStream(fileIn);
				prodList = (ArrayList<Product>) objIn.readObject();
				objIn.close();
				fileIn.close();
			}
			//************
			// Commented this part out while testing; seems to work fine without it
			// so I'm not sure if we really need it? - Kris
			//************
			/*else {
				FileOutputStream fileOut = new FileOutputStream(path, true);
				AppendToDB objOut = new AppendToDB(fileOut);
				
				for(Iterator<Product> itr = inventory.iterator(); itr.hasNext();) {
					Product temp = itr.next();
					objOut.writeObject(temp);
				}
				objOut.close();
				fileOut.close();
			}*/
		} catch(IOException i) {
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Serializes the current prodList for persistent storing.
     */
    public void saveToDB(){
        try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Mario/git/ShoppingCart/Inventory.ser");
			//************
			// Commented bits and pieces out while testing; seems to work fine as is
			// so I'm not sure if we really need the other bits? - Kris
			//************
			//if(path.exists()) {
				FileOutputStream fileOut = new FileOutputStream(path, false);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				
				/*for(Iterator<Product> itr = inventory.iterator(); itr.hasNext();) {
					Product temp = itr.next();
					objOut.writeObject(temp);
				}*/
				objOut.writeObject(prodList);
				objOut.close();
				fileOut.close();
			//}
			/*else {
				FileOutputStream fileOut = new FileOutputStream(path, true);
				AppendToDB objOut = new AppendToDB(fileOut);
				
				for(Iterator<Product> itr = inventory.iterator(); itr.hasNext();) {
					Product temp = itr.next();
					objOut.writeObject(temp);
				}
				objOut.writeObject(prodList);
				objOut.close();
				fileOut.close();
			}*/
		} catch(IOException i) {
			
		}
    }

    private static final long serialVersionUID = 1L;
}
