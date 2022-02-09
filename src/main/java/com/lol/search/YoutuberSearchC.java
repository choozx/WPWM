package com.lol.search;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/YoutuberSearchC")
public class YoutuberSearchC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Search.search(request);
		Search.youtuberSearch(request);
		System.out.println(request.getAttribute("matchCount"));
		if ((int) request.getAttribute("matchCount") == -1 || request.getAttribute("matchCount") == null) {
			request.setAttribute("contentPage", "noOneYoutuber.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);			
		} else {			
			request.setAttribute("contentPage", "main_youtuber_search.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
