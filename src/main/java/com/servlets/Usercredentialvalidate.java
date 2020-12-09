package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Usercredentialvalidate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String n = req.getParameter("phoneNumber");
		String p = req.getParameter("pAssword");

		User user = new User();
		user.setPhone_number(n);
		user.setPassword(p);
		user = LoginDao.login(user);

		if (user.isValid()) {
			HttpSession session = req.getSession(true);
			session.setAttribute("currentSessionUser", user);

			RequestDispatcher rd = req.getRequestDispatcher("User Home Page.jsp");
			rd.forward(req, res);
		} else {

			out.print("<script type=\"text/javascript\">");
			out.print("alert('Sorry user name and password not match:) ');");
			out.print("</script>");

			RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
			rd.include(req, res);

			out.close();
		}
	}
}
