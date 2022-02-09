package com.lol.search.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Match {
	private String sumornerName;
	private String championName;
	private String kda;
	private long kill;
	private long assists;
	private long deaths;
	private long damage;
	
	
}