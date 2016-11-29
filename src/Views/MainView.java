package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.Inventory;
import Inventory.Product;
import Models.ShoppingCart;

public class MainView extends JFrame {
	
	private LoginView loginView;
	private CreateAccountView createView;
	private CustomerInventoryView custInventoryView;
	private CheckOutView chkOutView;
	private InventoryManagementView invMangView;
	private EditAddProductView addProductView;
	private SalesDataView salesDataView;
	private Inventory mainInventory;
	private ShoppingCart cart;
	
	
	public MainView(){
		//create instances of models
		mainInventory = new Inventory();
		cart = new ShoppingCart();
		
		//create instances of every view
		loginView = new LoginView();
		createView = new CreateAccountView();
		custInventoryView = new CustomerInventoryView(mainInventory, cart);
		chkOutView = new CheckOutView(mainInventory, cart);
		invMangView = new InventoryManagementView(mainInventory);
		addProductView = new EditAddProductView(mainInventory);
		salesDataView = new SalesDataView(mainInventory, cart);
		
		//add change listeners to each view
		loginView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				// determines which button was clicked, for event branching, and
				// whether a successful login was customer-type or seller-type
				int temp = (int) event.getSource();
				if (temp == 3) {
					loginView.setVisible(false);
					createView.setVisible(true);
				}
				if (temp == 1) {
					loginView.setVisible(false);
					custInventoryView.setVisible(true);
				}
				if (temp == 2) {
					loginView.setVisible(false);
					invMangView.setVisible(true);
				}
				if (temp == 0) {
					boolean failed = true;
					loginView.updateView(failed);
					loginView.setVisible(true);
				}
			}
		});
		
		createView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				int success = (int) event.getSource();
				if (success == 1) {
					createView.setVisible(false);
					loginView.setVisible(true);
				}
				if (success == 0) {
					boolean failed = true;
					createView.updateView(failed);
					createView.setVisible(true);
				}
			}
		});
		
		custInventoryView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton btn = new JButton();
				JLabel label = new JLabel();
				if (event.getSource().getClass() == btn.getClass()) {
					custInventoryView.setVisible(false);
					chkOutView.RefreshCheckOutView(mainInventory, cart);
					chkOutView.setVisible(true);
				}
				if (event.getSource().getClass() == label.getClass()) {
					label = (JLabel) event.getSource();
					label.setVisible(true);
				}
			}
		});
		
		invMangView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton btn = new JButton();
				Product prod = new Product();
				if (event.getSource().getClass() == btn.getClass()) {
					btn = (JButton) event.getSource();
					if (btn.getText() == "Add New Product"){
						addProductView.updateView(mainInventory);
						invMangView.setVisible(false);
						addProductView.setVisible(true);
					}
					if (btn.getText() == "Review Sales Data"){
						salesDataView.updateView(mainInventory, cart);
						invMangView.setVisible(false);
						salesDataView.setVisible(true);
					}
				}
				if (event.getSource().getClass() == prod.getClass()) {
					prod = (Product) event.getSource();
					addProductView.editProduct(mainInventory, prod);
					invMangView.setVisible(false);
					addProductView.setVisible(true);
				}
			}
		});
		
		addProductView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton temp = (JButton) event.getSource();
				if (temp.getText() == "Create Product"){
					invMangView.updateView(mainInventory);
					addProductView.setVisible(false);
					invMangView.setVisible(true);
				}
				if (temp.getText() == "Save Product"){
					invMangView.updateView(mainInventory);
					addProductView.setVisible(false);
					invMangView.setVisible(true);
				}
			}
		});
		
		salesDataView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton temp = (JButton) event.getSource();
				if (temp.getText() == "Inventory Management"){
					invMangView.updateView(mainInventory);
					salesDataView.setVisible(false);
					invMangView.setVisible(true);
				}
			}
		});
		
		//Set layout and add each view to MainView frame; only LoginView is visible at first
		this.setLayout(new FlowLayout());
		this.add(loginView);
		this.add(createView);
		this.add(custInventoryView);
		this.add(chkOutView);
		this.add(invMangView);
		this.add(addProductView);
		this.add(salesDataView);
		this.setPreferredSize(new Dimension(900, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		
		MainView main = new MainView();
	       
		
		
		

	}
}