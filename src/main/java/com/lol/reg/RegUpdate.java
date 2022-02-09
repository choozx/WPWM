package com.lol.reg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;

@WebServlet("/RegUpdate")
public class RegUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			AccountDAO.loginCheck(request);
			RegDAO.getPassword(request);
			request.setAttribute("contentPage", "reg2.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			AccountDAO.login(request, response);
			AccountDAO.loginCheck(request);
			RegDAO.updateInfo(request);
			AccountDAO.logOut(request);
			request.setAttribute("contentPage", "home.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
	}

}
