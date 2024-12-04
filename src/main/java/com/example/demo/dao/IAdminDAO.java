package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.Admin;

@Mapper
public interface IAdminDAO {

	public Admin adminLogin(@Param ("adminId") String adminId,@Param ("adminPw") String adminPw);
	
}
