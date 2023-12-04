package com.example.Angler.core.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.example.Angler.core.entity.StudentDetailsEntity;
import com.example.Angler.core.repository.StudentsRepo;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.EntityManager;

@Service
public class StudentsCrudServiceImpl implements StudentsCrudService {

    @Autowired
    StudentsRepo studentsRepo;

    @Autowired
    DataSource dataSource;

    @Override
    public StudentDetailsEntity createStudent(StudentDetailsEntity studentDetails) throws SQLException {

        StudentDetailsEntity savedResponse = studentsRepo.save(studentDetails);

        return savedResponse;
    }

    @Override
    public List<StudentDetailsEntity> getAllStudentsDetails() throws SQLException {
        List<StudentDetailsEntity> studentDetails = studentsRepo.findAll();
        studentDetails.forEach(student ->{
           student.setDateOfBirth(StudentsCrudServiceImpl.convertDateFormat(student.getDateOfBirth(), "yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy"));
        });
        return studentDetails;
    }

    @Override
    public String deleteStudent(String studentCode) throws Exception {
        try {
            studentsRepo.deleteById(studentCode);
            return studentCode;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public BigDecimal checkEmailExist(String emailCode) throws Exception {

        try {
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource)
                    .withProcedureName("Bpc_Check_Email_Exsist");

            if (jdbcCall != null) {

                SqlParameterSource namedParameterSource = new MapSqlParameterSource()
                        .addValue("P_Email", emailCode);

                jdbcCall.declareParameters(new SqlParameter("P_Email", Types.VARCHAR),
                        new SqlOutParameter("P_Count", Types.NUMERIC));

                Map<String, Object> result = jdbcCall.execute(namedParameterSource);
                BigDecimal emailCount = (BigDecimal) result.get("P_Count");
                return emailCount;
            }
            return null;

        } catch (Exception e) {
            throw new Exception();
        }

    }
    static String ORACLE_dd_MMM_yy = "dd-MMM-yy";

    public static String convertDateFormat(String inputDateValue, String inputFormat, String outputFormat) {

          if (inputDateValue != null) {
            int dateLength = inputDateValue.length();
            if (dateLength > 10 && !outputFormat.equals(ORACLE_dd_MMM_yy)) {
              try {

                // Parse the input date string using the input pattern
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
                LocalDateTime dateTime = LocalDateTime.parse(inputDateValue, inputFormatter);

                // Format the LocalDateTime object to the desired output pattern
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
                String outputDate = dateTime.format(outputFormatter);

                return outputDate;

              } catch (Exception e) {
                e.printStackTrace();
              }
            } else if (outputFormat.equals(ORACLE_dd_MMM_yy)) {

              try {
                // Simple Date input Format					
                SimpleDateFormat inputFormatDate = new SimpleDateFormat(inputFormat.toString());
                Date date = inputFormatDate.parse(inputDateValue);

                // Simple Date output Format			
                SimpleDateFormat outputFormatDate = new SimpleDateFormat(outputFormat.toString());
                String outputDate = outputFormatDate.format(date);
                return outputDate;
              } catch (Exception e) {
                e.printStackTrace();
              }

            }
          }

          return null;
        }

}
