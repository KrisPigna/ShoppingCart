package Views;

import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.Border;

import Models.Account;
import Database.AppendToDB;
import Inventory.Inventory;
import Inventory.Product; 


public class LoginView extends JFrame {
	public LoginView(){
		final JTextField userField = new JTextField(10);
		final JTextField pwField = new JTextField(10);
		JLabel userLabel = new JLabel("Username:");
		JLabel pwLabel = new JLabel("Password: ");
		JButton login = new JButton("Login");
		JButton createAcct = new JButton("Create Account");
		createAcct.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						dispose();
						CreateAccountView create = new CreateAccountView();
					}
		});
		
		login.addActionListener(new
				ActionListener() {
					public void actionPerformed(ActionEvent event) {
						
						ArrayList<Account> checkDB = new ArrayList<Account>();
						
						try { // Insert your own directory to avoid errors. Filename extension must be .ser
							FileInputStream file_in = new FileInputStream("/Users/Mario/git/ShoppingCart/Login_Credentials.ser");
							ObjectInputStream obj_in = new ObjectInputStream(file_in);
							Account temp = (Account) obj_in.readObject();
							
							try {
								while(temp != null) {
									checkDB.add(temp);
									temp = (Account) obj_in.readObject();
								}
							} catch (EOFException e) {
								file_in.close();
								obj_in.close();
							} 
							
							Account checkLogin = new Account(userField.getText(), pwField.getText());
							
							for(Iterator<Account> it = checkDB.iterator(); it.hasNext();) {
								Account account = it.next();

								if(checkLogin.equals(account)) { // Login credentials verified, proceed to next view
									// For testing purposes throughout development, 
									// these two print statements should always give same number
									// if the objects are equals.
									//System.out.println("HashCode: " + checkLogin.hashCode());
									//System.out.println("HashCode: " + account.hashCode());
								}
								else{
									// Login failed
								}
							}
							
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} 
						
						
					}
		});
		
		JPanel fields = new JPanel();
		SpringLayout layout = new SpringLayout();
		fields.setLayout(layout);
		fields.add(userLabel);
		fields.add(userField);
		fields.add(pwLabel);
		fields.add(pwField);
		layout.putConstraint(SpringLayout.WEST, userLabel,
                5, SpringLayout.WEST, fields);
		layout.putConstraint(SpringLayout.NORTH, userLabel,
                5, SpringLayout.NORTH, fields);
		layout.putConstraint(SpringLayout.WEST, userField,
                5,
                SpringLayout.EAST, userLabel);
		layout.putConstraint(SpringLayout.NORTH, userField,
                5,
                SpringLayout.NORTH, fields);
		layout.putConstraint(SpringLayout.WEST, pwLabel,
                5, SpringLayout.WEST, fields);
		layout.putConstraint(SpringLayout.NORTH, pwLabel,
                20, SpringLayout.NORTH, userLabel);
		layout.putConstraint(SpringLayout.WEST, pwField,
                5,
                SpringLayout.EAST, pwLabel);
		layout.putConstraint(SpringLayout.NORTH, pwField,
                20,
                SpringLayout.NORTH, userField);
		fields.setPreferredSize(new Dimension(400,50));
		fields.setMaximumSize(new Dimension(400,50));
		fields.setBorder(BorderFactory.createEmptyBorder());
		JPanel buttons = new JPanel();
		buttons.add(login);
		buttons.add(createAcct);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fields, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}
	
	
	public static void main(String[] args){
		
		LoginView login = new LoginView();
		
		Inventory mainInventory = new Inventory();
	       
	    

	}
}