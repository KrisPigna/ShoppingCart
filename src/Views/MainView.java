package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
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
			}
		});
		
		createView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				createView.setVisible(false);
				loginView.setVisible(true);
			}
		});
		
		custInventoryView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton temp = (JButton) event.getSource();
				if (temp.getText() == "Checkout"){
					custInventoryView.setVisible(false);
					chkOutView.setVisible(true);
				}
			}
		});
		
		invMangView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton temp = (JButton) event.getSource();
				if (temp.getText() == "Add New Product"){
					addProductView.updateView(mainInventory);
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
		this.setPreferredSize(new Dimension(900, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		
		MainView main = new MainView();
	       
		
		
		

	}
}
