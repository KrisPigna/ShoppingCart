package Models;

import Views.*;
import Database.AppendToDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Account implements java.io.Serializable {
	
	public Account(){
		username = null;
		password = null;
	}
	
	public Account(String user, String pw){
		username = user;
		password = pw;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void createAccount(){
		//TODO add save-to-DB functionality
		
		LoginView login = new LoginView();
		
		try { // attempting to implement "Java Serialization" for database. Insert your own directory to avoid errors.
			File path = new File("C:/Users/Lectora Desktop/git/ShoppingCart/Login_Credentials.ser");
			
			if(!path.exists()) {
				FileOutputStream fileOut = new FileOutputStream(path, true);
				ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
				objOut.writeObject(this);
				objOut.close();
				fileOut.close();
			}
			else {
				FileOutputStream fileOut = new FileOutputStream(path, true);
				AppendToDB objOut = new AppendToDB(fileOut);
				objOut.writeObject(this); 
			}
			
			 
			
			
		}
		catch(IOException i) {
			i.printStackTrace();
		}
	}
		
	private static final long serialVersionUID = 6622568068083351485L;
	private String username;
	private String password;
}