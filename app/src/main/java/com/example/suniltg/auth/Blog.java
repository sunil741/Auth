package com.example.suniltg.auth;

/**
 * Created by sunil TG on 10/9/2016.
 */
 class Blog {
    private String title;
    private String desc;
    private String user;
    private String image;

public Blog()
{

}
    public Blog(String user,String title, String desc, String image) {
        this.user=user;
        this.title = title;
        this.desc = desc;
        this.image=image;
    }


    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
    public String getUser() {
        return user;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
