package com.example.Angler.core.entity;


import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "STUDENTS_DETAILS")
@Data
public class StudentDetailsEntity {

    @Id
    @Column(name = "V_STU_CODE", nullable = false)
    private String studentCode;

    @Column(name = "V_STU_FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "V_STU_LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "V_STU_DEPT", nullable = false)
    private String department;

    @Column(name = "V_GENDER", nullable = false)
    private String gender;

    @Column(name = "V_STU_EMAIL_ID", nullable = false)
    private String emailId;

    @Column(name = "D_STU_DOB")
    private String  dateOfBirth;

}
