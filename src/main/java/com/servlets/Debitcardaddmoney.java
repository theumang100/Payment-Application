package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Debitcardaddmoney extends HttpServlet {
	static ResultSet rs = null;
	static Connection con = null;
	Statement st = null;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		long debitcardNo = Long.parseLong(req.getParameter("debitcardno"));
		int debitcardPin = Integer.parseInt(req.getParameter("debitcardpin"));
		int addmoneyrequest = Integer.parseInt(req.getParameter("addmoneyinput"));

		String query = "SELECT * FROM debitcard_details where debitcard_no='" + debitcardNo + "' AND debitcard_pin='"
				+ debitcardPin + "'";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connstring = "jdbc:sqlserver://DESKTOP-1SBDLCS\\SQLEXPRESS;database=Learning;integratedSecurity=true;";
			con = DriverManager.getConnection(connstring);
			st = con.createStatement();
			rs = st.executeQuery(query);
			boolean well = rs.next();

			if (!well) {

				out.print("<script type=\"text/javascript\">");
				out.print("alert('Debit Card Details Not Correct Please Try Again ');");
				out.print("</script>");

				RequestDispatcher rd = req.getRequestDispatcher("Add Money.jsp");
				rd.include(req, res);
			} else if (well) {
				int debitcardBalance = Integer.parseInt(rs.getString("debitcard_balance"));
				if ((addmoneyrequest) > (debitcardBalance)) {

					out.print("<script type=\"text/javascript\">");
					out.print("alert('Insufficient Balance In DebitCard Account ');");
					out.print("</script>");

					RequestDispatcher rd = req.getRequestDispatcher("Add Money.jsp");
					rd.include(req, res);
				} else if ((addmoneyrequest) < (debitcardBalance)) {

					HttpSession session = req.getSession();
					User currentUser = ((User) (session.getAttribute("currentSessionUser")));
					int usercurrentwalletbalance = currentUser.getWallet_balance();
					String userphoneNo = currentUser.getPhone_number();

					int userupdatedwalletbalance = usercurrentwalletbalance + addmoneyrequest;
					int debitcardupdatedbalance = debitcardBalance - addmoneyrequest;

					String sql1 = "UPDATE paymentapplication_userdata SET wallet_balance=" + userupdatedwalletbalance
							+ "WHERE phone_number='" + userphoneNo + "'";
					st.executeUpdate(sql1);

					String sql2 = "UPDATE debitcard_details SET debitcard_balance=" + debitcardupdatedbalance
							+ "WHERE debitcard_no=" + debitcardNo;
					st.executeUpdate(sql2);

					out.print("<script type=\"text/javascript\">");
					out.print("alert('Money Added to Wallet Successfully ');");
					out.println("location='User Home Page.jsp';");
					out.print("</script>");

				}

			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
				}
				st = null;
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}
				con = null;
			}
		}

	}

}
