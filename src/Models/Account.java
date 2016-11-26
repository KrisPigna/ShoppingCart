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
		seller = false;
	}
	
	public Account(String user, String pw){
		username = user;
		password = pw;
		seller = false;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean getType() {
		return seller;
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

	public boolean createAccount(){
		ArrayList<Account> checkDB = new ArrayList<Account>();
		try { 
			// Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("/Users/Robert/git/ShoppingCart/Login_Credentials.ser");
			ObjectInputStream obj_in = new ObjectInputStream(file_in);
			Account temp = (Account) obj_in.readObject();
			
			try {
				while (temp != null) {
					checkDB.add(temp);
					temp = (Account) obj_in.readObject();
				}
			} catch (EOFException e) {
				file_in.close();
				obj_in.close();
			}
			
			for (Iterator<Account> it = checkDB.iterator(); it.hasNext();) {
				Account account = it.next();
				if(this.getUsername().equals(account.getUsername()) || this.getPassword().equals(account.getPassword())) {
					return false;
				}
			}
			// Insert your own directory to avoid errors. Filename extension must be .ser
			File path = new File("/Users/Robert/git/ShoppingCart/Login_Credentials.ser");
			//********
			// Uncomment this line to create a seller-type account, then
			// re-comment it so all accounts created are customer-type
			// - Kris
			//********
			//seller = true;
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
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public int loginAccount(){
		ArrayList<Account> checkDB = new ArrayList<Account>();
		try { // Insert your own directory to avoid errors. Filename extension must be .ser
			FileInputStream file_in = new FileInputStream("/Users/Robert/git/ShoppingCart/Login_Credentials.ser");
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
					
					//if account type is customer, returns 1 to proceed to custInventoryView
					if (account.getType() == false) {
						return 1;
					}
					//if account type is seller, returns 2 to proceed to invMangView
					if (account.getType() == true) {
						return 2;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		//if login fails, returns 0
		return 0;
	}
		
	private static final long serialVersionUID = 6622568068083351485L;
	private String username;
	private String password;
	private boolean seller;
}