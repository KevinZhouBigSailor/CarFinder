package model;

public class SearchMetadata {
	private long max_id;
	private long since_id;
	private String query;
	private String next_results;
	
	public String getNext_results() {
		return next_results;
	}
	public void setNext_results(String next_results) {
		this.next_results = next_results;
	}
	private long count;
	
	public long getMax_id() {
		return max_id;
	}
	public void setMax_id(long max_id) {
		this.max_id = max_id;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getSince_id() {
		return since_id;
	}
	public void setSince_id(long since_id) {
		this.since_id = since_id;
	}
}
