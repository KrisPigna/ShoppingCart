package Inventory;

import java.io.EOFException;
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
    	getAllCosts();
    	//************
        // Hard coded products to initially populate inventory; remove comments
    	// to add them at first, but comment out again to avoid adding them
    	// over and over each time the program runs - Kris
    	//************
//    	prodList.add(new Product("Umbrella", 5, 15.99, 10.00, "Defeats the rain"));
//    	prodList.add(new Product("Coffee Mug", 10, 7.99, 4.00, "Holds precious, precious coffee"));
//    	prodList.add(new Product("Legal Pad", 20, 4.99, 2.00, "For writing on, legally"));
//    	prodList.add(new DiscountProduct(new Product("Legal Pad", 20, 4.99, 2.00, "For writing on, legally"), 0.5));
        saveToDB();
    }
    
    /**
     * Un-serializes the stored prodList to repopulate the list.
     */
    public void loadDB(){
    	try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Paul/git/ShoppingCart/Inventory.ser");
			
			if(path.exists()) {
				FileInputStream fileIn = new FileInputStream(path);
				ObjectInputStream objIn = new ObjectInputStream(fileIn);
				prodList = (ArrayList<GenericProduct>) objIn.readObject();
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
				FileOutputStream fileOut = new FileOutputStream(path, false);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(prodList);
				objOut.close();
				fileOut.close();

		} catch(IOException i) {
			
		}
    }
    
    public void saveCostsToDB(double newCost){
		try { 
			costHistory = costHistory + newCost;
			// Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Mario/git/ShoppingCart/All_Costs.ser");
			//if(!path.exists()) {
				FileOutputStream fileOut = new FileOutputStream(path, false);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(costHistory);
				objOut.close();
				fileOut.close();
			//}
/*			else {
				FileOutputStream fileOut = new FileOutputStream(path, false);
				AppendToDB objOut = new AppendToDB(fileOut);
				objOut.writeObject(costHistory); 
				objOut.close();
				fileOut.close();
			}*/
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Get all costs from inventory
	 * @return costs
	 */
	public void getAllCosts(){
		double costs = 0;
		try { 
			// Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("/Users/Mario/git/ShoppingCart/All_Costs.ser");
			ObjectInputStream obj_in = new ObjectInputStream(file_in);
			costHistory = (double) obj_in.readObject();
				file_in.close();
				obj_in.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public double getCosts() {
		return costHistory;
	}
	
	private double costHistory;
    private static final long serialVersionUID = 1L;
}