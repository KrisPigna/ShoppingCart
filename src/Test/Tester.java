package Test;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import static org.junit.Assert.*;
import org.junit.Test;

import Database.*;
import Inventory.*;
import Models.*;
/**
 * Tester class for the Shopping Cart Java Swing Application that tests a select number of functionalities.
 * <p>Will test to verify product properly removed from inventory after purchase.</p>
 * <p>Ensures revenue of a sale gets recorded.</p>
 * @author Paul
 */
public class Tester {
	/**
	 * Tests a regular customer purchase process. 
	 * <p>Will test to verify product properly removed from inventory after purchase.</p>
	 * @throws CloneNotSupportedException 
	 */
	@Test
	public void testPurchaseProcess() throws CloneNotSupportedException {
		
		ShoppingCart testCart = new ShoppingCart();
		TestInventory testInventory = new TestInventory();
		testInventory.createTestInventory();
		
		Iterator<GenericProduct> i = testInventory.getIterator();
		GenericProduct temp = null;
		while(i.hasNext()) {
			temp = i.next();
		}
		int chosenQty = 1;
		GenericProduct selected = testInventory.findProduct(temp);
		int productQty = selected.getQty();
		selected.setQty(chosenQty);
		testCart.addProduct(selected);
		temp.setQty(productQty - chosenQty);
		testCart.checkout();
		testInventory.saveToDB();
		
		int expectedQtyInInventory = 0;
		assertTrue(expectedQtyInInventory == temp.getQty());
	}
	/**
	 * Ensures revenue of a sale gets recorded
	 * @throws CloneNotSupportedException 
	 */
	@Test
	public void testStatsTracked() throws CloneNotSupportedException {
		TestCart testCart = new TestCart();
		TestInventory testInventory = new TestInventory();
		testInventory.createTestInventory();
		
		Iterator<GenericProduct> i = testInventory.getIterator();
		GenericProduct temp = null;
		while(i.hasNext()) {
			temp = i.next();
		}
		GenericProduct selected = testInventory.findProduct(temp);
		testCart.addProduct(selected);
		testCart.saveToDB();
		testCart.checkout();
		assertTrue(testCart.getAllRevenue() == 7.99); // 7.99 is sale of one coffee cup, which we sold out in the previous test.
		File clearForNextRun = new File("/Users/Paul/git/ShoppingCart/Test_All_Sales.ser");
		clearForNextRun.delete();
	}
	/**
	 * Tests that a seller can edit the quantity of an existing inventory item
	 */
	@Test
	public void testEditInventoryQty() {
		// T0-DO once edit is implemented for InventoryManagementView
	}
	/**
	 * Begin main method to run tests
	 * @param args
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		Tester testCase = new Tester();
		testCase.testPurchaseProcess();
		testCase.testStatsTracked();
	}
}
/**
 * TestCart to extend ShoppingCart, saves data to a different file
 * @author Paul
 */
class TestCart extends ShoppingCart {	
	public void checkout(){
		prodList = new ArrayList<GenericProduct>();
		saveToDB();
	}
	
	public void saveToDB(){
		try { 
			// Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Paul/git/ShoppingCart/Test_All_Sales.ser");
			if(!path.exists()) {
				FileOutputStream fileOut = new FileOutputStream(path, true);
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
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public double getAllRevenue(){
		double revenue = 0;
		try { 
			// Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("/Users/Paul/git/ShoppingCart/Test_All_Sales.ser");
			ObjectInputStream obj_in = new ObjectInputStream(file_in);
			ArrayList<Product> allSales = (ArrayList<Product>) obj_in.readObject();
			try {
				while (allSales != null) {
					for (Product i : allSales)	{
						revenue = revenue + i.getSellPrice();
					}
					allSales = (ArrayList<Product>) obj_in.readObject();
				}
			} catch (EOFException e) {
				file_in.close();
				obj_in.close();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return revenue;
	}
}

/**
 * TestInventory to extend Inventory, saves data to a different file 
 * @author Paul
 */
class TestInventory extends Inventory {
    private static final long serialVersionUID = 1L;
    
	public TestInventory() {
    	loadDB(); 
        saveToDB();
    }
	
	public void createTestInventory() {
		prodList.add(new Product("Coffee Mug", 1, 7.99, 4.00, "Holds precious, precious coffee"));
		saveToDB();
	}
    
    /**
     * Un-serializes the stored prodList to repopulate the list.
     */
    public void loadDB(){
    	try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Paul/git/ShoppingCart/TestInventory.ser");
			
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
			File path = new File("/Users/Paul/git/ShoppingCart/TestInventory.ser");
				FileOutputStream fileOut = new FileOutputStream(path, false);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(prodList);
				objOut.close();
				fileOut.close();

		} catch(IOException i) {
			
		}
    }
}