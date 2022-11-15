package com.example.springjdbc.dao;

import com.example.springjdbc.dto.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    List<Employee> selectClient();
}
