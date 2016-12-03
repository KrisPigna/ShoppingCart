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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.GenericProduct;
import Inventory.Inventory;
import Models.ShoppingCart;
/**
 * View class handles UI for a Check Out window.
 * <p>Acts as display for items in user cart, allowing them to submit order or remove items.</p>
 * @author Paul
 */
public class CheckOutView extends JPanel {
	/**
	 * Constructor for the view
	 * @param inv
	 * @param cart
	 */
	public CheckOutView(Inventory inv, ShoppingCart cart) {
		JLabel total = new JLabel("Cart total: $");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		checkOutTotal = new JLabel("0");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(total);
		header.add(checkOutTotal);
		JPanel cartList = buildCartList(inv, cart);
		JScrollPane productScroll = new JScrollPane(cartList);
		productScroll.setPreferredSize(new Dimension(300, 400));
		JButton checkout = new JButton("Checkout");

		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(productScroll, BorderLayout.CENTER);
		this.add(checkout, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(400, 450));
		this.setVisible(false);	
	}
	/**
	 * Method to reconstruct the view when dynamic data has changed.
	 * <p>A JButton "submitOrder" includes functionality to reset the checkOutTotal JLabel to zero
	 * and initiate a state changed event to switch views to CompleteOrderView.</p>
	 * @param inv
	 * @param cart
	 */
	public void RefreshCheckOutView(Inventory inv, ShoppingCart cart) {
		this.removeAll();
		JLabel total = new JLabel("Cart total: $");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(total);
		header.add(checkOutTotal);
		JPanel cartList = buildCartList(inv, cart);
		JScrollPane productScroll = new JScrollPane(cartList);
		productScroll.setPreferredSize(new Dimension(300, 400));
		JButton submitOrder = new JButton("Submit Order");
		submitOrder.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						editCheckOutTotal(checkOutTotal.getText(), Double.parseDouble( checkOutTotal.getText()));
						ChangeEvent evt = new ChangeEvent(submitOrder);
						fireStateChanged(evt);
					}
		});
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(productScroll, BorderLayout.CENTER);
		this.add(submitOrder, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(400, 450));
		this.setVisible(false);	
	}
	/**
	 * Iterates through the items in the cart and puts them on display in a JPanel.
	 * <p>A JButton "remove" includes functionality to remove items from customer cart and put them back in inventory.</p>
	 * @param inv
	 * @param cart
	 * @return cartList
	 */
	public JPanel buildCartList(Inventory inv, ShoppingCart cart) {
		Iterator<GenericProduct> i = cart.getIterator();
		JPanel cartList = new JPanel();
		cartList.setLayout(new GridLayout(0,1,0,5));
		while(i.hasNext()) {
				final GenericProduct temp = i.next();
				JLabel productName = new JLabel(temp.getName());
				JLabel qty = new JLabel(" -  Qty: ");
				JLabel qtyOf = new JLabel(Integer.toString(temp.getQty()));
				JButton remove = new JButton("Remove");
				JPanel itemDisplay = new JPanel();
				
				remove.addActionListener(new
						ActionListener(){
							public void actionPerformed(ActionEvent event){
								cart.removeProduct(temp);
								inv.updateQty(inv.findActualProduct(temp), inv.findActualProduct(temp).getQty() + temp.getQty());
								inv.saveToDB(); 
								editCheckOutTotal(checkOutTotal.getText(), temp.getQty() * temp.getSellPrice());
								ChangeEvent evt = new ChangeEvent(remove);
								fireStateChanged(evt);
							}
				});
				
				itemDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemDisplay.setBackground(Color.WHITE);
				itemDisplay.setLayout(new FlowLayout());
				itemDisplay.add(productName);
				itemDisplay.add(qty);
				itemDisplay.add(qtyOf);
				itemDisplay.add(remove);
				itemDisplay.repaint();
				cartList.add(itemDisplay);
		}
		
		return cartList;
	}

	/**
	 * Method to add a change listener to an object
	 * @param listener
	 */
	public void addChangeListener(ChangeListener listener) {
	    listenerList.add(ChangeListener.class, listener);
	}
	
	/**
	 * Method to iterate through all existing change listeners and notify them of a state change
	 * @param evt
	 */
	protected void fireStateChanged(ChangeEvent evt) {
	    ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
	    if (listeners != null && listeners.length > 0) {
	        for (ChangeListener listener : listeners) {
	            listener.stateChanged(evt);
	        }
	    }
	}
	/**
	 * Method to update the instance JLabel to display a new given String value.
	 * @param key
	 */
	public static void setCheckOutTotal(String key) {
		checkOutTotal.setText(key);
	}
	/**
	 * Method to update the instance JLabel to display a new String value.
	 * <p>New value based on the difference of the current value and the parameter subtract.</p>
	 * @param current
	 * @param subtract
	 */
	public static void editCheckOutTotal(String current, Double subtract) {
		Double total = Double.parseDouble(current);
		Double newTotal = total - subtract;
		String setTotal = Double.toString(newTotal);
		setCheckOutTotal(setTotal);
	}
	
	private static JLabel checkOutTotal;
}