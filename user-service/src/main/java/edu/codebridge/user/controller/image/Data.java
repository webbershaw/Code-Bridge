package edu.codebridge.user.controller.image;

public class Data{
    String url;
    String alt;
    String href;

    public Data(String url) {
        this.url = url;
    }

    public Data(String url, String alt, String href) {
        this.url = url;
        this.alt = alt;
        this.href = href;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
