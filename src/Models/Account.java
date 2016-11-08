package Models;

import Views.*;
import Database.AppendToDB;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public void createAccount(){
		LoginView login = new LoginView();
		
		try { // Insert your own directory to avoid errors. Filename extension must be .ser
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
				objOut.close();
				fileOut.close();
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