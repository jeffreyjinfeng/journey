package Javas;

import java.sql.ResultSet;
import java.sql.SQLException;
import Javas.DBClass;

public class Users {
	private String username;
	private String password;
	private String email;
	private String dob;
	private boolean logged_in;
	DBClass d;
	
	public Users()
	{
		logged_in = false;
		d = new DBClass();
		d.connectMeIn();
	}

	public Users(String username, String password, String email, String dob)
	{
		logged_in = false;
		d = new DBClass();
		d.connectMeIn();
		this.username = username;
		this.password = password;
		this.email = email;
		this.dob = dob;
	}

	public Users(String username, String password)
	{
		logged_in = false;
		d = new DBClass();
		d.connectMeIn();
		this.username = username;
		this.password = password;
		this.logged_in = check();
	}
	
	public void insert()
	{
		String SQL = "INSERT INTO Users(niner_id, PASSWORD, EMAIL, DOB) Values('"+username+"','"+password+
		"','"+email+"','"+dob+"')";
		
	    d.insert(SQL);
	}

	public void closeConnection()
	{
		d.closeConnection();
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}

	public void setDob(String dob)
	{
		this.dob = dob;
	}
	
	public String getDob()
	{
		return dob;
	}

	public boolean getLoggedIn()
	{
		return logged_in;
	}
	
	public boolean check()
	{
		String findUPCombo = "SELECT * FROM users WHERE niner_id = '"+username+"' AND password='"+password+"'";
		ResultSet rs = d.execute(findUPCombo);
		System.out.println("Attempting Login...");
		try{
			System.out.println("Login Validation...");
			if(rs.next()){
				System.out.println("Logging In...");
				logged_in = true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return logged_in;
	}

	public boolean checkUsername()
	{
		String findUser = "SELECT * FROM users WHERE niner_id = '"+username+"'";
		ResultSet rs = d.execute(findUser)
		;
		
		try{
			if(rs.next()){
				System.out.println("Account Exists");
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		insert();
		return false;
	}
}