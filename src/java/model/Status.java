package model;

public class Status {
	private long id;
	private String text;
	private String source;
	private Long retweet_count;
	private long favorite_count;
	private User user;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Long getRetweet_count() {
		return retweet_count;
	}
	public void setRetweet_count(Long retweet_count) {
		this.retweet_count = retweet_count;
	}
	public long getFavorite_count() {
		return favorite_count;
	}
	public void setFavorite_count(long favorite_count) {
		this.favorite_count = favorite_count;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
