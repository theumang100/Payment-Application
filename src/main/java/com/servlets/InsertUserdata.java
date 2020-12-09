package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertUserdata extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		try {
			String first_name = req.getParameter("first_name");
			String last_name = req.getParameter("last_name");
			String phone_number = req.getParameter("phone_number");
			String password = req.getParameter("password");
			String email_address = req.getParameter("email_address");
			String birth_date = req.getParameter("birth_date");
			String gender = req.getParameter("gender");

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connstring = "jdbc:sqlserver://DESKTOP-1SBDLCS\\SQLEXPRESS;database=Learning;integratedSecurity=true;";
			Connection con = DriverManager.getConnection(connstring);

			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO paymentapplication_userdata (first_name,last_name,phone_number,password,email_address,birth_date,gender) values(?,?,?,?,?,?,?)");
			ps.setString(1, first_name);
			ps.setString(2, last_name);
			ps.setString(3, phone_number);
			ps.setString(4, password);
			ps.setString(5, email_address);
			ps.setString(6, birth_date);
			ps.setString(7, gender);

			int i = ps.executeUpdate();
			if (i != 0) {
				out.print("<script type=\"text/javascript\">");
				out.print("alert('Record has been Updated :) ');");
				out.println("location='Login.jsp';");
				out.print("</script>");

			} else {
				out.print("<script type=\"text/javascript\">");
				out.print("alert('Failled to Sign Up ');");
				out.println("location='Sign Up.jsp';");
				out.print("</script>");

			}

		} catch (Exception e) {
			out.println(e);
		}

	}

}
