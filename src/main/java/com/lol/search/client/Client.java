package com.lol.search.client;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.parser.JSONParser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Client {
	private final String API_KEY;
	private final String API_VALUE;
	
	protected <T> T get(String url,String path) throws Exception{
		InputStream is = makeHttpsURLConnection(url, path + getApiKeyParam(path), "GET").getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		JSONParser jp = new JSONParser();
		return (T) jp.parse(isr);
	}
	
	protected HttpsURLConnection makeHttpsURLConnection(String url,String path, String method) throws Exception{
		HttpsURLConnection huc = null;
		URL u = new URL(url+path);
		huc = (HttpsURLConnection) u.openConnection(); //fixme ¤¾¤¾ ¹Ì½ÉÂ½¾î
		huc.setRequestProperty(API_KEY, API_VALUE);
		huc.setRequestMethod(method);
		return huc;
	}
	
	protected String setPathVariables(String url, String... pathVariables) {
		return String.format(url, pathVariables);
	}
	
	private String getApiKeyParam(String path) {
		if(path.indexOf("?") == -1) {
			return "?"+API_KEY+"="+API_VALUE; 
		}
		return "&"+API_KEY+"="+API_VALUE;
	}
}
