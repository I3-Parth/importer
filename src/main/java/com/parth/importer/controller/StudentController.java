package com.parth.importer.controller;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ROLE_OFFICE_ADMIN')")
    public List<LogDisplayDto> addStudents(@Valid @RequestBody List<StudentAdditionDto> studentAdditionDtos, @RequestHeader("Authorization") String token){
        return studentService.addStudents(studentAdditionDtos, token);
    }
}
