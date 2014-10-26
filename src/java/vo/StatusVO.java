package vo;

import java.io.Serializable;

public class StatusVO implements Serializable{

	private static final long serialVersionUID = 3395523867815389564L;
	private String text;
	private int polarity;
	private int id;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getPolarity() {
		return polarity;
	}
	public void setPolarity(int polarity) {
		this.polarity = polarity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
