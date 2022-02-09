package com.lol.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Summoner {
	private String id;
	private String name;
	private String puuid;
	private String accountId;
	private Long level;

}
