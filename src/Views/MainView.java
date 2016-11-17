package Views;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Inventory.Inventory;
import Inventory.Product;

public class MainView extends JFrame {
	
	private LoginView loginView;
	private CreateAccountView createView;
	private CustomerInventoryView inventoryView;
	
	public MainView(){
		
		//create instances of every view
		loginView = new LoginView();
		createView = new CreateAccountView();
		inventoryView = new CustomerInventoryView();
		
		//add change listeners to each view
		loginView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				//determines which button was clicked, for event branching
				JButton temp = (JButton) event.getSource();
				if(temp.getText() == "Create Account") {
					loginView.setVisible(false);
					createView.setVisible(true);
				}
				if(temp.getText() == "Login") {
					loginView.setVisible(false);
					inventoryView.setVisible(true);
				}
			}
		});
		
		createView.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				createView.setVisible(false);
				loginView.setVisible(true);
			}
		});
		
		//Set layout and add each view to MainView frame; only LoginView is visible at first
		this.setLayout(new FlowLayout());
		this.add(loginView);
		this.add(createView);
		this.add(inventoryView);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		MainView main = new MainView();
	       
		
		
		

	}
}
