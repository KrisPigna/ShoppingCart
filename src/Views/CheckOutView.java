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

	public CheckOutView(Inventory inv, ShoppingCart cart) {
		/* NOTE TO SELF: why is cart total now not displaying on CustomerInventoryView now that I've added it here? */	
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
	
	public void RefreshCheckOutView(Inventory inv, ShoppingCart cart) {
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
		JButton checkout = new JButton("Checkout");
		
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(productScroll, BorderLayout.CENTER);
		this.add(checkout, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(400, 450));
		this.setVisible(false);	
	}
	
	public static JPanel buildCartList(Inventory inv, ShoppingCart cart) {
		Iterator<Product> i = cart.getIterator();
		JPanel cartList = new JPanel();
		cartList.setLayout(new GridLayout(0,1,0,5));
		while(i.hasNext()) {
				final Product temp = i.next();
				JLabel productName = new JLabel(temp.getName());
//				JButton edit = new JButton("Edit");
//				edit.addActionListener(new
//				ActionListener(){
//					public void actionPerformed(ActionEvent event){
//						ChangeEvent evt = new ChangeEvent(temp);
//						fireStateChanged(evt);
//					}
//				});
				JPanel itemDisplay = new JPanel();
				itemDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemDisplay.setBackground(Color.WHITE);
				itemDisplay.setLayout(new FlowLayout());
				itemDisplay.repaint();
				itemDisplay.add(productName);
				//itemDisplay.add(edit);
				cartList.add(itemDisplay);
			}
		return cartList;
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
	
	public static void setCheckOutTotal(String key) {
		checkOutTotal.setText(key);
	}
	
	private static JLabel checkOutTotal;
}
