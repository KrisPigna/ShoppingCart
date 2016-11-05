package Views;

import java.awt.*;
import java.awt.event.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.*;

import Implementation.StateManager;

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
						state.createAccountView();
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
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(fields, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		//this.pack();
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
