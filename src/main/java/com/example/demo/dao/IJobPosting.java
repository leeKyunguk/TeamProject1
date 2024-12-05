package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.JobPosting;

@Mapper
public interface IJobPosting {
	public List<JobPosting> getList(); // 전체 조회
	public void reglist(JobPosting jobposting); // 등록하기
	public void dellist(int postNo); // 삭제하기
	public JobPosting getJobPostingByPostNo(int postNo);
	public void updateJobPosting(JobPosting jobPosting); // 공고 수정
    public void deleteJobPosting(int postNo);           // 공고 삭제
    public List<JobPosting> getJobPostingsByPostUsersId(String usersid);
}