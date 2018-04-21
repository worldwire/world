package com.spring.worldwire.query.base;

/**
 * Created by luxun on 2018/4/21.
 */
public class Pager {
    private Integer pageSize ;

    private Integer pageNo;

    public Pager(Integer pageSize) {
        this.pageSize = 10;
    }

    //    private int pageStart;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageStart() {
        return (pageSize - 1)*pageNo;
    }

}
