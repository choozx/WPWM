package com.lol.search.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.lol.search.Summoner;
import com.lol.search.Youtuber;
import com.lol.main.DBManager;

public class RiotClient extends Client{
	
	private final static YoutubeClient youtuberclient = new YoutubeClient();
	
	private final String KO_URL = "https://kr.api.riotgames.com";
	private final String ASIA_URL = "https://asia.api.riotgames.com";	
	private final String SEARCH_SUMMONERS_PATH = "/lol/summoner/v4/summoners/by-name/%s";
	private final String SEARCH_LEAGUE_PATH = "/lol/league/v4/entries/by-summoner/%s";
	private final String SEARCH_MATCHID_PATH = "/lol/match/v5/matches/by-puuid/%s/ids?start=%s&count=%s";
	private final String SEARCH_PUUID_PATH = "/lol/match/v5/matches/by-puuid/";
	private final String SEARCH_MATCH_PATH = "/lol/match/v5/matches/%s";
	
	public RiotClient() {
		super("X-Riot-Token", "RGAPI-b0962b3c-4c65-4626-86ad-632f3d4a3c05");
	}
		
	public Summoner getUser(String name) throws Exception {
	
		String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
		JSONObject jsonResult = this.<JSONObject>get(KO_URL ,setPathVariables(SEARCH_SUMMONERS_PATH, encodedName));
		return new Summoner(
				(String) jsonResult.get("id"),
				(String) jsonResult.get("name"),
				(String) jsonResult.get("puuid"),
				(String) jsonResult.get("accountId"),
				(Long) jsonResult.get("summonerLevel")
		);
	}
	
	public Tier getUserTier(String id) throws Exception {
		JSONArray jsonResult = this.<JSONArray>get(KO_URL ,setPathVariables(SEARCH_LEAGUE_PATH, id));
		JSONObject soloRankDTO = null;
		
		for (int i = 0; i < jsonResult.size(); i++) {
			JSONObject aa = (JSONObject) jsonResult.get(i);
			if (aa.containsValue("RANKED_SOLO_5x5")) {
				soloRankDTO = aa;
			}
		}
		
		return new Tier(
				(String) soloRankDTO.get("tier"),
				(String) soloRankDTO.get("rank"),
				(Long) soloRankDTO.get("leaguePoints"),
				(Long) soloRankDTO.get("wins"),
				(Long) soloRankDTO.get("losses")
				);
	}
	
	public List<String> getMatchIdList(String puuid, int start, int count) throws Exception {
		JSONArray jsonResult = this.<JSONArray>get(ASIA_URL,setPathVariables(SEARCH_MATCHID_PATH, puuid, String.valueOf(start), String.valueOf(count)));
		List<String> matchList = new ArrayList<String>();
		for (int i = 0; i < jsonResult.size(); i++) {
			matchList.add((String) jsonResult.get(i));			
		}
		return matchList;
	}
	
	public List<Match> getMatch(String match) throws Exception {
		JSONObject jsonResult = this.<JSONObject>get(ASIA_URL ,setPathVariables(SEARCH_MATCH_PATH, match));
		JSONObject info = (JSONObject) jsonResult.get("info");
		JSONArray participantsDT = (JSONArray) info.get("participants");
		
		List<Match> matchDTO = new ArrayList<Match>();
		Match user = null;
		
		for (int i = 0; i < 10; i++) {
			JSONObject participantsDTO = (JSONObject) participantsDT.get(i);
			DecimalFormat format = new DecimalFormat("#0.00");
			
			String championName = (String) participantsDTO.get("championName");
			String summonerName = (String) participantsDTO.get("summonerName");
			Long kill = (Long) participantsDTO.get("kills");
			Long assists = (Long) participantsDTO.get("assists");
			Long deaths = (Long) participantsDTO.get("deaths");
			Long totalDamageDealtToChampions = (Long) participantsDTO.get("totalDamageDealtToChampions");
			
			user = new Match();
			user.setChampionName(championName);
			user.setSumornerName(summonerName);
			user.setKill(kill);
			user.setAssists(assists);
			user.setDeaths(deaths);
			if(deaths == 0) {
				user.setKda("perfect");
			} else {
				double kda = ((kill + assists)/ (double) deaths);
				String f_kda = format.format(kda);
				user.setKda(f_kda);	
			}
			
			user.setDamage(totalDamageDealtToChampions);
			
			matchDTO.add(user);
		}
		return matchDTO;
	}
	
	public Youtuber getYoutuberInfo(String puuid) {
		/**
		 * 1. DB에서 데이터를 조회
		 * 2. 조회한 데이터로 youtubeClient를 사용
		 */
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Youtuber youtuber = null;
		
		
		String cutPuuid = puuid.substring(0, 30);
		String sql = "select * from youtuber_account where a_puuid like \'"+cutPuuid +"%\'";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			youtuber = new Youtuber();
			
			rs.next();
			String youtuber_url = "https://www.youtube.com/" + rs.getString("a_channelURL");
			
			String channelId = rs.getString("a_channelID");
			List<String> info = youtuberclient.getYoutuberInfo(channelId);
			String y_name = info.get(0);
			String y_subscribers = info.get(1);
			String y_img = info.get(2);
			
			youtuber.setChannel_url(youtuber_url);
			youtuber.setName(y_name);
			youtuber.setSubscribers(y_subscribers);
			youtuber.setImg(y_img);
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		return youtuber;
	}
	
	public List<List<String>> getWithMatch(List<String> matchid) throws Exception {
		List<List<String>> matchAll_youtuber = new ArrayList<List<String>>();
		List<String> puuid_youtuber = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from youtuber_account";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				puuid_youtuber.add(rs.getString("a_puuid"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
		for (int i = 0; i < matchid.size(); i++) {
			String match = (String) matchid.get(i);
			JSONObject jsonResult = this.<JSONObject>get(ASIA_URL ,setPathVariables(SEARCH_MATCH_PATH, match));
			List<String> match_youtuber = new ArrayList<String>();
			
			JSONObject metadata = (JSONObject) jsonResult.get("metadata"); 
			JSONArray participants = (JSONArray) metadata.get("participants");
			
			
			for (int j = 0; j < participants.size(); j++) {
				for (int j2 = 0; j2 < puuid_youtuber.size(); j2++) {
					if (participants.get(j).equals(puuid_youtuber.get(j2))) {
						match_youtuber.add(puuid_youtuber.get(j2));
						break;
					}
				}
			}
			if(!match_youtuber.isEmpty()) {					
				match_youtuber.add(match);
				matchAll_youtuber.add(match_youtuber);
			}
		}
		return matchAll_youtuber;
	}

}
