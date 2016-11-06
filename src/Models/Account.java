package Models;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import Views.*;

public class Account {
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
	}
	
	private String username;
	private String password;
}