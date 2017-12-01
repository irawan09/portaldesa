package com.laelektronik.user.portaldesa.Util;

import java.io.Serializable;

/**
 * Created by user on 12/1/2017.
 */

public class Post implements Serializable {
    private int id;
    private String imgUrl;
    private String titlePost;
    private String post;
    private String date;
    private String file;

    public Post() {
    }

    public Post(int id, String imgUrl, String titlePost, String post, String date) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.titlePost = titlePost;
        this.post = post;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
