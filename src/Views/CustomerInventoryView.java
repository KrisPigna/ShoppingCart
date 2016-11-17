package Views;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Inventory.Inventory;
import Inventory.Product;

public class CustomerInventoryView extends JPanel {
	public CustomerInventoryView(){
		JPanel products = new JPanel();
		products.setLayout(new GridLayout(0,1));
		Inventory mainInventory = new Inventory();
		Iterator<Product> i = mainInventory.getIterator();
		Product temp = new Product();
		while(i.hasNext()) {
			temp = i.next();
			JLabel itemDisplay = new JLabel(temp.getName());
			products.add(itemDisplay);
		}
		this.setLayout(new FlowLayout());
		this.add(products);
		this.setVisible(false);
		
	}
}
