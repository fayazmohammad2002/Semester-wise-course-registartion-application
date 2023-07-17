package com.example.idpbdcheck;
public class Model {
    private String imageUrl;
    public Model(String imageUrl)
    {
        this.imageUrl=imageUrl;
        setImageUrl(imageUrl);
        getImageUrl();
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}