package com.ardroid.petroom.data;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GalaxyPicture {
    @SerializedName("media_type")
    private String mediaType;
    private String copyright;
    private Date date;
    private String explantion;
    private String hdurl;
    private String title;
    private String url;

    public GalaxyPicture(String copyright, Date date, String explantion, String hdurl, String mediaType, String title, String url) {
        this.copyright = copyright;
        this.date = date;
        this.explantion = explantion;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.title = title;
        this.url = url;
    }

    public GalaxyPicture(String explantion, String hdurl, String title, String url) {
        this.explantion = explantion;
        this.hdurl = hdurl;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "GalaxyPicture{" +
                "mediaType='" + mediaType + '\'' +
                ", copyright='" + copyright + '\'' +
                ", date=" + date +
                ", explantion='" + explantion + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getExplantion() {
        return explantion;
    }

    public void setExplantion(String explantion) {
        this.explantion = explantion;
    }

    public String getHdurl() {
        return hdurl;
    }

    public void setHdurl(String hdurl) {
        this.hdurl = hdurl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

