package Implementation;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Views.*;

public class StateManager {
	public StateManager(){
		login = new LoginView(this);
		createAcct = new CreateAccountView(this);
		main = new JFrame();
		main.setLayout(new BorderLayout());
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.add(login);
		//main.add(createAcct);
		main.pack();
		main.setVisible(true);
	};
	
	public void loginView(){
		main.remove(createAcct);
		main.add(login);
		main.pack();
	}
	public void createAccountView(){
		main.remove(0);
		main.add(createAcct);
		main.invalidate();
		main.validate();
		main.pack();
	}
	
	public static void main(String[] args){
		StateManager state = new StateManager();
		//state.loginView();
	}
	
	private LoginView login;
	private CreateAccountView createAcct;
	private JFrame main;
}
