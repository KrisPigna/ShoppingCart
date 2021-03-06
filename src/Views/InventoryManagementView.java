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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.DiscountProduct;
import Inventory.GenericProduct;
import Inventory.Inventory;
import Inventory.Product;

public class InventoryManagementView extends JPanel {
	/**
	 * Constructor for the InventoryManagementView GUI
	 * @param inv The main inventory to modify or pull data from
	 */
	public InventoryManagementView(Inventory inv){
		JLabel subtitle = new JLabel("Inventory Management");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JButton logout = new JButton("Logout");
		logout.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event){
				ChangeEvent evt = new ChangeEvent(logout);
				fireStateChanged(evt);
			}
		});
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(subtitle);
		header.add(logout);
		JPanel products = buildInventoryList(inv);
		JScrollPane productScroll = new JScrollPane(products);
		productScroll.setPreferredSize(new Dimension(600, 400));
		JButton addProduct = new JButton("Add New Product");
		addProduct.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(addProduct);
						fireStateChanged(evt);
					}
		});
		JButton addBundle = new JButton("Add New Bundle");
		addBundle.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(addBundle);
						fireStateChanged(evt);
					}
		});
		JButton salesData = new JButton("Review Sales Data");
		salesData.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(salesData);
						fireStateChanged(evt);
					}
		});
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,3,5,20));
		buttons.add(addProduct);
		buttons.add(addBundle);
		buttons.add(salesData);
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(productScroll, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(600, 450));
		this.setVisible(false);	
	}
	
	/**
	 * Populates a JPanel with all the items in the inventory
	 * and returns that JPanel for adding into the InventoryManagementView
	 * @return JPanel with all inventory items added to it
	 */
	public JPanel buildInventoryList(Inventory inv){
		Iterator<GenericProduct> i = inv.getIterator();
		JPanel products = new JPanel();
		products.setLayout(new GridLayout(0,1,0,5));
		while(i.hasNext()) {
				final GenericProduct temp = i.next();
				JLabel productName = new JLabel(temp.getName());
				JButton edit = new JButton("Edit");
				edit.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(temp);
						fireStateChanged(evt);
					}
				});
				JButton remove = new JButton("Remove");
				remove.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						int confirmButton = JOptionPane.YES_NO_OPTION;
						int confirmResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to permanently remove this product from inventory?", "Confirm", confirmButton);
						if(confirmResult == 0) {
							inv.removeProduct(temp);
							inv.saveToDB();
							ChangeEvent evt = new ChangeEvent(remove);
							fireStateChanged(evt);
						}
					}
				});
				JButton discount = new JButton("Apply Discount");
				discount.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						Object[] options = {"5", "10", "15", "20", "25", "30", "40", "50", "60", "70", "80", "90", "100"};
						String s = (String)JOptionPane.showInputDialog(null, "Choose discount percentage: ", null, JOptionPane.PLAIN_MESSAGE, null, options, "5%");
						DiscountProduct disc = new DiscountProduct(temp, Double.parseDouble(s)/100);
						inv.replaceProduct(temp, disc);
						inv.saveToDB();
						ChangeEvent evt = new ChangeEvent(remove);
						fireStateChanged(evt);
					};
				});
				JPanel itemDisplay = new JPanel();
				itemDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				itemDisplay.setBackground(Color.WHITE);
				itemDisplay.setLayout(new FlowLayout());
				itemDisplay.repaint();
				itemDisplay.add(productName);
				itemDisplay.add(edit);
				itemDisplay.add(discount);
				itemDisplay.add(remove);
				products.add(itemDisplay);
			}
		return products;
	}
	
	/**
	 * Re-creates the view after a state-change to update its contents
	 * @param inv Inventory object to populate view and add products to
	 */
	public void updateView(Inventory inv) {
		this.removeAll();
		JLabel subtitle = new JLabel("Inventory Management");
		JLabel title = new JLabel("Shop-A-Tron 5000");
		title.setFont(new Font("Serif",  Font.BOLD, 30));
		JButton logout = new JButton("Logout");
		logout.addActionListener(new
				ActionListener(){
			public void actionPerformed(ActionEvent event){
				ChangeEvent evt = new ChangeEvent(logout);
				fireStateChanged(evt);
			}
		});
		JPanel header = new JPanel();
		header.setLayout(new FlowLayout());
		header.add(title);
		header.add(subtitle);
		header.add(logout);
		JPanel products = buildInventoryList(inv);
		JScrollPane productScroll = new JScrollPane(products);
		productScroll.setPreferredSize(new Dimension(600, 400));
		JButton addProduct = new JButton("Add New Product");
		addProduct.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(addProduct);
						fireStateChanged(evt);
					}
		});
		JButton addBundle = new JButton("Add New Bundle");
		addBundle.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(addBundle);
						fireStateChanged(evt);
					}
		});
		JButton salesData = new JButton("Review Sales Data");
		salesData.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(salesData);
						fireStateChanged(evt);
					}
		});
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,3,5,20));
		buttons.add(addProduct);
		buttons.add(addBundle);
		buttons.add(salesData);
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(productScroll, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(600, 450));
		this.setVisible(false);	
	}
	
	/**
	 * Method to add a change listener to an object
	 * @param listener The listener to be added
	 */
	public void addChangeListener(ChangeListener listener) {
	    listenerList.add(ChangeListener.class, listener);
	}
	
	/**
	 * Method to iterate through all existing change listeners and notify them of a state change
	 * @param evt The ChangeEvent that listeners are being notified of
	 */
	protected void fireStateChanged(ChangeEvent evt) {
	    ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
	    if (listeners != null && listeners.length > 0) {
	        for (ChangeListener listener : listeners) {
	            listener.stateChanged(evt);
	        }
	    }
	}

}