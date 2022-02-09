package com.lol.search;

import javax.servlet.http.HttpServletRequest;

import com.lol.search.client.Match;
import com.lol.search.client.RiotClient;
import com.lol.search.client.Tier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Search { 

	private final RiotClient riotClient = new RiotClient();
	
	public static void search(HttpServletRequest request) {
		Summoner summoner = null;
		try {
			String userName = request.getParameter("name");
			summoner = new RiotClient().getUser(userName);
			System.out.println(summoner);
			Tier tier = new RiotClient().getUserTier(summoner.getId());
			System.out.println(tier);
			
			request.setAttribute("tier", tier);
			request.setAttribute("summorner", summoner);

		} catch (Exception e) {
			request.setAttribute("summorner", summoner);
			e.printStackTrace();
		}
	}

	public static void match(HttpServletRequest request) {
		
		try {
			Summoner s = (Summoner) request.getAttribute("summorner");
			String puuid = s.getPuuid();
			
			List<String> matchId = new RiotClient().getMatchIdList(puuid, 0, 20);//최근 20개임 모두 반환					
			List<List<Match>> matchALL = new ArrayList<List<Match>>();
			for (String match : matchId) {
				matchALL.add(new RiotClient().getMatch(match));
			}
			
			request.setAttribute("matchAll", matchALL);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}		

	public static void youtuberSearch(HttpServletRequest request) {
		/**
		 * 현재상태
		 * 라이엇클라이언트로 유튜브 클라이언트를 사용해서 디비를 조회하고 결과를 만들어냄
		 */
		
		/**
		 * 개선안
		 * 1. DB에서 데이터를 조회
		 * 2. 조회한 데이터로 youtubeClient를 사용
		 */
		try {
			Summoner s = (Summoner) request.getAttribute("summorner");
			String puuid = s.getPuuid();
			
			List<String> matchId = new RiotClient().getMatchIdList(puuid, 0, 20);//최근 40개임 모두 반환
			List<List<String>> match_with_youtuber = new RiotClient().getWithMatch(matchId);//위에 뽑은것중에 유뷰버 있는지 없는지 검색	아래서는 유튜버 puuid로 사용		
			
			for (int i = 0; i < match_with_youtuber.size(); i++) {
				List<Youtuber> youtuberDTO = new ArrayList<Youtuber>();
				List<String> matchDTO = new ArrayList<String>(match_with_youtuber.get(i));
				for (int j = 0; j < matchDTO.size() - 1; j++) {
					youtuberDTO.add(new RiotClient().getYoutuberInfo(matchDTO.get(j)));
				}				
				System.out.println(youtuberDTO);
				request.setAttribute("youtuberDTO" + i, youtuberDTO);
				request.setAttribute("matchDTO" + i, new RiotClient().getMatch(matchDTO.get(matchDTO.size()-1)));		
			}
			request.setAttribute("matchCount", match_with_youtuber.size()-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
