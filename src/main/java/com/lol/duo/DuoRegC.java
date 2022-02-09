package com.lol.duo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.reg.RegDAO;
import com.lol.signup.AccountDAO;


@WebServlet("/DuoRegC")
public class DuoRegC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	if(request.getParameter("name") == null) {
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
	} else {
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().getAllDuo(request);
		DuoDAO.getDdao().paging(1, request);
		request.setAttribute("contentPage", "duo_reg.jsp");
		request.getRequestDispatcher("duo/duo.jsp").forward(request, response);
	}	
		
}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().regDuo(request, response);
		DuoDAO.getDdao().getAllDuo(request);
		DuoDAO.getDdao().paging(1, request);
		
	

}
}
