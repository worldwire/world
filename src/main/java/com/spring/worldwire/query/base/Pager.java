package com.spring.worldwire.query.base;

/**
 * Created by luxun on 2018/4/21.
 */
public class Pager {
    private Integer pageSize = 10;

    private Integer pageNo;

    private int pageStart;

    private int pageCount;

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

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageStart() {
        return pageNo == null ? null : (pageNo - 1) * pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
