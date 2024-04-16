package com.parth.importer.services;

import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.model.StudentEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private static String POST_STUDENTS_URL = "http://localhost:8081/students";

    public List<StudentEntity> addStudents(List<StudentAdditionDto> studentAdditionDtos, String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        List<StudentEntity> res = new ArrayList<>();
        for (StudentAdditionDto studentAdditionDto: studentAdditionDtos){
            HttpEntity<StudentAdditionDto> requestEntity = new HttpEntity<>(studentAdditionDto, httpHeaders);
            res.add(new RestTemplate().exchange(POST_STUDENTS_URL, HttpMethod.POST, requestEntity, StudentEntity.class).getBody());
        }

        return res;
    }
}
