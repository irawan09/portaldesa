package com.laelektronik.user.portaldesa.Util;

import java.io.Serializable;

/**
 * Created by user on 12/14/2017.
 */

public class MyPDF implements Serializable {
    String url, tittle, thumbnails, tanggal;

    public MyPDF() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
