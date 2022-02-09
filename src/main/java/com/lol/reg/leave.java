package com.lol.reg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;

@WebServlet("/leave")
public class leave extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		AccountDAO.loginCheck(request);
		request.setAttribute("contentPage", "reg3.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			if (request.getParameter("pw") != null) {
				RegDAO.delete(request);
				AccountDAO.loginCheck(request);
				request.setAttribute("contentPage", "home.jsp");
		
			}else {
				AccountDAO.loginCheck(request);
				request.setAttribute("contentPage", "home.jsp");
				
			}
			
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
