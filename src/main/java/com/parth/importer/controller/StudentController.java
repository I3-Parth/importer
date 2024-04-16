package com.parth.importer.controller;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.dto.StudentDisplayDto;
import com.parth.importer.model.StudentEntity;
import com.parth.importer.services.DummyService;
import com.parth.importer.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    DummyService dummyService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ROLE_OFFICE_ADMIN')")
    public List<StudentEntity> addStudents(@Valid @RequestBody List<StudentAdditionDto> studentAdditionDtos, @RequestHeader("Authorization") String token){
        return dummyService.dummyServiceCall(studentAdditionDtos, token);
    }
}
