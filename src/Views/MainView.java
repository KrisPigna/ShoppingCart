package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.DiscountProduct;
import Inventory.GenericProduct;
import Inventory.Inventory;
import Inventory.Product;
import Inventory.ProductBundle;
import Models.ShoppingCart;

public class MainView extends JFrame {
	
	private LoginView loginView;
	private CreateAccountView createView;
	private CustomerInventoryView custInventoryView;
	private CheckOutView chkOutView;
	private CompleteOrderView compOrderView;
	private InventoryManagementView invMangView;
	private EditAddProductView addProductView;
	private SalesDataView salesDataView;
	private Inventory mainInventory;
	private ShoppingCart cart;
	
	/**
	 * Constructor for the MainView, which holds panels of all other views
	 */
	public MainView(){
		//create instances of models
		mainInventory = new Inventory();
		cart = new ShoppingCart();
		
		//create instances of every view
		loginView = new LoginView();
		createView = new CreateAccountView();
		custInventoryView = new CustomerInventoryView(mainInventory, cart);
		chkOutView = new CheckOutView(mainInventory, cart);
		compOrderView = new CompleteOrderView();
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
					custInventoryView.updateView(mainInventory, cart);
					loginView.setVisible(false);
					custInventoryView.setVisible(true);
				}
				if (temp == 2) {
					invMangView.updateView(mainInventory);
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
					btn = (JButton) event.getSource();
					if(btn.getText() == "Checkout") {
						custInventoryView.setVisible(false);
						chkOutView.updateView(mainInventory, cart);
						chkOutView.setVisible(true);
					}
					if (btn.getText() == "Logout"){
						custInventoryView.setVisible(false);
						loginView.setVisible(true);
					}
				}
				if (event.getSource().getClass() == label.getClass()) {
					label = (JLabel) event.getSource();
					label.setVisible(true);
				}
			}
		});
		
		chkOutView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton btn = new JButton();
				if (event.getSource().getClass() == btn.getClass()) {
					btn = (JButton) event.getSource();
					if (btn.getText() == "Remove"){
						chkOutView.updateView(mainInventory, cart);
						chkOutView.setVisible(true);
					}
					if (btn.getText() == "Back"){
						chkOutView.setVisible(false);
						custInventoryView.setVisible(true);
					}
					if (btn.getText() == "Submit Order"){
						chkOutView.setVisible(false);
						
						compOrderView.setVisible(true);
					}
				}
			}
		});
		
		compOrderView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton btn = new JButton();
				if (event.getSource().getClass() == btn.getClass()) {
					btn = (JButton) event.getSource();
					if (btn.getText() == "Shop Some More!"){
						compOrderView.setVisible(false);
						custInventoryView.updateView(mainInventory, cart);
						chkOutView.updateView(mainInventory, cart);
						custInventoryView.setVisible(true);
					}
				}
			}
		});
		
		invMangView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JButton btn = new JButton();
				Product prod = new Product();
				DiscountProduct discProd = new DiscountProduct(prod, 0);
				ProductBundle bundle = new ProductBundle();
				if (event.getSource().getClass() == btn.getClass()) {
					btn = (JButton) event.getSource();
					if (btn.getText() == "Add New Product"){
						addProductView.updateView(mainInventory);
						invMangView.setVisible(false);
						addProductView.setVisible(true);
					}
					if (btn.getText() == "Add New Bundle"){
						addProductView.updateView(mainInventory);
						addProductView.addBundle(mainInventory);
						invMangView.setVisible(false);
						addProductView.setVisible(true);
					}
					if (btn.getText() == "Review Sales Data"){
						salesDataView.updateView(mainInventory, cart);
						invMangView.setVisible(false);
						salesDataView.setVisible(true);
					}
					if (btn.getText() == "Remove"){
						invMangView.updateView(mainInventory);
						invMangView.setVisible(true);
					}
					if (btn.getText() == "Logout"){
						invMangView.setVisible(false);
						loginView.setVisible(true);
					}
				}
				if (event.getSource().getClass() == prod.getClass() || event.getSource().getClass() == discProd.getClass() || event.getSource().getClass() == bundle.getClass()) {
					GenericProduct temp = (GenericProduct) event.getSource();
					addProductView.updateView(mainInventory);
					addProductView.editProduct(mainInventory, temp);
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
				if (temp.getText() == "Create Bundle"){
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
		this.add(compOrderView);
		this.add(invMangView);
		this.add(addProductView);
		this.add(salesDataView);
		this.setPreferredSize(new Dimension(900, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	//Main function for launching view/application
	public static void main(String[] args){
		MainView main = new MainView();
	}
}