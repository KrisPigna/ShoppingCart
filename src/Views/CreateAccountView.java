package Views;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import javax.swing.*;

import javax.swing.JFrame;

import Models.Account;

public class CreateAccountView extends JFrame /*implements LayoutManager*/ {
	public CreateAccountView(){
		final JTextArea userField = new JTextArea(1,10);
		final JTextArea pwField = new JTextArea(1,10);
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
		fields.add(userField);
		fields.add(pwField);
		JPanel buttons = new JPanel();
		buttons.add(create);
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
}