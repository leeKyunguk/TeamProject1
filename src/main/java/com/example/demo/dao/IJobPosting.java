package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.JobPosting;

@Mapper
public interface IJobPosting {
	public List<JobPosting> getList();
	public void reglist(JobPosting jobposting);
	public void dellist(int postNo);
	public JobPosting getJobPostingByPostNo(int postNo);
	public void updateJobPosting(JobPosting jobPosting);
    public void deleteJobPosting(int postNo);           
	public boolean isBookmarked(String comName, int userNo);
	public void deleteBookmark(String comName, int userNo);
	public void addBookmark(String comName, int userNo);
}
