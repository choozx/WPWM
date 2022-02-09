package com.lol.search.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Tier {
	private String tier;
	private String rank;
	private Long leaguePoints;
	private Long wins;
	private Long losses;
}
