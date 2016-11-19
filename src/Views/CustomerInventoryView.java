package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.Inventory;
import Inventory.Product;
import Models.ShoppingCart;

public class CustomerInventoryView extends JPanel {
	public CustomerInventoryView(Inventory inv, ShoppingCart cart){
		JLabel total = new JLabel("Cart total: $");
		cartTotal = new JLabel("0");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(total);
		header.add(cartTotal);
		JPanel products = buildInventoryList(inv, cart);
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
	public JPanel buildInventoryList(Inventory inv, ShoppingCart cart){
		Iterator<Product> i = inv.getIterator();
		JPanel products = new JPanel();
		products.setLayout(new GridLayout(0,1,0,5));
		while(i.hasNext()) {
			final Product temp = i.next();
			//if product quantity in inventory is 0, this is skipped and item not added to view
			if (temp.getQty() > 0) {
				//this array is for making the "quantity" drop-down menu for each product
				String[] quantity = new String[temp.getQty() + 1];
				quantity[0] = "Quantity";
				for (int j = 1; j <= temp.getQty(); j++) {
					quantity[j] = Integer.toString(j);
				}
				JLabel productName = new JLabel(temp.getName());
				JComboBox amount = new JComboBox(quantity);
				JButton addToCart = new JButton("Add to Cart");
				addToCart.addActionListener(new
						ActionListener(){
							public void actionPerformed(ActionEvent event){
								//get the amount that the drop-down box is set to
								int chosenQty = amount.getSelectedIndex();
								Product selected = inv.findProduct(temp);
								//get the current quantity of the product in inventory
								int productQty = selected.getQty();
								//set selected product quantity to selected amount
								selected.setQty(chosenQty);
								//and add it to the shopping cart
								cart.addProduct(selected);
								//set product inventory quantity to original amount minus selected amount
								//********
								// NOTE: this isn't working correctly at the moment; both "temp" and "selected"
								// are still references to the same object, so it's reverting to quantity of the
								// product in the cart to the same amount as the product in the inventory - Kris
								//********
								temp.setQty(productQty - chosenQty);
								//save changes to inventory
								inv.saveToDB();
								//update total of contents in cart on the view
								cartTotal.setText(Double.toString(cart.getTotal()));
							}
				});
				JPanel itemDisplay = new JPanel();
				itemDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemDisplay.setBackground(Color.WHITE);
				itemDisplay.setLayout(new FlowLayout());
				itemDisplay.add(productName);
				itemDisplay.add(amount);
				itemDisplay.add(addToCart);
				products.add(itemDisplay);
			}
		}
		return products;
	}
	
	//method to add a change listener to an object
	public void addChangeListener(ChangeListener listener) {
	    listenerList.add(ChangeListener.class, listener);
	}
	
	//method to iterate through all existing change listeners and notify them of a state change
	protected void fireStateChanged(ChangeEvent evt) {
	    ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
	    if (listeners != null && listeners.length > 0) {
	        for (ChangeListener listener : listeners) {
	            listener.stateChanged(evt);
	        }
	    }
	}
	
	private JLabel cartTotal;
}
