package Models;

import Views.*;
import Database.AppendToDB;

import java.awt.Rectangle;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

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
		
		try { // Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("C:/Users/Mario/git/ShoppingCart/Login_Credentials.ser");
			
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
	
	public boolean loginAccount(){
		ArrayList<Account> checkDB = new ArrayList<Account>();
		boolean success = false;
		try { // Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("C:/Users/Mario/git/ShoppingCart/Login_Credentials.ser");
			ObjectInputStream obj_in = new ObjectInputStream(file_in);
			Account temp = (Account) obj_in.readObject();
			
			try {
				while(temp != null) {
					checkDB.add(temp);
					temp = (Account) obj_in.readObject();
				}
			} catch (EOFException e) {
				file_in.close();
				obj_in.close();
			}
			
			for(Iterator<Account> it = checkDB.iterator(); it.hasNext();) {
				Account account = it.next();

				if(this.equals(account)) { // Login credentials verified, returns true and proceed to next view
					// For testing purposes throughout development, 
					// these two print statements should always give same number
					// if the objects are equals.
					//System.out.println("HashCode: " + this.hashCode());
					//System.out.println("HashCode: " + account.hashCode());
					success = true;
				}
				else{ //login failed, returns false
					success = false;
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return success;
	}
		
	private static final long serialVersionUID = 6622568068083351485L;
	private String username;
	private String password;
}