package com.example.demo.service;

import com.example.demo.dao.SignUpDao;
import com.example.demo.dto.Company;
import com.example.demo.dto.UserProfiles;
import com.example.demo.dto.Users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final SignUpDao signUpDao;

    // 구직자 회원가입 처리
    public void registerJobSeeker(UserProfiles userProfiles) {
        // Users Table에 먼저 저장
        Users user = new Users();
        user.setUsersId(userProfiles.getUsersId());
        user.setPassword(userProfiles.getPassword());
        user.setEmail(userProfiles.getEmail());
        user.setRole("구직자");
        user.setAddress(userProfiles.getAddress());

        int result = signUpDao.insertUsers(user);
        if (result != 0) {
            signUpDao.insertJobSeeker(userProfiles);  // 구직자 데이터 저장
        }
    }

    // 기업 회원가입 처리
    public void registerCompany(Company company) {
        // Users Table에 저장
        Users user = new Users();
        user.setUsersId(company.getUsersId());
        user.setPassword(company.getPassword());
        user.setEmail(company.getEmail());
        user.setRole("기업");
        user.setAddress(company.getAddress());

        int result = signUpDao.insertUsers(user);
        if (result != 0) {
            signUpDao.insertCompany(company);  // 기업 데이터 저장
        }
    }

    // 구직자 중복 확인 로직
    public boolean isUserIdDuplicate(String usersId) {
        return signUpDao.existsByUserId(usersId);
    }

    public boolean isEmailDuplicate(String email) {
        return signUpDao.existsByEmail(email);
    }

    public boolean isNicknameDuplicate(String nickname) {
        return signUpDao.existsByNickname(nickname);
    }

    public boolean isResiNumberDuplicate(String resiNumber) {
        return signUpDao.existsByResiNumber(resiNumber);
    }

    public boolean isPhoneDuplicate(String phone) {
        return signUpDao.existsByPhone(phone);
    }

    // 기업 중복 확인 로직
    public boolean isBusinessNoDuplicate(String businessNo) {
        return signUpDao.existsByBusinessNo(businessNo);
    }

    public boolean isTelDuplicate(String tel) {
        return signUpDao.existsByTel(tel);
    }
}
