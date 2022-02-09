package com.lol.duo;

import java.sql.Date;

public class Message2 {
	private int no;
	private String sendname;
	private String recvname;
	private String content;
	private Date date;
	
	public Message2() {
		// TODO Auto-generated constructor stub
	}

	public Message2(int no, String sendname, String recvname, String content, Date date) {
		super();
		this.no = no;
		this.sendname = sendname;
		this.recvname = recvname;
		this.content = content;
		this.date = date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSendname() {
		return sendname;
	}

	public void setSendname(String sendname) {
		this.sendname = sendname;
	}

	public String getRecvname() {
		return recvname;
	}

	public void setRecvname(String recvname) {
		this.recvname = recvname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
