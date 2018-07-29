package com.spring.worldwire.model;

/**
 * @Auther pg
 * @Date create in 22:09 2018/7/25
 */
public class Country {

    private String ename;
    private String cname;
    private String url;

    public Country(String ename, String cname, String url) {
        this.ename = ename;
        this.cname = cname;
        this.url = url;
    }

    public String getEname() {
        return ename;
    }

    public String getCname() {
        return cname;
    }

    public String getUrl() {
        return url;
    }
}
