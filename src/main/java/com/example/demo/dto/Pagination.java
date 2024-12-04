package com.example.demo.dto;

public class Pagination {

    private int currentPage; // 현재 페이지
    private int totalPages;  // 총 페이지 수
    private int pageSize;    // 한 페이지당 게시글 수
    private int totalItems;  // 총 게시글 수

    public Pagination(int currentPage, int totalItems, int pageSize) {
        this.currentPage = Math.max(1, currentPage); // 최소 1 보장
        this.pageSize = Math.max(1, pageSize);       // 최소 1 보장
        this.totalItems = Math.max(0, totalItems);   // 음수 방지
        this.totalPages = (int) Math.ceil((double) this.totalItems / this.pageSize);
    }

    public int getOffset() {
        int offset = (currentPage - 1) * pageSize;
        return Math.min(offset, Math.max(0, totalItems - pageSize)); // 범위 초과 방지
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = Math.max(1, currentPage);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = Math.max(1, pageSize);
        this.totalPages = (int) Math.ceil((double) this.totalItems / this.pageSize); // 페이지 재계산
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = Math.max(0, totalItems);
        this.totalPages = (int) Math.ceil((double) this.totalItems / this.pageSize); // 페이지 재계산
    }
}


