package model;


public class Photo {
	private long id;
	
	private String owner;
	
	private String secret;
	
	private int server;
	
	private int farm;
	
	private String title;
	
	private boolean ispublic;
	
	private boolean isfriend;
	
	private boolean isfamily;

	private int views;
	
	public long getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public String getSecret() {
		return secret;
	}

	public int getServer() {
		return server;
	}

	public int getFarm() {
		return farm;
	}

	public String getTitle() {
		return title;
	}

	public boolean isIspublic() {
		return ispublic;
	}

	public boolean isIsfriend() {
		return isfriend;
	}

	public boolean isIsfamily() {
		return isfamily;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public void setServer(int server) {
		this.server = server;
	}

	public void setFarm(int farm) {
		this.farm = farm;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setIspublic(boolean ispublic) {
		this.ispublic = ispublic;
	}

	public void setIsfriend(boolean isfriend) {
		this.isfriend = isfriend;
	}

	public void setIsfamily(boolean isfamily) {
		this.isfamily = isfamily;
	}
}
