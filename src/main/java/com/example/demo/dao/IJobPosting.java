package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Applicant;
import com.example.demo.dto.JobPosting;

@Mapper
public interface IJobPosting {
	public List<JobPosting> getList();
	public void reglist(JobPosting jobposting);
	public void dellist(int postNo);
	public JobPosting getJobPostingByPostNo(int postNo);
	public void updateJobPosting1(JobPosting jobPosting);
    public void deleteJobPosting1(int postNo);
    public void isBookmarked(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public void addBookmark(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public void deleteBookmark(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public int checkBookmarkStatus(@Param("comNo") int comNo, @Param("userNo") int userNo);
	public void updateJobPosting(JobPosting jobPosting);
    public void deleteJobPosting(int postNo);
    public List<JobPosting> getJobPostingsByPostUsersId(String usersid);
    public List<Applicant> getapplicantlist(int postNo);
    public void applicatePost(Applicant applicant);
    public void delApplicant(Applicant applicant);
} 
