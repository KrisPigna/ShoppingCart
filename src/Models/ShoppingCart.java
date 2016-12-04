package Models;

import java.awt.event.ActionEvent;
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

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;

import Database.AppendToDB;
import Inventory.ConcreteList;
import Inventory.GenericProduct;
import Inventory.Product;
/**
 * Class to simulate the user's shopping cart.
 * <p>This class mostly handles storing of cart data in database, while its
 * inherited ConcreteList handles the functionality of the Shopping Cart itself</p>
 * @author Paul
 */
public class ShoppingCart extends ConcreteList implements Serializable {
	public ShoppingCart(){}
	/**
	 * Dump the cart and save its state. 
	 */
	public void checkout(){
		saveToDB();
		prodList = new ArrayList<GenericProduct>();
	}
	/**
	 * Scope of project does not include a full on "complete order and pay" process, however
	 * placeholder method is here for future implementation.
	 * <p>Current "complete the order" functionality is handled by change listener in appropriate view.</p>
	 */
	public void completeOrder(){
		
	}
	/**
	 * Save state of the cart for statistics purposes
	 */
	public void saveToDB(){
		try { 
			// Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Paul/git/ShoppingCart/All_Sales.ser");
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
	/**
	 * Get all revenue from transactions
	 * @return revenue
	 */
	public double getAllRevenue(){
		double revenue = 0;
		try { 
			// Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("/Users/Paul/git/ShoppingCart/All_Sales.ser");
			ObjectInputStream obj_in = new ObjectInputStream(file_in);
			ArrayList<Product> allSales = (ArrayList<Product>) obj_in.readObject();
			try {
				while (allSales != null) {
					for (GenericProduct i : allSales)	{
						revenue = revenue + i.getSellPrice() * i.getQty();
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