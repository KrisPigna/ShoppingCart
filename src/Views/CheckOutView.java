package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.Inventory;
import Inventory.Product;
import Models.ShoppingCart;

public class CheckOutView extends JPanel {

	public CheckOutView(Inventory inv, ShoppingCart cart, JLabel cartTotal) {
		/* NOTE TO SELF: why is cart total now not displaying when adding items to cart? */
		JLabel total = new JLabel("Cart total: $");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(total);
		header.add(cartTotal);
		JPanel products = buildCartList(inv, cart);
		JScrollPane productScroll = new JScrollPane(products);
		productScroll.setPreferredSize(new Dimension(300, 400));
		JButton checkout = new JButton("Checkout");
		
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(productScroll, BorderLayout.CENTER);
		this.add(checkout, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(400, 450));
		this.setVisible(false);	
	}
	
	private JPanel buildCartList(Inventory inv, ShoppingCart cart) {
		Iterator<Product> i = cart.getIterator();
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
				JLabel price = new JLabel("$" + Double.toString(temp.getSellPrice()));
				JLabel productName = new JLabel(temp.getName());
				JLabel description = new JLabel(temp.getDescription());
				description.setVisible(false);
				JComboBox amount = new JComboBox(quantity);
				JButton viewDetails = new JButton("View Details");
				viewDetails.addActionListener(new
						ActionListener(){
							public void actionPerformed(ActionEvent event){
								ChangeEvent evt = new ChangeEvent(description);
								fireStateChanged(evt);
							}
				});
				JButton addToCart = new JButton("Add to Cart");
				addToCart.addActionListener(new
						ActionListener(){
							public void actionPerformed(ActionEvent event){
								//get the amount that the drop-down box is set to
								int chosenQty = amount.getSelectedIndex();

								if(amount.getSelectedIndex() != 0) {
									Product selected = inv.findProduct(temp);
									//get the current quantity of the product in inventory
									int productQty = selected.getQty();
									//set selected product quantity to selected amount
									selected.setQty(chosenQty);
									//and add it to the shopping cart
									cart.addProduct(selected);
									temp.setQty(productQty - chosenQty);
									//save changes to inventory
									inv.saveToDB();
									//update total of contents in cart on the view
									cartTotal.setText(Double.toString(cart.getSellTotal()));
									inv.updateQty(selected, (inv.findProduct(temp).getQty() - selected.getQty()));
									System.out.println("after: " + inv.findProduct(temp).getQty());
								}
							}
				});
				
				JPanel itemDisplay = new JPanel();
				itemDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemDisplay.setBackground(Color.WHITE);
				itemDisplay.setLayout(new FlowLayout());
				itemDisplay.add(productName);
				itemDisplay.add(price);
				itemDisplay.add(amount);
				itemDisplay.add(viewDetails);
				itemDisplay.add(addToCart);
				itemDisplay.add(description);
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
