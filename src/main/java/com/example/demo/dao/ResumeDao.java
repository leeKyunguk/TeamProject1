package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Certification;
import com.example.demo.dto.Education;
import com.example.demo.dto.Experience;
import com.example.demo.dto.Military;
import com.example.demo.dto.Resume;
import com.example.demo.dto.ResumeDto;
import com.example.demo.dto.SelfIntroduction;

@Mapper
public interface ResumeDao {
	 // 이력서 기본 정보 삽입
    void insertResume(ResumeDto resomeDto);
    
    // 학력 정보 삽입
    void insertEducation(Education education);
    
    // 경력 정보 삽입
    void insertExperience(Experience experience);
    
    // 자격증 정보 삽입
    void insertCertification(Certification certification);
    
    // 병역 정보 삽입
    void insertMilitary(Military military);
    
    // 자기소개서 삽입
    void insertSelfIntroduction(SelfIntroduction selfIntroduction);

}
