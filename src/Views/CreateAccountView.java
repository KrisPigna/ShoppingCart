package Views;

import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Database.AppendToDB;
import Models.Account;

public class CreateAccountView extends JPanel {
	public CreateAccountView(){
		final JTextField userField = new JTextField(10);
		final JTextField pwField = new JTextField(10);
		JLabel userLabel = new JLabel("Username:");
		JLabel pwLabel = new JLabel("Password: ");
		JButton create = new JButton("Create");
		create.addActionListener(new
				ActionListener(){
					public void actionPerformed(ActionEvent event){
						String user = userField.getText();
						String pw = pwField.getText();
						Account temp = new Account(user, pw);
						temp.createAccount();
						ChangeEvent evt = new ChangeEvent(event);
						fireStateChanged(evt);
					}
		});
		JPanel fields = new JPanel();
		fields.setLayout(new GridLayout(2,2));
		fields.add(userLabel);
		fields.add(userField);
		fields.add(pwLabel);
		fields.add(pwField);
		JPanel buttons = new JPanel();
		buttons.add(create);
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