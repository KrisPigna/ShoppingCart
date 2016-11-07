package Models;

import Views.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Account implements java.io.Serializable {
	public Account(){
		username = null;
		password = null;
	}
	
	public Account(String user, String pw){
		username = user;
		password = pw;
	}
	
	public void createAccount(){
		//TODO add save-to-DB functionality
		
		LoginView login = new LoginView();
		
		try { // attempting to implement "Java Serialization" for database
			FileOutputStream fileOut = new FileOutputStream("C:/Users/Paul/git/ShoppingCart/src/Models/Login_Credentials.ser");
			ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(this); // this is from "temp.createAccount()" call in CreateAccountView
			
			objOut.close();
			fileOut.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		
	}
		
	
	
	private String username;
	private String password;
}