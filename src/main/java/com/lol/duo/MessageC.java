package com.lol.duo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lol.signup.AccountDAO;

@WebServlet("/MessageC")
public class MessageC extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("no") != null) {

			AccountDAO.loginCheck(request);
			DuoDAO.getDdao().getDuo(request);
			ComplimentDAO.getCdao().getAllCompliment(request);
			request.getRequestDispatcher("duo/message.jsp").forward(request, response);
		} else {
			AccountDAO.loginCheck(request);
			DuoDAO.getDdao().getDuo(request);
			MessageDAO.getRecvMessage(request);
			MessageDAO.getSendMessage(request);
			request.getRequestDispatcher("duo/message2.jsp").forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountDAO.loginCheck(request);
		DuoDAO.getDdao().getDuo(request);
		MessageDAO.regMessage(request);
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();


		String msg = "쪽지 보내기 성공";
		String str = "";
		str = "<script language='javascript'>";
		str += "alert('" + msg + "');"; // 얼럿창 띄우기
		str += "</script>";
		out.print(str);

		str = "";
		str = "<script language='javascript'>";
		str += "self.close();"; // 창닫기
		str += "</script>";
		out.print(str);
		
	}

}
