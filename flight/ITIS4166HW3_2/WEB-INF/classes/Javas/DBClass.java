package Javas;

import java.sql.*;

public class DBClass {
	String connName;
	String userName;
	String password;
	String hostName;
	int port;
	String SID;
	Connection connection;
    Statement stat;

	public DBClass()
	{
		this.connName = "MySQL Connection";
		this.userName = "root";
		this.password = "qwe123";
		this.hostName = "localhost";
		this.port = 3306;
		this.SID = "hw2";
	}

	public DBClass(String pconnName, String puserName, String ppassword, String phostName, int pport, String pSID)
	{
		this.connName = pconnName;
		this.userName = puserName;
		this.password = ppassword;
		this.hostName = phostName;
		this.port = pport;
		this.SID = pSID;
	}

	public void connectMeIn()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		}
		try {
			//connection = DriverManager.getConnection("jdbc:oracle:thin:@" + this.hostName + ":" + this.port +":"+ this.SID + "," +  this.userName + "," + this.password);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw2", "root","qwe123");
			stat = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet execute(String SQL)
	{
	    ResultSet rs;
		try {
			rs = stat.executeQuery(SQL);
		    return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void insert(String SQL)
	{
	    Statement stat;
		try {
			stat = connection.createStatement();
			stat.executeUpdate(SQL);
			stat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}   
	}

	public void closeConnection()
	{
		try {
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}