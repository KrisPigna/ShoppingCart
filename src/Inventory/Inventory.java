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
    	//inventory = ConcreteList.getList();
    	loadDB();
        // hard code some inventory objects
        saveToDB();
    }
    
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
    
    public void saveToDB(){
        try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Mario/git/ShoppingCart/Inventory.ser");
			
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
    
    //private ArrayList<Product> inventory;
    private static final long serialVersionUID = 1L;
}
