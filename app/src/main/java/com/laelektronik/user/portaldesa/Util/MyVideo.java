package com.laelektronik.user.portaldesa.Util;

import java.io.Serializable;

/**
 * Created by user on 12/14/2017.
 */

public class MyVideo implements Serializable{
    String VideoId, tittle, thumbnails, tanggal;

    public MyVideo() {
    }

    public String getVideoId() {
        return VideoId;
    }

    public void setVideoId(String videoId) {
        VideoId = videoId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(String thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
