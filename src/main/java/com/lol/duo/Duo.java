package com.lol.duo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Duo {
	private int no;
	private String name;
	private String position;
	private String type;
	private String memo;
	private int pw;
	private String date;
	private String tier;
	private Long win;
	private Long lose;
	
	
	
	public Duo() {
		// TODO Auto-generated constructor stub
	}



	public Duo(int no, String name, String position, String type, String memo, int pw, String date, String tier,
			Long win, Long lose) {
		super();
		this.no = no;
		this.name = name;
		this.position = position;
		this.type = type;
		this.memo = memo;
		this.pw = pw;
		this.date = date;
		this.tier = tier;
		this.win = win;
		this.lose = lose;
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



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getMemo() {
		return memo;
	}



	public void setMemo(String memo) {
		this.memo = memo;
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



	public String getTier() {
		return tier;
	}



	public void setTier(String tier) {
		this.tier = tier;
	}



	public Long getWin() {
		return win;
	}



	public void setWin(Long win) {
		this.win = win;
	}



	public Long getLose() {
		return lose;
	}



	public void setLose(Long lose) {
		this.lose = lose;
	}
	
	
	
	
	
	
	
}
