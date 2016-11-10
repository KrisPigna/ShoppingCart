package Views;

import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import javax.swing.*;

import Database.AppendToDB;
import Models.Account;

public class CreateAccountView extends JFrame {
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
						dispose();
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fields, BorderLayout.NORTH);
		this.add(buttons, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}
	
}