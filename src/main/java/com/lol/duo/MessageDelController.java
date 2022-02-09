package com.lol.duo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;


@WebServlet("/MessageDelController")
public class MessageDelController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().getAllDuo(request);
	
	if(request.getParameter("nickname") == null) {
		MessageDAO.deleteSendMessage(request, response);
	
	} else {	
		MessageDAO.deleteAllSendMessage(request, response);
	}
	MessageDAO.getRecvMessage(request);
	MessageDAO.getSendMessage(request);
	request.getRequestDispatcher("duo/message_sendlist.jsp").forward(request, response);
			
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().getAllDuo(request);
	
	if(request.getParameter("nickname") == null) {
		MessageDAO.deleteRecvMessage(request);
	
	} else {	
		MessageDAO.deleteAllRecvMessage(request, response);
	}
	MessageDAO.getRecvMessage(request);
	MessageDAO.getSendMessage(request);
	
	request.getRequestDispatcher("duo/message_recvlist.jsp").forward(request, response);
		
	}

}
