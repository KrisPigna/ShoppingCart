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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Models.Account;
import Database.AppendToDB;
import Inventory.Inventory;
import Inventory.Product; 


public class LoginView extends JPanel {
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
						ChangeEvent evt = new ChangeEvent(3);
						fireStateChanged(evt);
					}
		});
		
		login.addActionListener(new
				ActionListener() {
					public void actionPerformed(ActionEvent event) {
						Account checkLogin = new Account(userField.getText(), pwField.getText());
						//if loginAccount() returns true, login was successful and fireStateChanged called
						if (checkLogin.loginAccount() == 1){
							ChangeEvent evt = new ChangeEvent(1);
							fireStateChanged(evt);
						}
						if (checkLogin.loginAccount() == 2){
							ChangeEvent evt = new ChangeEvent(2);
							fireStateChanged(evt);
						}
					}
		});
		
		JPanel fields = new JPanel();
		fields.setLayout(new GridLayout(2,2));
		fields.add(userLabel);
		fields.add(userField);
		fields.add(pwLabel);
		fields.add(pwField);
		fields.setPreferredSize(new Dimension(400,50));
		fields.setMaximumSize(new Dimension(400,50));
		JPanel buttons = new JPanel();
		buttons.add(login);
		buttons.add(createAcct);
		JLabel header = new JLabel("Shop-A-Tron 5000");
		header.setFont(new Font("Serif", Font.BOLD, 30));
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(fields, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.setVisible(true);
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