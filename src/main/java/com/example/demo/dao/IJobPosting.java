package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.JobPosting;
import com.example.demo.dto.UscBookMark;

@Mapper
public interface IJobPosting {
	public List<JobPosting> getList();
	public void reglist(JobPosting jobposting);
	public void dellist(int postNo);
	public JobPosting getJobPostingByPostNo(int postNo);
	public void updateJobPosting(JobPosting jobPosting);
    public void deleteJobPosting(int postNo);
    public void isBookmarked(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public void addBookmark(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public void deleteBookmark(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public int checkBookmarkStatus(@Param("comNo") int comNo, @Param("userNo") int userNo);
}
