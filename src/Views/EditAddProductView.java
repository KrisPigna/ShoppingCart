package Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.Inventory;
import Inventory.Product;

public class EditAddProductView extends JPanel {
	public EditAddProductView(Inventory inv){
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
		JPanel fields = new JPanel();
		fields.setLayout(new SpringLayout());
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
		this.setLayout(new BorderLayout());
		this.add(fields, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.SOUTH);
		this.setVisible(false);
	}
	
	public void editProduct(Inventory inv, Product prod) {
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
		this.setLayout(new BorderLayout());
		this.add(fields, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.SOUTH);
		this.setVisible(false);
	}
	
	/**
	 * Re-creates the view after a state-change to update its contents
	 * @param inv Inventory object to add products to
	 */
	public void updateView(Inventory inv) {
		this.removeAll();
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
		this.setLayout(new BorderLayout());
		this.add(fields, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.SOUTH);
		this.setVisible(false);
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
