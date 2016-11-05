package Models;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
		ArrayList<String> lines = new ArrayList();
		lines.add(username);
		lines.add(password);
		Path p = Paths.get("C:\\Users\\Public\\Desktop\\", "accountsDB.txt");
		try{
			if (Files.exists(p) == false){
				Files.createFile(p);
			}
			Files.write(p, lines, Charset.forName("UTF-8"));
		}catch(Exception e){
			e.printStackTrace();
		}
		//login.Notify();
	}
	
	private String username;
	private String password;
}