package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Applicant;
import com.example.demo.dto.JobPosting;

@Mapper
public interface IJobPosting {
	public List<JobPosting> getList(); // 전체 조회
	public void reglist(JobPosting jobposting); // 등록하기
	public void dellist(int postNo); // 삭제하기
	public JobPosting getJobPostingByPostNo(int postNo);
	public void updateJobPosting(JobPosting jobPosting); // 공고 수정
    public void deleteJobPosting(int postNo);           // 공고 삭제
    public List<JobPosting> getJobPostingsByPostUsersId(String usersId);
    public List<Applicant> getapplicantlist(int postNo);
    public void applicatePost(Applicant applicant);
    public void delApplicant(Applicant applicant);
    public List<Applicant> appliedJobList(Applicant applicant);
    public Applicant getApplicantByPostNoAndUserNo(Applicant applicant);
}