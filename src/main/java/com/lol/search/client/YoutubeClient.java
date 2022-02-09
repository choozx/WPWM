package com.lol.search.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.lol.search.Youtuber;
import com.lol.main.DBManager;

public class YoutubeClient extends Client {
	
	private final String YOUTUBE_URL = "https://www.googleapis.com/youtube";
	private final String CHANNEL_API_PATH ="/v3/channels"; 
	private final String CHANNEL_API_PARAMS = "?id=%s&part=statistics,snippet";
	
	public YoutubeClient() {
		super("key", "AIzaSyDEHOobubELvUY8Pwt2RWD7e02_HlYkbdI");
	}
	
	public List<String> getYoutuberInfo(String id) throws Exception{
		JSONObject jsonResult = get(YOUTUBE_URL ,setPathVariables(CHANNEL_API_PATH+CHANNEL_API_PARAMS, id));
		JSONArray items = (JSONArray) jsonResult.get("items");
		JSONObject item = (JSONObject) items.get(0);
		JSONObject snippet = (JSONObject) item.get("snippet");
		String title = (String) snippet.get("title");
		
		JSONObject thumbnails = (JSONObject) snippet.get("thumbnails");
		JSONObject img = (JSONObject) thumbnails.get("default");
		String url = (String) img.get("url");
		
		JSONObject statistics = (JSONObject) item.get("statistics");
		String subscriberCount = (String) statistics.get("subscriberCount");
		
		//아 뭔가 추가하는 코드 
		System.out.println(title);
		System.out.println(subscriberCount);
		System.out.println(url);
		
		List<String> info = new ArrayList<String>();
		info.add(title);
		info.add(subscriberCount);
		info.add(url);
		return info;
	}
	
}
