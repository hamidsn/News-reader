package com.example.newsreader;

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
    	//if(title == null) return "nnull";
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageHref() {
    	//if(imageHref == null) return "nnull";
        return imageHref;
    }

    public void setImageHref(String imageHref) {
        this.imageHref = imageHref;
    }

	public String getDescription() {
    	//if(description == null) return "nnull";
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}