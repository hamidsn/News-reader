package com.example.newsreader;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class NewsSubject {

	@SerializedName("title")
	private String title;
	@SerializedName("rows")
	private ArrayList<News> rows;

	public NewsSubject(ArrayList<News> rows, String title) {
		this.rows = rows;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<News> getNews() {
		return rows;
	}

	public void setNews(ArrayList<News> news) {
		this.rows = news;
	}
}