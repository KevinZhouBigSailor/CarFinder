package com.carsFinder.twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import model.DataPackage;
import vo.StatusVO;
import vo.ViewObject;

import com.opensymphony.xwork2.ActionSupport;

public class MainAction extends ActionSupport{
	private static final long serialVersionUID = 6503785056428684687L;
	private String s1;
	private String s2;
	private ArrayList<String> photoUrls;
	private int subjectHotLevel;
	private int subjectScore;
	private List<Integer> attitudeSplit;
	private Map<String, Integer> locationDistribution;
	private int totalTweet;
	private List<StatusVO> top5GoodStatus;
	private List<StatusVO> top5BadStatus;

	public void excuteSearch(){
		String keyword = s1+" "+s2;
                
		Flickr flickr = new Flickr();
		photoUrls = flickr.getPhoto(keyword);
                System.out.println(keyword);
                DataPackage dp = Twitter.searchTwitter(keyword);
                Aggregation ag = new Aggregation();
              
                top5GoodStatus = ag.getTop10Tweets(dp);
                top5BadStatus = ag.getTop5BandStatus(dp);
                
                totalTweet = ag.getTotalTweet(dp);
                ViewObject voTemp = new ViewObject();
                
		voTemp.setPhotoUrls(photoUrls);
                voTemp.setTotalTweet(totalTweet);
                voTemp.setTop10GoodStatus(top5GoodStatus);
                voTemp.setTop5BadStatus(top5BadStatus);

		
	}
	
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public ArrayList<String> getPhotoUrls() {
		return photoUrls;
	}
	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	
	public int getTotalTweet() {
		return totalTweet;
	}
	public void setTotalTweet(int totalTweet) {
		this.totalTweet = totalTweet;
	}

	public List<StatusVO> getTop5GoodStatus() {
		return top5GoodStatus;
	}

	public void setTop10GoodStatus(List<StatusVO> top5GoodStatus) {
		this.top5GoodStatus = top5GoodStatus;
	}

	public List<StatusVO> getTop5BadStatus() {
		return top5BadStatus;
	}

	public void setTop5BadStatus(List<StatusVO> top5BadStatus) {
		this.top5BadStatus = top5BadStatus;
	}
	
}
