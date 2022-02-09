package com.lol.suggestion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SuggestionPageController")
public class SuggestionPageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int p = Integer.parseInt(request.getParameter("p"));
		SuggestionDAO.getDdao().getAllDuo(request);
		SuggestionDAO.getDdao().paging(p, request);
		request.setAttribute("contentPage", "suggestion_home.jsp");
		request.getRequestDispatcher("suggestion/suggestion.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
