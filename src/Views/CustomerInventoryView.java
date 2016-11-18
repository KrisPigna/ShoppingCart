package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Inventory.Inventory;
import Inventory.Product;

public class CustomerInventoryView extends JPanel {
	public CustomerInventoryView(){
		JPanel products = buildInventoryList();
		JLabel header = new JLabel("Shop-A-Tron 5000");
		header.setFont(new Font("Serif",  Font.BOLD, 30));
		JScrollPane productScroll = new JScrollPane(products);
		productScroll.setPreferredSize(new Dimension(300, 400));
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(productScroll, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(400, 450));
		this.setVisible(false);
		
	}
	
	/**
	 * Populates a JPanel with all the items in the inventory
	 * and returns that JPanel for adding into the CustomerInventoryView
	 * @return JPanel with all inventory items added to it
	 */
	public JPanel buildInventoryList(){
		Inventory mainInventory = new Inventory();
		Iterator<Product> i = mainInventory.getIterator();
		Product temp = new Product();
		JPanel products = new JPanel();
		products.setLayout(new GridLayout(0,1,0,5));
		while(i.hasNext()) {
			temp = i.next();
			//this array is for making the "quantity" drop-down menu for each product
			String[] quantity = new String[temp.getQty() + 1];
			quantity[0] = "Quantity";
			for (int j = 1; j <= temp.getQty(); j++) {
				quantity[j] = Integer.toString(j);
			}
			JLabel itemName = new JLabel(temp.getName());
			JComboBox amount = new JComboBox(quantity);
			JButton add = new JButton("Add to Cart");
			JPanel itemDisplay = new JPanel();
			itemDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			itemDisplay.setBackground(Color.WHITE);
			itemDisplay.setLayout(new FlowLayout());
			itemDisplay.add(itemName);
			itemDisplay.add(amount);
			itemDisplay.add(add);
			products.add(itemDisplay);
		}
		return products;
	}
}
