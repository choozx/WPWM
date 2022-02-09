package com.lol.suggestion;

import java.sql.Date;

public class Suggestion {
	private int no;
	private String name;
	private String headname;
	private String title;
	private String pw;
	private String text;
	private String date;
	
	public Suggestion() {
		// TODO Auto-generated constructor stub
	}

	public Suggestion(int no, String name, String headname, String title, String pw, String text, String date) {
		super();
		this.no = no;
		this.name = name;
		this.headname = headname;
		this.title = title;
		this.pw = pw;
		this.text = text;
		this.date = date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadname() {
		return headname;
	}

	public void setHeadname(String headname) {
		this.headname = headname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = Time.calculateTime(date);
	}

}
