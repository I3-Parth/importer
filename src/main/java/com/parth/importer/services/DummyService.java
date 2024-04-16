package com.parth.importer.services;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.model.Log;
import com.parth.importer.model.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DummyService {

    @Autowired
    StudentService studentService;

    private static String POST_STUDENTS_URL = "http://localhost:8081/students";

    public List<StudentEntity> dummyServiceCall(List<StudentAdditionDto> studentAdditionDtos, String token) {
        List<LogDisplayDto> logDisplayDtos =  studentService.addLog(studentAdditionDtos);
        System.out.println("Display logs"+logDisplayDtos);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        List<StudentEntity> studentEntities = new ArrayList<>();

        for (StudentAdditionDto studentAdditionDto : studentAdditionDtos) {
            HttpEntity<StudentAdditionDto> requestEntity = new HttpEntity<>(studentAdditionDto, httpHeaders);
            ResponseEntity<StudentEntity> responseEntity = new RestTemplate().exchange(POST_STUDENTS_URL, HttpMethod.POST, requestEntity, StudentEntity.class);
            studentEntities.add(responseEntity.getBody());
        }
        return studentEntities;
    }
}
