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

public class ShoppingCart extends ConcreteList implements Serializable {
	public ShoppingCart(){
		//prodList = new ArrayList<GenericProduct>();
	}
	
	public void checkout(){
		prodList = new ArrayList<GenericProduct>();
		saveToDB();
	}
	
	public void completeOrder(JButton event){
		
	}
	
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
	
	public double getAllRevenue(){
		double revenue = 0;
		try { 
			// Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("/Users/Paul/git/ShoppingCart/All_Sales.ser");
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