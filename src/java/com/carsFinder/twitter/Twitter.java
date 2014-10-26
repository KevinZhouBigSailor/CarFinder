package com.carsFinder.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Data;
import model.DataPackage;
import model.SearchResult;
import model.SentimentObject;
import model.SentimentResponse;
import model.Status;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;


public class Twitter {
	public static String LOCATIONS_URL = "http://www.sentiment140.com/api/bulkClassifyJson";
	
	public static DataPackage searchTwitter(String keyword) {
		//step1: get search results from twitter
		keyword = keyword.replaceAll(" ", "%20");
		Gson gson = new Gson();
		OAuthService service = new ServiceBuilder().provider(TwitterApi.class)
				.apiKey("anQy8geR1YrJvpSPyJeHaQ")
				.apiSecret("I2XV6QNf1DaWLBTWC0E3vPB5objyY6UcbE8axddW0").build();
		Token accessToken = new Token(
				"245840803-riOYDVIknZWpRiYIGgq6wrHt2zIhROrIi2S9sdvQ",
				"L683erVsscvSp3bfDEOxFlgOMEPoHHqEBqFQgtUPP78sp");
		List<SearchResult> srl = new ArrayList<SearchResult>();		
		List<SearchResult> partial = getResult(service, accessToken, keyword);
		int count = 0;
		
		while (partial != null && count < 2000) {
			for (SearchResult stmp : partial) {
				count += stmp.getStatuses().size();
			}
			srl.addAll(partial);
			partial = getResult(service, accessToken, keyword);
		}
		
		//feedback statuses to dp
		DataPackage dp = new DataPackage();
		ArrayList<Status> tmpSRL = new ArrayList<Status>();
		for (SearchResult sr : srl) {
			tmpSRL.addAll(sr.getStatuses());
		}
		dp.setStatuses(tmpSRL);
		
		
		
		//step2: Assemble the parameter to invoke the sentiment analyze
		SentimentObject so = new SentimentObject();
		so.setLanguage("en");
		List<Data> datas = new ArrayList<Data>();
		for (SearchResult sr : srl) {
			if (sr.getStatuses().size() > 0) {
				for (Status stmp : sr.getStatuses()) {
					Data data = new Data();
					data.setId(stmp.getId());
					data.setTopic(keyword);
					data.setText(stmp.getText());
					datas.add(data);
				}
			}
		}
		so.setData(datas);
		String soToJson = gson.toJson(so);
		//step3: Invoke the sentiment analyze web service using the http client
		try {
			@SuppressWarnings("deprecation")
			DefaultHttpClient client = new DefaultHttpClient();
			StringEntity entity = new StringEntity(soToJson);
			HttpPost httpost = new HttpPost(LOCATIONS_URL);
			httpost.setEntity(entity);
			HttpResponse httpResponse = client.execute(httpost);
			String locationsJSONString = getStringFromHttp(httpResponse
					.getEntity());
			SentimentResponse sr = gson.fromJson(locationsJSONString,
					SentimentResponse.class);
			HashMap<Long, Integer> map = new HashMap<Long, Integer>(3000);
			List<Data> dataList = sr.getData();
			for (Data data : dataList) {
				map.put(data.getId(), data.getPolarity());
			}
			dp.setMap(map);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dp;
	}

	
	private static List<SearchResult> getResult(OAuthService service, Token accessToken, String keyword) {
		Gson gson = new Gson();
		ArrayList<SearchResult> result = new ArrayList<SearchResult>();
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"https://api.twitter.com/1.1/search/tweets.json?q=" + 
				keyword+"&count=100&lang=en");
		service.signRequest(accessToken, request);
		Response response = request.send();
		String value ="";
		value = response.getBody();
		SearchResult searchResult = gson.fromJson(value, SearchResult.class);
		
		if (searchResult.getStatuses().size() < 1) {
			return null;
		}
		
		result.add(searchResult);
		while (searchResult.getSearch_metadata().getNext_results() != null) {
			 request = new OAuthRequest(Verb.GET,
						"https://api.twitter.com/1.1/search/tweets.json" + 
						searchResult.getSearch_metadata().getNext_results());
			 service.signRequest(accessToken, request);
			 response = request.send();
			 searchResult = gson.fromJson(response.getBody(), SearchResult.class);
			 result.add(searchResult);
		}
		
		return result;
	}
	
	private static String getStringFromHttp(HttpEntity entity) {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
}
