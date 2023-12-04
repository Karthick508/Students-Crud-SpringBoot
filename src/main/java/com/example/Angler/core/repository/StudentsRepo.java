package com.example.Angler.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Angler.core.entity.StudentDetailsEntity;
import com.example.Angler.core.query.StudentsQuery;

import org.springframework.data.jpa.repository.Query;

@Repository
public interface StudentsRepo extends JpaRepository<StudentDetailsEntity, String> {

}