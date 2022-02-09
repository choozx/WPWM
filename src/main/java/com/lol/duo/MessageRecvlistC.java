package com.lol.duo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;


@WebServlet("/MessageRecvlistC")
public class MessageRecvlistC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().getAllDuo(request);
		MessageDAO.detailRecvMessage(request);
		MessageDAO.getRecvMessage(request);
		request.getRequestDispatcher("duo/message_recvlist.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
	}

}
