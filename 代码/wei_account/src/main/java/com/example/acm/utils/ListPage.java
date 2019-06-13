package com.example.acm.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPage<T> {
    private int currentPage;
    private int nextPage;
    private int prevPage;
    private int pageSize;
    private int totalItems;
    private boolean hasNext;
    private boolean hasPrev;
    private boolean isFirst;
    private boolean isEnd;
    private boolean isCurrent;
    private int from;
    private int totalPage;
    private T items;
    private String parentID;
    private String path;
    private int isSetting = 0;
    private int isManagement = 0;

    ListPage(int validedCurrentPage, int pageSize, int totalItems, T items) {
        this.currentPage = validedCurrentPage < 0?1:validedCurrentPage;
        this.pageSize = pageSize < 0?10:pageSize;
        this.totalItems = totalItems < 0?0:totalItems;
        this.items = items;
        this.initState();
    }

    private void initState() {
        if(this.totalItems == 0) {
            this.currentPage = 0;
        }

        this.totalPage = this.totalItems / this.pageSize + (this.totalItems % this.pageSize == 0?0:1);
        this.hasNext = this.currentPage < this.totalPage;
        if(this.hasNext) {
            this.nextPage = this.currentPage + 1;
        }

        this.hasPrev = this.currentPage > 1;
        if(this.hasPrev) {
            this.prevPage = this.currentPage - 1;
        }

        this.from = (this.currentPage - 1 < 0?0:this.currentPage - 1) * this.pageSize;
        this.isFirst = this.currentPage == 1 || this.currentPage == 0;
        this.isEnd = this.currentPage == this.totalPage;
        this.isCurrent = this.currentPage == this.currentPage;
    }

    public String toString() {
        return "currentPage=" + this.currentPage + "\n" + "pageSize=" + this.pageSize + "\n" + "totalPage=" + this.totalPage + "\n" + "hasNext=" + this.hasNext + "\n" + "hasPrev=" + this.hasPrev + "\n" + "from=" + this.from + "\n" + "isFirst=" + this.isFirst + "\n" + "isEnd=" + this.isEnd + "\n" + "isCurrent=" + this.isCurrent + "\n" + "totalItems=" + this.totalItems + "\n" + "parentID=" + this.parentID;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getNextPage() {
        return this.nextPage;
    }

    public int getPrevPage() {
        return this.prevPage;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public int getTotalItems() {
        return this.totalItems;
    }

    public boolean isHasNext() {
        return this.hasNext;
    }

    public boolean isHasPrev() {
        return this.hasPrev;
    }

    public boolean getIsFirst() {
        return this.isFirst;
    }

    public boolean getIsEnd() {
        return this.isEnd;
    }

    public boolean isCurrent() {
        return this.isCurrent;
    }

    public int getFrom() {
        return this.from;
    }

    public T getItems() {
        return this.items;
    }

    public String getParentID() {
        return this.parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static <T> ListPage<T> createListPage(int currentPage, int pageSize, int totalSize, T items) {
        int totalPage = calculateTotalPage(totalSize, pageSize);
        int validedCurrentPage = validCurrentPage(currentPage, totalPage);
        return new ListPage(validedCurrentPage, pageSize, totalSize, items);
    }

    public static int page2From(int page, int pageSize) {
        return page < 1?0:(page - 1) * pageSize;
    }

    public static int getValidPage(int wantPage, int totalSize, int pageSize) {
        int totalPage = calculateTotalPage(totalSize, pageSize);
        return validCurrentPage(wantPage, totalPage);
    }

    private static int calculateTotalPage(int totalSize, int pageSize) {
        int rawPage = totalSize / pageSize;
        return totalSize % pageSize == 0?rawPage:rawPage + 1;
    }

    private static int validCurrentPage(int currentPage, int totalPage) {
        return currentPage < 1?1:(currentPage > totalPage?totalPage:currentPage);
    }

    public static <O> List<O> getItemsOfPage(int ipage, int isize, List<O> lists) {
        if(lists != null && lists.size() != 0) {
            int page = ipage;
            int size = isize;
            if(ipage < 1) {
                page = 1;
            }

            if(isize <= 0) {
                size = 10;
            }

            int from = (page - 1) * size;
            if(from >= lists.size()) {
                from = lists.size() - size;
                if(from < 0) {
                    from = 0;
                }
            }

            int end = from + size;
            if(end >= lists.size()) {
                end = lists.size();
            }

            return lists.subList(from, end);
        } else {
            return null;
        }
    }

    public int getIsSetting() {
        return this.isSetting;
    }

    public void setIsSetting(int isSetting) {
        this.isSetting = isSetting;
    }

    public static void main(String[] args) {
        int currentPage = 1;
        int pageSize = 10;
        int totalSize = 98;
        Map<String, String> items = new HashMap();
        ListPage<Map<String, String>> listPage = createListPage(currentPage, pageSize, totalSize, items);
        System.out.println(listPage);
        System.out.println(page2From(3, 10));
        List<String> testList = new ArrayList();

        for(int i = 0; i < 97; ++i) {
            testList.add(String.valueOf(i));
        }

        System.out.println(getItemsOfPage(2, 10, testList));
    }

    public void setIsManagement(int isManagement) {
        this.isManagement = isManagement;
    }

    public int getIsManagement() {
        return this.isManagement;
    }
}
