package com.example.Angler.core.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.example.Angler.core.entity.StudentDetailsEntity;

public interface StudentsCrudService {

    public StudentDetailsEntity createStudent(StudentDetailsEntity studentDetails) throws SQLException;

    public List<StudentDetailsEntity> getAllStudentsDetails() throws SQLException ;

    public String deleteStudent(String studentCode) throws Exception;

    public BigDecimal checkEmailExist(String emailCode) throws Exception;
} 
