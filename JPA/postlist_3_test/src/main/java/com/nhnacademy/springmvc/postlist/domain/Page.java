package com.nhnacademy.springmvc.postlist.domain;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    int pageNumber;
    int pageSize;
    int totalPageCount;
    long totalCount;
    List<T> list = new ArrayList<>();

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public java.util.List<T> getList() {
        return list;
    }

    public void setList(List<T> List) {
        this.list = List;
    }
}
