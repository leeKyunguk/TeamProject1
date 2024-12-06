package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Applicant;
import com.example.demo.dto.JobPosting;
import com.example.demo.dto.UscBookMark;

@Mapper
public interface IJobPosting {
	public List<JobPosting> getList();
	public void reglist(JobPosting jobposting);
	public void dellist(int postNo);
	public JobPosting getJobPostingByPostNo(int postNo);
<<<<<<< HEAD
	public void updateJobPosting(JobPosting jobPosting);
    public void deleteJobPosting(int postNo);
    public void isBookmarked(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public void addBookmark(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public void deleteBookmark(@Param("comNo") int comNo, @Param("userNo") int userNo);
    public int checkBookmarkStatus(@Param("comNo") int comNo, @Param("userNo") int userNo);
}
=======
	public void updateJobPosting(JobPosting jobPosting); // 공고 수정
    public void deleteJobPosting(int postNo);           // 공고 삭제
    public List<JobPosting> getJobPostingsByPostUsersId(String usersid);
    public List<Applicant> getapplicantlist(int postNo);
    public void applicatePost(Applicant applicant);
    public void delApplicant(Applicant applicant);
}
>>>>>>> f3aab77fa452a4fcf3acd79dc1b2e572c38b5076
