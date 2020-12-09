package com.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDao {
	static ResultSet rs=null;
	static Connection con=null;
	
	public static User login(User link) {
		Statement st=null;
		
		String phone_number=link.getPhone_number();
		String password=link.getPassword();
		
		String query="SELECT * FROM paymentapplication_userdata where phone_number='"
                + phone_number
                + "' AND password='"
                + password
                + "'";
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connstring = "jdbc:sqlserver://DESKTOP-1SBDLCS\\SQLEXPRESS;database=Learning;integratedSecurity=true;";
			con = DriverManager.getConnection(connstring);
			st = con.createStatement();
			rs = st.executeQuery(query);
			boolean well=rs.next();
			
			if(!well) {
				link.setValid(false);
			}
			
			else if(well) {
				String firstName=rs.getString("first_name");
				String lastName=rs.getString("last_name");
				String emailAddress=rs.getString("email_address");
				String birthDate=rs.getString("birth_date");
				String gender=rs.getString("gender");
				int walletBalance=rs.getInt("wallet_balance");
				
				link.setFirst_name(firstName);
				link.setLast_name(lastName);
				link.setEmail_address(emailAddress);
				link.setBirth_date(birthDate);
				link.setGender(gender);
				link.setWallet_balance(walletBalance);
				link.setValid(true);
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		finally
		{
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
			}
				rs=null;
		}
			if(st!=null) {
				try {
					st.close();}
				catch(Exception e) {}
				st=null;	
				}
			if(con!=null) {
				try {
					con.close();}
				catch(Exception e) {
				}
				con=null;
			}
			}
		return link;
	}
	
}
