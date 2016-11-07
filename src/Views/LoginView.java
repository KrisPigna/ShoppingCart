package Views;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;

import Models.Account;

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
		
						try { // Insert your own directory to avoid errors.
							FileInputStream file_in = new FileInputStream("C:/Users/Lectora Desktop/git/ShoppingCart/Login_Credentials.ser");
							ObjectInputStream obj_in = new ObjectInputStream(file_in);
							
							Account checkLogin = (Account) obj_in.readObject();
							
							if(checkLogin.getUsername() == userField.getText()) {
								System.out.println("We're onto something...");
							}
							else
								System.out.println("Something is wrong...");
							
							file_in.close();
							obj_in.close();
							
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