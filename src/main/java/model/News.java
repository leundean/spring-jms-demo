package model;

import java.util.List;

public class News {
	private int id;
	private String title;
	private List<ExtraNews> extra;
	public News() {}
	public News(int id, String title, List<ExtraNews> extra) {
		this.id = id;
		this.title = title;
		this.extra = extra;
	}	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public List<ExtraNews> getExtra() {
		return extra;
	}

	public void setExtra(List<ExtraNews> extra) {
		this.extra = extra;
	}
	/*
	@Override
	public String toString() {
		return id + ", " + title;
	}

	 */
}