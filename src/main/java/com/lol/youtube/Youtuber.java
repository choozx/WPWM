package com.lol.youtube;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Youtuber {

	private int no;
	private String youtuber;
	private String puuid;
	private String channelURL;
	private String channelID;
	

}
