package Views;

import java.awt.*;
import java.awt.event.*;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.*;

import Models.Account;
import Database.AppendToDB;

public class LoginView extends JFrame /*implements LayoutManager*/ {
	public LoginView(){
		final JTextArea userField = new JTextArea(1,10);
		final JTextArea pwField = new JTextArea(1,10);
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
						
						try { // Insert your own directory to avoid errors.
							FileInputStream file_in = new FileInputStream("C:/Users/Lectora Desktop/git/ShoppingCart/Login_Credentials.ser");
							ObjectInputStream obj_in = new ObjectInputStream(file_in);
							Account temp = (Account) obj_in.readObject();
							
							try {
								while(temp != null) {
									checkDB.add(temp); System.out.println("Add called.");
									temp = (Account) obj_in.readObject();
								}
							} catch (EOFException e) {
								file_in.close();
								obj_in.close();
							} 
							
							Account checkLogin = new Account(userField.getText(), pwField.getText());
							
							for(int i = 0; i < checkDB.size(); i++) {
								System.out.println("Data: " +  checkDB.get(i));
								System.out.println("checkDB Size: " + checkDB.size());
							}
							
							for(Iterator<Account> it = checkDB.iterator(); it.hasNext();) {
								System.out.println("Inside for loop for iterator...");
								Account account = it.next();
								System.out.println("checkLogin class: " + checkLogin.getUsername().getClass().toString());
								System.out.println("DB Account class: " + account.getUsername().getClass());
								
								String un = account.getUsername();
								
								if(checkLogin.getUsername() == un) {
									System.out.println("Good.");
								}
								else{
									System.out.println("Not... good...");
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
		//fields.setLayout(new GridLayout(2,2));
		fields.add(userLabel);
		fields.add(userField);
		fields.add(pwLabel);
		fields.add(pwField);
		JPanel buttons = new JPanel();
		buttons.add(login);
		buttons.add(createAcct);
		//JFrame frame = new JFrame();
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fields, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}
	
	public void Notify(){
		this.setVisible(true);
	}

	/*@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void layoutContainer(Container arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub

	}*/
	
	public static void main(String[] args){
		
		LoginView login = new LoginView();

	}
}