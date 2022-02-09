package com.lol.search;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchC")
public class SearchC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Search.search(request);
		System.out.println(request.getAttribute("summorner"));
		if (request.getAttribute("summorner") == null) {
			request.setAttribute("contentPage", "noSearchUser.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		Search.match(request);
		request.setAttribute("contentPage", "main_search.jsp");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
