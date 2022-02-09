package com.lol.duo;

import java.sql.Date;

public class Compliment {
	private int no;
	private String name;
	private String tier;
	private String memo;
	private int likecount;
	private int pw;
	private String date;
	private String username;
	
	public Compliment() {
		// TODO Auto-generated constructor stub
	}

	public Compliment(int no, String name, String tier, String memo, int likecount, int pw, String date, String username) {
		super();
		this.no = no;
		this.name = name;
		this.tier = tier;
		this.memo = memo;
		this.likecount = likecount;
		this.pw = pw;
		this.date = date;
		this.username = username;
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

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getLikecount() {
		return likecount;
	}

	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}

	public int getPw() {
		return pw;
	}

	public void setPw(int pw) {
		this.pw = pw;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = Time.calculateTime(date);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
