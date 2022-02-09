package com.lol.youtube;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;

@WebServlet("/YoutuberUpC")
public class YoutuberUpC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		AccountDAO.loginCheck(request);
		YoutuberDAO.getYoutuber(request);
		request.setAttribute("contentPage", "youtuber_up.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
		AccountDAO.loginCheck(request);
		YoutuberDAO.updateYoutuber(request);
		YoutuberDAO.getAllYoutuber(request);
		request.setAttribute("contentPage", "youtuber.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	
	}

}
