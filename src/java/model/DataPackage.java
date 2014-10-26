package model;

import java.util.HashMap;
import java.util.List;

public class DataPackage {
	private List<Status> statuses;
	private HashMap<Long,Integer> map;
	
	public List<Status> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
	public HashMap<Long, Integer> getMap() {
		return map;
	}
	public void setMap(HashMap<Long, Integer> map) {
		this.map = map;
	}
}
