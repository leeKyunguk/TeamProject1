package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Company;
import com.example.demo.dto.UserProfiles;
import com.example.demo.dto.Users;

@Mapper
public interface IUsersDAO {
	public Users userLogin(@Param ("usersId") String usersId, @Param ("password") String password);
	public UserProfiles userInfoLoad(@Param ("usersId") String usersId);
	public Company comInfoLoad(@Param ("usersId") String usersId);
    public int updateUserProfile(UserProfiles userProfiles);
    public int updateCompany(Company company);
}