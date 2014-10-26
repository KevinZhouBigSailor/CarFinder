package com.carsFinder.twitter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import vo.StatusVO;
import model.DataPackage;
import model.Status;

public class Aggregation {
	
	public ArrayList<StatusVO> getTop10Tweets(DataPackage dp) {
		ArrayList<StatusVO> top = new ArrayList<StatusVO>();
		ArrayList<Status> tmp = new ArrayList<Status>(dp.getStatuses());
		if (tmp == null || tmp.size() == 0) {
			return top;
		}
		HashSet<String> set = new HashSet<String>();
		Collections.sort(tmp, new MyComparator());
		int i = 0;
		HashMap<Long,Integer> map = dp.getMap();
		int count = 0;
		while (i < tmp.size()) {
			Status status = tmp.get(i);
			if(map.get(status.getId())==4){
				if(!set.contains(status.getText())){
					set.add(status.getText());
					StatusVO statusVO = new StatusVO();
					statusVO.setText(status.getText());
					statusVO.setPolarity(map.get(status.getId()));
					top.add(statusVO);
					count++;
					if(count==5){
						break;
					}
				}
			}
			i++;
		}
		return top;
	}
	
	public ArrayList<StatusVO> getTop5BandStatus(DataPackage dp) {
		ArrayList<StatusVO> top = new ArrayList<StatusVO>();
		ArrayList<Status> tmp = new ArrayList<Status>(dp.getStatuses());
		if (tmp == null || tmp.size() == 0) {
			return top;
		}
		Collections.sort(tmp, new MyComparator());
		int i = 0;
		HashSet<String> set = new HashSet<String>();
		HashMap<Long,Integer> map = dp.getMap();
		int count = 0;
		while (i < tmp.size() && count < 5) {
			Status status = tmp.get(i);
			if(map.get(status.getId())==0){
				if(!set.contains(status.getText())){
					set.add(status.getText());
					StatusVO statusVO = new StatusVO();
					statusVO.setText(status.getText());
					statusVO.setPolarity(map.get(status.getId()));
					System.out.println(status.getText()+status.getId());
					top.add(statusVO);
					count++;
				}
			}
			i++;
		}
		return top;
	}
	
	
	public int getTotalTweet(DataPackage dp) {
		int count = -1;
		if (dp == null) {
			return count;
		}
		ArrayList<Status> status = new ArrayList<Status>(dp.getStatuses());
		if (status == null || status.size() == 0) {
			return count;
		}
		count = status.size();
		return count;
	}
	
	private class MyComparator implements Comparator<Status>{
		@Override
		public int compare(Status o1, Status o2) {
			int o1Hot = (int) (o1.getFavorite_count() + o1.getRetweet_count());
			int o2Hot = (int) (o2.getFavorite_count() + o2.getRetweet_count());
			return o1Hot - o2Hot;
		}
	}
	

}
