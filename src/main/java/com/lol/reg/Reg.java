package com.lol.reg;

public class Reg {
	private String id;
	private String pw;
	private String nickname;
	private String puuid;
	
	public Reg() {
		// TODO Auto-generated constructor stub
	}

	public Reg(String id, String pw, String nickname, String puuid) {
		super();
		this.id = id;
		this.pw = pw;
		this.nickname = nickname;
		this.puuid = puuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}
	
}
