package com.example.newsreader;
// created by h_sedghy

import com.google.gson.annotations.SerializedName;
public class News {

    @SerializedName("title")
    private String title;
    @SerializedName("imageHref")
    private String imageHref;
    @SerializedName("description")
    private String description;

    public News(String title, String imageHref,  String description) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageHref() {
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}