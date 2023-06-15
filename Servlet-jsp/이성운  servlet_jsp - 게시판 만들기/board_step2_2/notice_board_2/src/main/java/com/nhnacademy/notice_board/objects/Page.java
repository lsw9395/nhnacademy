package com.nhnacademy.notice_board.objects;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

    int pageNumber;
    int pageSize;
    int totalPageCount;
    long totalCount;
    List<T> List = new ArrayList<>();

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
        return List;
    }

    public void setList(List<T> List) {
        this.List = List;
    }
}
