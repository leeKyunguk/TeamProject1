package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ICompany {
    
    @Select("SELECT comNo FROM company WHERE comName = #{comName}")
    int getComNoByComName(String comName);
}