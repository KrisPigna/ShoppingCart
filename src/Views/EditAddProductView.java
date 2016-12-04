package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.GenericProduct;
import Inventory.Inventory;
import Inventory.Product;
import Inventory.ProductBundle;

public class EditAddProductView extends JPanel {
	public EditAddProductView(Inventory inv){
		JPanel addPanel = buildAddPanel(inv);
		this.setLayout(new BorderLayout());
		this.add(addPanel, BorderLayout.CENTER);
		this.setVisible(false);
	}
	
public void addBundle(Inventory inv) {
	this.removeAll();
	JPanel addBundle = buildBundlePanel(inv);
	this.setLayout(new BorderLayout());
	this.add(addBundle, BorderLayout.CENTER);
	this.setVisible(false);
}
	
	public void editProduct(Inventory inv, GenericProduct prod) {
		final JTextField name = new JTextField(prod.getName());
		final JTextField quantity = new JTextField(Integer.toString(prod.getQty()));
		final JTextField sellPrice = new JTextField(Double.toString(prod.getSellPrice()));
		final JTextField wholePrice = new JTextField(Double.toString(prod.getWholesalePrice()));
		final JTextField details = new JTextField(prod.getDescription());
		JLabel nameLabel = new JLabel("Name: ");
		JLabel qtyLabel = new JLabel("Quantity: ");
		JLabel sellLabel = new JLabel("Selling Price: ");
		JLabel wholeLabel = new JLabel("Wholesale Price: ");
		JLabel detailsLabel = new JLabel("Description: ");
		JButton saveProd = new JButton("Save Product");
		saveProd.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						prod.updateProduct(
								name.getText(), 
								Integer.parseInt(quantity.getText()),
								Double.parseDouble(sellPrice.getText()),
								Double.parseDouble(wholePrice.getText()),
								details.getText());
						inv.saveToDB();
						ChangeEvent evt = new ChangeEvent(saveProd);
						fireStateChanged(evt);
					}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(saveProd);
						fireStateChanged(evt);
					}
		});
		JPanel fields = new JPanel();
		fields.setLayout(new GridLayout(5,2));
		fields.add(nameLabel);
		fields.add(name);
		fields.add(qtyLabel);
		fields.add(quantity);
		fields.add(sellLabel);
		fields.add(sellPrice);
		fields.add(wholeLabel);
		fields.add(wholePrice);
		fields.add(detailsLabel);
		fields.add(details);
		JPanel buttons = new JPanel();
		buttons.add(saveProd);
		buttons.add(cancel);
		this.setLayout(new BorderLayout());
		this.add(fields, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.setVisible(false);
	}
	
	/**
	 * Re-creates the view after a state-change to update its contents
	 * @param inv Inventory object to add products to
	 */
	public void updateView(Inventory inv) {
		this.removeAll();
		JPanel addPanel = buildAddPanel(inv);
		this.setLayout(new BorderLayout());
		this.add(addPanel, BorderLayout.CENTER);
		this.setVisible(false);
	}
	
	public JPanel buildAddPanel(Inventory inv) {
		final JTextField name = new JTextField(10);
		final JTextField quantity = new JTextField(10);
		final JTextField sellPrice = new JTextField(10);
		final JTextField wholePrice = new JTextField(10);
		final JTextField details = new JTextField(10);
		JLabel nameLabel = new JLabel("Name: ");
		JLabel qtyLabel = new JLabel("Quantity: ");
		JLabel sellLabel = new JLabel("Selling Price: ");
		JLabel wholeLabel = new JLabel("Wholesale Price: ");
		JLabel detailsLabel = new JLabel("Description: ");
		JButton createProd = new JButton("Create Product");
		createProd.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						Product newProd = new Product(
								name.getText(),
								Integer.parseInt(quantity.getText()),
								Double.parseDouble(sellPrice.getText()),
								Double.parseDouble(wholePrice.getText()),
								details.getText());
						inv.addProduct(newProd);
						inv.saveToDB();
						ChangeEvent evt = new ChangeEvent(createProd);
						fireStateChanged(evt);
					}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(createProd);
						fireStateChanged(evt);
					}
		});
		JPanel fields = new JPanel();
		fields.setLayout(new GridLayout(5,2));
		fields.add(nameLabel);
		fields.add(name);
		fields.add(qtyLabel);
		fields.add(quantity);
		fields.add(sellLabel);
		fields.add(sellPrice);
		fields.add(wholeLabel);
		fields.add(wholePrice);
		fields.add(detailsLabel);
		fields.add(details);
		JPanel buttons = new JPanel();
		buttons.add(createProd);
		buttons.add(cancel);
		JPanel addPanel = new JPanel();
		addPanel.setLayout(new BorderLayout());
		addPanel.add(fields, BorderLayout.CENTER);
		addPanel.add(buttons, BorderLayout.SOUTH);
		return addPanel;
	}
	
	public JPanel buildBundlePanel(Inventory inv) {
		final JTextField name = new JTextField(10);
		final JTextField quantity = new JTextField(10);
		JLabel nameLabel = new JLabel("Name: ");
		JLabel qtyLabel = new JLabel("Quantity: ");
		ProductBundle bundle = new ProductBundle();
		Iterator<GenericProduct> i = inv.getIterator();
		JPanel products = new JPanel();
		products.setLayout(new GridLayout(0,1,0,5));
		while(i.hasNext()) {
			final GenericProduct temp = i.next();
			JLabel prodName = new JLabel(temp.getName());
			JButton addProd = new JButton("Add to Bundle");
			addProd.addActionListener(new
					ActionListener(){
						public void actionPerformed(ActionEvent event){
							try {
								GenericProduct selected = inv.findProduct(temp);
								bundle.addProduct(selected);
								addProd.setEnabled(false);
								addProd.setText("Added!");
							} catch (CloneNotSupportedException e) {
								e.printStackTrace();
							}
						}
			});
			JPanel itemDisplay = new JPanel();
			itemDisplay.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			itemDisplay.setBackground(Color.WHITE);
			itemDisplay.setLayout(new BorderLayout(10, 10));
			itemDisplay.add(prodName, BorderLayout.LINE_START);
			itemDisplay.add(addProd, BorderLayout.LINE_END);
			products.add(itemDisplay);
		}
		JScrollPane productScroll = new JScrollPane(products);
		productScroll.setPreferredSize(new Dimension(300, 400));
		JButton createBundle = new JButton("Create Bundle");
		createBundle.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						if (bundle.getSize() > 1) {
							bundle.setName(name.getText());
							bundle.setQty(Integer.parseInt(quantity.getText()));
							inv.addProduct(bundle);
							inv.saveToDB();
							ChangeEvent evt = new ChangeEvent(createBundle);
							fireStateChanged(evt);
						}
					}
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						ChangeEvent evt = new ChangeEvent(createBundle);
						fireStateChanged(evt);
					}
		});
		JPanel fields = new JPanel();
		fields.setLayout(new GridLayout(5,2));
		fields.add(nameLabel);
		fields.add(name);
		fields.add(qtyLabel);
		fields.add(quantity);
		JPanel buttons = new JPanel();
		buttons.add(createBundle);
		buttons.add(cancel);
		JPanel addBundle = new JPanel();
		addBundle.setLayout(new BorderLayout());
		addBundle.add(fields, BorderLayout.NORTH);
		addBundle.add(productScroll, BorderLayout.CENTER);
		addBundle.add(buttons, BorderLayout.SOUTH);
		return addBundle;
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
}