package com.lol.duo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;


@WebServlet("/ComplimentRegC")
public class ComplimentRegC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("nickname") == null) {
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			AccountDAO.loginCheck(request);
			ComplimentDAO.getCdao().getAllCompliment(request);
			ComplimentDAO.getCdao().paging(1, request);
			request.setAttribute("contentPage", "compliment_reg.jsp");
			request.getRequestDispatcher("duo/compliment.jsp").forward(request, response);
		}	
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		ComplimentDAO.getCdao().regCompliment(request, response);
		ComplimentDAO.getCdao().getAllCompliment(request);
		ComplimentDAO.getCdao().paging(1, request);
		request.setAttribute("contentPage", "compliment_home.jsp");
		
		
		
	}

}
