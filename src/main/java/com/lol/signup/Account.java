package com.lol.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Account {
	private String id;
	private String pw;
	private String nickname;
	private String puuid;
	private String tier;
	private Long win;
	private Long lose;
	
	
	
	
}
