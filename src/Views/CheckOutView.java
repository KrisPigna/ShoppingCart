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
		// copy pasted from CustomerInventoryView, since gonna be similar, but still need to make lots of changes.
		JLabel total = new JLabel("Cart total: $");
		cartTotal = new JLabel("0");
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
		// TODO Auto-generated method stub
		return null;
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
