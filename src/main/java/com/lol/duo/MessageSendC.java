package com.lol.duo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;


@WebServlet("/MessageSendC")
public class MessageSendC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			AccountDAO.loginCheck(request);
			DuoDAO.getDdao().getDuo(request);
			MessageDAO.detailSendMessage(request);
			MessageDAO.getRecvMessage(request);
			MessageDAO.getSendMessage(request);
			request.getRequestDispatcher("duo/message_send.jsp").forward(request, response);
			
			
			
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().getDuo(request);	
		MessageDAO.regMessage2(request,response);
		



	}

}
