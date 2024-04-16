package com.parth.importer.controller;

import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.model.StudentEntity;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private static String POST_STUDENTS_URL = "http://localhost:8081/students";

    @PostMapping
    public List<StudentEntity> addStudents(@RequestBody List<StudentAdditionDto> studentAdditionDtos, @RequestHeader("Authorization") String token){
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
