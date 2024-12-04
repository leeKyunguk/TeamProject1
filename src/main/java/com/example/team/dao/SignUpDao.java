package com.example.team.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.team.dto.Company;
import com.example.team.dto.UserProfiles;
import com.example.team.dto.Users;

@Mapper
public interface SignUpDao {

    // Users 테이블에 데이터 삽입 (공통)
    int insertUsers(@Param("users") Users users);

    // 구직자 데이터 삽입
    void insertJobSeeker(@Param("userProfiles") UserProfiles userProfiles);

    // 기업 데이터 삽입
    void insertCompany(@Param("company") Company company);

    // 사용자 ID 중복 확인
    boolean existsByUserId(@Param("usersId") String usersId);

    // 이메일 중복 확인
    boolean existsByEmail(@Param("email") String email);

    // 별칭 중복 확인
    boolean existsByNickname(@Param("nickname") String nickname);

    // 주민번호 중복 확인
    boolean existsByResiNumber(@Param("resiNumber") String resiNumber);

    // 연락처 중복 확인
    boolean existsByPhone(@Param("phone") String phone);

    // 사업자 등록번호 중복 확인
    boolean existsByBusinessNo(@Param("businessNo") String businessNo);

    // 기업 연락처 중복 확인
    boolean existsByTel(@Param("tel") String tel);
}
