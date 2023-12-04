package com.example.Angler.core.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Angler.core.entity.StudentDetailsEntity;
import com.example.Angler.core.responseDo.ResponseDo;
import com.example.Angler.core.service.StudentsCrudService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONObject;

@RestController
@CrossOrigin(origins = "${client.url}")
public class StudentsCrudController {

    @Autowired
    ResponseDo responseDo;

    @Autowired
    StudentsCrudService studentsCrudService;

    @PostMapping("/register-student")
    ResponseDo createStudent(@RequestBody StudentDetailsEntity studentDetailsEntity, final HttpServletRequest request,
            final HttpServletResponse response) {

        try {
            StudentDetailsEntity studentDetailsresponse = studentsCrudService.createStudent(studentDetailsEntity);
            return responseDo.setSuccessResponse(studentDetailsresponse);
        } catch (Exception e) {
            e.printStackTrace();
            return responseDo.setFailureResponse(e.toString());
        }

    }

    @GetMapping("/getAllStudentsDetails")
    ResponseDo getStudentsDetails(final HttpServletRequest request, final HttpServletResponse response) {
        try {
            List<StudentDetailsEntity> studentDetailsresponse = studentsCrudService.getAllStudentsDetails();
            return responseDo.setSuccessResponse(studentDetailsresponse);
        } catch (Exception e) {
            e.printStackTrace();
            return responseDo.setFailureResponse(e.toString());
        }
    }

    @DeleteMapping("/deleteStudent/{studentCode}")
    ResponseDo deleteStudent(@PathVariable("studentCode") String studentCode, final HttpServletRequest request,
            final HttpServletResponse response) {

        try {
            String responseCode = studentsCrudService.deleteStudent(studentCode);
            return responseDo.setSuccessResponse(responseCode);
        } catch (Exception e) {
            e.printStackTrace();
            return responseDo.setFailureResponse(e.toString());
        }
    }

    @PutMapping("/updateStudent/{studentCode}")
    ResponseDo updateStudent(@RequestBody StudentDetailsEntity studentDetailsEntity, final HttpServletRequest request,
            final HttpServletResponse response) {

        try {
            StudentDetailsEntity studentDetailsresponse = studentsCrudService.createStudent(studentDetailsEntity);
            return responseDo.setSuccessResponse(studentDetailsresponse);
        } catch (Exception e) {
            e.printStackTrace();
            return responseDo.setFailureResponse(e.toString());
        }
    }

    @PostMapping("/checkEmailExistinProcedure")
    ResponseDo checkEmailExistFromProcedure(@RequestBody JSONObject json, final HttpServletRequest request,
            final HttpServletResponse response) {

        try {
            String email = json.getAsString("emailId");
            BigDecimal emailCount = studentsCrudService.checkEmailExist(email);
            return responseDo.setSuccessResponse(emailCount);
        } catch (Exception e) {
            e.printStackTrace();
            return responseDo.setFailureResponse(e.toString());
        }

    }

}
