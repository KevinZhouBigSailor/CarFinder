package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewObject implements Serializable{

	private static final long serialVersionUID = 8242029122995660604L;
	private ArrayList<String> photoUrls;
	private int subjectHotLevel;
	private int subjectScore;
	private List<Integer> attitudeSplit;
	private Map<String, Integer> locationDistribution;
	private int totalTweet;
	private List<StatusVO> top10GoodStatus;
	private List<StatusVO> top5BadStatus;
	
	public ArrayList<String> getPhotoUrls() { 
		return photoUrls;
	}
	public int getSubjectHotLevel() {
		return subjectHotLevel;
	}
	public int getSubjectScore() {
		return subjectScore;
	}
	public Map<String, Integer> getLocationDistribution() {
		return locationDistribution;
	}
	public int getTotalTweet() {
		return totalTweet;
	}
	public void setPhotoUrls(ArrayList<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	public void setSubjectHotLevel(int subjectHotLevel) {
		this.subjectHotLevel = subjectHotLevel;
	}
	public void setSubjectScore(int subjectScore) {
		this.subjectScore = subjectScore;
	}
	public List<Integer> getAttitudeSplit() {
		return attitudeSplit;
	}
	public void setAttitudeSplit(List<Integer> attitudeSplit) {
		this.attitudeSplit = attitudeSplit;
	}
	public void setLocationDistribution(Map<String, Integer> locationDistribution) {
		this.locationDistribution = locationDistribution;
	}
	public void setTotalTweet(int totalTweet) {
		this.totalTweet = totalTweet;
	}
	public List<StatusVO> getTop10GoodStatus() {
		return top10GoodStatus;
	}
	public void setTop10GoodStatus(List<StatusVO> top10GoodStatus) {
		this.top10GoodStatus = top10GoodStatus;
	}
	public List<StatusVO> getTop5BadStatus() {
		return top5BadStatus;
	}
	public void setTop5BadStatus(List<StatusVO> top5BadStatus) {
		this.top5BadStatus = top5BadStatus;
	}
}
