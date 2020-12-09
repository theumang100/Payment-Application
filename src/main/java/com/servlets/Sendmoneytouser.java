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

public class Sendmoneytouser extends HttpServlet {
	static ResultSet rs = null;
	static Connection con = null;
	Statement st = null;

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String moneyReceiveruserphoneNo = req.getParameter("sendmoneyuser");
		int sendMoneyamount = Integer.parseInt(req.getParameter("sendmoneyinput"));

		String sql = "SELECT * FROM paymentapplication_userdata WHERE phone_number='" + moneyReceiveruserphoneNo + "'";

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connstring = "jdbc:sqlserver://DESKTOP-1SBDLCS\\SQLEXPRESS;database=Learning;integratedSecurity=true;";
			con = DriverManager.getConnection(connstring);
			st = con.createStatement();
			rs = st.executeQuery(sql);
			boolean correct = rs.next();

			if (!correct) {

				out.print("<script type=\"text/javascript\">");
				out.print("alert('User Doesn't Exists');");
				out.print("</script>");

				RequestDispatcher rd = req.getRequestDispatcher("Sendmoney.jsp");
				rd.include(req, res);

			}

			else if (correct) {
				int receiverUserwalletbalance = rs.getInt("wallet_balance");

				HttpSession session = req.getSession();
				User currentUser = ((User) (session.getAttribute("currentSessionUser")));

				String currentUserphoneno = currentUser.getPhone_number();
				int currentUserwalletbalance = currentUser.getWallet_balance();

				if ((sendMoneyamount) > (currentUserwalletbalance)) {

					out.print("<script type=\"text/javascript\">");
					out.print("alert('Not Sufficient Balance On Your Wallet ');");
					out.print("</script>");

					RequestDispatcher rd = req.getRequestDispatcher("Sendmoney.jsp");
					rd.include(req, res);

				} else if ((sendMoneyamount) < (currentUserwalletbalance)) {
					int currentUserupdatedwalletbalance = currentUserwalletbalance - sendMoneyamount;
					int receiverUserupdatedwalletbalance = receiverUserwalletbalance + sendMoneyamount;

					String sql3 = "UPDATE paymentapplication_userdata SET wallet_balance="
							+ currentUserupdatedwalletbalance + "WHERE phone_number='" + currentUserphoneno + "'";
					String sql4 = "UPDATE paymentapplication_userdata SET wallet_balance="
							+ receiverUserupdatedwalletbalance + "WHERE phone_number='" + moneyReceiveruserphoneNo
							+ "'";

					st.executeUpdate(sql3);
					st.executeUpdate(sql4);

					out.print("<script type=\"text/javascript\">");
					out.print("alert('Money Sended Successfully :) ');");
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
