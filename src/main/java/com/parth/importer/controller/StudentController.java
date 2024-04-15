package com.parth.importer.controller;

import com.parth.importer.dto.StudentAdditionDto;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private static String POST_STUDENTS_URL = "http://localhost:8081/students";

    @PostMapping
    public String addStudents(@RequestBody List<StudentAdditionDto> studentAdditionDtos, @RequestHeader("Authorization") String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        String res = "";
        for (StudentAdditionDto studentAdditionDto: studentAdditionDtos){
            HttpEntity<StudentAdditionDto> requestEntity = new HttpEntity<>(studentAdditionDto, httpHeaders);
            res += new RestTemplate().exchange(POST_STUDENTS_URL, HttpMethod.POST, requestEntity, String.class).getBody();
        }

        return "Students added successfully \n\n"+res;
    }
}
