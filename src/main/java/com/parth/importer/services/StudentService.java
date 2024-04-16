package com.parth.importer.services;

import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.dto.StudentDisplayDto;
import com.parth.importer.mapstructMapper.StudentMapper;
import com.parth.importer.model.StudentEntity;
import com.parth.importer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    LogRepository logRepository;

    @Autowired
    StudentMapper studentMapper;

    private static String POST_STUDENTS_URL = "http://localhost:8081/students";

    public List<StudentDisplayDto> addStudents(List<StudentAdditionDto> studentAdditionDtos, String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        List<StudentDisplayDto> studentDisplayDtos = new ArrayList<>();
        StudentEntity studentEntity;
        long statusCodeValue;
        for (StudentAdditionDto studentAdditionDto: studentAdditionDtos){
            HttpEntity<StudentAdditionDto> requestEntity = new HttpEntity<>(studentAdditionDto, httpHeaders);
            ResponseEntity<StudentEntity> responseEntity = new RestTemplate().exchange(POST_STUDENTS_URL, HttpMethod.POST, requestEntity, StudentEntity.class);
            studentEntity = responseEntity.getBody();
            HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
            statusCodeValue = statusCode.value();
            String statusMessage = statusCode.getReasonPhrase();
            logRepository.addLogs(studentEntity,statusCodeValue,statusMessage);
            studentDisplayDtos.add(studentMapper.convertStudentEntityToStudentDisplayDto(studentEntity));
        }
        return studentDisplayDtos;
    }
}
