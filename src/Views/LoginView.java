package Views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Models.Account; 


public class LoginView extends JPanel {
	/**
	 * Constructor for LoginView GUI.
	 */
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
						//if loginAccount() returns 1, login was successful for customer type; if 2,
						//successful for seller type; if 0, login failed
						if (checkLogin.loginAccount() == 1){
							ChangeEvent evt = new ChangeEvent(1);
							fireStateChanged(evt);
						}
						if (checkLogin.loginAccount() == 2){
							ChangeEvent evt = new ChangeEvent(2);
							fireStateChanged(evt);
						}
						if (checkLogin.loginAccount() == 0){
							ChangeEvent evt = new ChangeEvent(0);
							fireStateChanged(evt);
						}
					}
		});
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, 1));
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
		center.add(fields);
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	/**
	 * Method for updating view based on user interaction
	 * @param fail if this is true, method makes visible a panel that informs
	 * the user their login attempt was unsuccessful
	 */
	public void updateView(boolean fail) {
		this.removeAll();
		final JTextField userField = new JTextField(10);
		final JTextField pwField = new JTextField(10);
		JLabel failed = new JLabel("Username or password incorrect. Please try again.");
		failed.setForeground(Color.RED);
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
						//if loginAccount() returns 1, login was successful for customer type; if 2,
						//successful for seller type; if 0, login failed
						if (checkLogin.loginAccount() == 1){
							ChangeEvent evt = new ChangeEvent(1);
							fireStateChanged(evt);
						}
						if (checkLogin.loginAccount() == 2){
							ChangeEvent evt = new ChangeEvent(2);
							fireStateChanged(evt);
						}
						if (checkLogin.loginAccount() == 0){
							ChangeEvent evt = new ChangeEvent(0);
							fireStateChanged(evt);
						}
					}
		});
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, 1));
		if (fail == true) {
			center.add(failed);
		}
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
		center.add(fields);
		this.setLayout(new BorderLayout());
		this.add(header, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(buttons, BorderLayout.SOUTH);
		this.setVisible(false);
	}
	
	/**
	 * Method to add a change listener to an object
	 * @param listener The listener to be added
	 */
	public void addChangeListener(ChangeListener listener) {
	    listenerList.add(ChangeListener.class, listener);
	}
	
	/**
	 * Method to iterate through all existing change listeners and notify them of a state change
	 * @param evt The ChangeEvent that listeners are being notified of
	 */
	protected void fireStateChanged(ChangeEvent evt) {
	    ChangeListener[] listeners = listenerList.getListeners(ChangeListener.class);
	    if (listeners != null && listeners.length > 0) {
	        for (ChangeListener listener : listeners) {
	            listener.stateChanged(evt);
	        }
	    }
	}

}