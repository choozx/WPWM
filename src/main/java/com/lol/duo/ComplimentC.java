package com.lol.duo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;


@WebServlet("/ComplimentC")
public class ComplimentC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		AccountDAO.loginCheck(request);
		ComplimentDAO.getCdao().getAllCompliment(request);
		ComplimentDAO.getCdao().paging(1, request);
		request.setAttribute("contentPage", "compliment_home.jsp");
		request.getRequestDispatcher("duo/compliment.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}