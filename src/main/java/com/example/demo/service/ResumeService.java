package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ResumeDao;
import com.example.demo.dto.ResumeDto;

@Service
public class ResumeService {

    @Autowired
    private ResumeDao resumeDao;

    @Transactional
    public void registerResume(ResumeDto resumeDto) {
        try {
            // 1. 이력서 기본 정보 저장
            resumeDao.insertResume(resumeDto);
            Integer resumeNo = resumeDto.getResumeNo(); // 생성된 이력서 번호 가져오기

            // 2. 학력 정보 저장
            if (resumeDto.getEducationList() != null) {
                resumeDto.getEducationList().forEach(education -> {
                    education.setResumeNo(resumeNo);
                    resumeDao.insertEducation(education);
                });
            }

            // 3. 경력 정보 저장
            if (resumeDto.getExperienceList() != null) {
                resumeDto.getExperienceList().forEach(experience -> {
                    experience.setResumeNo(resumeNo);
                    resumeDao.insertExperience(experience);
                });
            }

            // 4. 자격증 정보 저장
            if (resumeDto.getCertificationList() != null) {
                resumeDto.getCertificationList().forEach(certification -> {
                    certification.setResumeNo(resumeNo);
                    resumeDao.insertCertification(certification);
                });
            }

            // 5. 병역 정보 저장 (선택 사항)
            if (resumeDto.getMilitary() != null) {
                resumeDto.getMilitary().setResumeNo(resumeNo);
                resumeDao.insertMilitary(resumeDto.getMilitary());
            }

            // 6. 자기소개서 저장
            if (resumeDto.getSelfIntroduction() != null) {
                resumeDto.getSelfIntroduction().setResumeNo(resumeNo);
                resumeDao.insertSelfIntroduction(resumeDto.getSelfIntroduction());
            }

        } catch (Exception e) {
            throw new RuntimeException("이력서 등록 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
