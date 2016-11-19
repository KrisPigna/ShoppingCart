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
		
		//add change listeners to each view
		loginView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				//determines which button was clicked, for event branching
				JButton temp = (JButton) event.getSource();
				if (temp.getText() == "Create Account") {
					loginView.setVisible(false);
					createView.setVisible(true);
				}
				if (temp.getText() == "Login") {
					loginView.setVisible(false);
					custInventoryView.setVisible(true);
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
				if (temp.getText() == "Add to Cart"){
					
				}
			}
		});
		
		//Set layout and add each view to MainView frame; only LoginView is visible at first
		this.setLayout(new FlowLayout());
		this.add(loginView);
		this.add(createView);
		this.add(custInventoryView);
		this.setPreferredSize(new Dimension(900, 600));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		
		MainView main = new MainView();
	       
		
		
		

	}
}
