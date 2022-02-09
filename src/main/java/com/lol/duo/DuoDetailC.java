package com.lol.duo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;


@WebServlet("/DuoDetailC")
public class DuoDetailC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().getDuo(request);
		DuoDAO.getDdao().getAllDuo(request);
		DuoDAO.getDdao().paging(1, request);
		request.setAttribute("contentPage", "duo_detail.jsp");
		request.getRequestDispatcher("duo/duo.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
