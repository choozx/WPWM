package com.lol.duo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;


@WebServlet("/DuoSearchC")
public class DuoSearchC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().searchDuo(request);
		DuoDAO.getDdao().paging(1, request);
		request.setAttribute("contentPage", "duo_home.jsp");
		request.getRequestDispatcher("duo/duo.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().searchNickname(request);
		DuoDAO.getDdao().paging(1, request);
		request.setAttribute("contentPage", "duo_home.jsp");
		request.getRequestDispatcher("duo/duo.jsp").forward(request, response);
		
		
	}

}
