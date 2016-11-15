package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Inventory.Product;

public class CustomerInventoryView extends JPanel {
	public CustomerInventoryView(){
		
		JLabel itemDisplay = new JLabel();
		ArrayList<Product> inventory = new ArrayList<>();
		
		try { // Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("C:/Users/Paul/git/ShoppingCart/Inventory.ser");
			ObjectInputStream obj_in = new ObjectInputStream(file_in);
			Product temp = (Product) obj_in.readObject();
					

			try {
				while(temp != null) {
					inventory.add(temp);
					temp = (Product) obj_in.readObject(); 
				}
			} catch (EOFException e) {
				file_in.close();
				obj_in.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		for(Product product: inventory) {
			
		}
		
		this.setLayout(new FlowLayout());
		this.add(itemDisplay, BorderLayout.CENTER);
		this.setVisible(false);
		
	}
}
