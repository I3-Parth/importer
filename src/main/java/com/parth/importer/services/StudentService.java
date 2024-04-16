package com.parth.importer.services;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.mapstructMapper.LogMapper;
import com.parth.importer.model.Log;
import com.parth.importer.model.StudentEntity;
import com.parth.importer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    LogRepository logRepository;

    @Autowired
    LogMapper logMapper;

    private static String POST_STUDENTS_URL = "http://localhost:8081/students";

    public List<LogDisplayDto> addStudents(List<StudentAdditionDto> studentAdditionDtos, String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);

        List<LogDisplayDto> logDisplayDtos = new ArrayList<>();
        StudentEntity studentEntity;
        Log log;
        long statusCodeValue;
        for (StudentAdditionDto studentAdditionDto : studentAdditionDtos) {
            HttpEntity<StudentAdditionDto> requestEntity = new HttpEntity<>(studentAdditionDto, httpHeaders);
            ResponseEntity<StudentEntity> responseEntity = new RestTemplate().exchange(POST_STUDENTS_URL, HttpMethod.POST, requestEntity, StudentEntity.class);
            studentEntity = responseEntity.getBody();
            HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
            statusCodeValue = statusCode.value();
            String statusMessage = statusCode.getReasonPhrase();
            log = logMapper.convertDataToLogEntity(studentEntity, statusCodeValue, statusMessage);
            logRepository.addLogs(log);
            logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(log));
        }
        return logDisplayDtos;
    }

    public List<LogDisplayDto> addDummyLogs(List<StudentAdditionDto> studentAdditionDtos) {
        return addLog(studentAdditionDtos);
    }

    @Transactional
    public List<LogDisplayDto> addLog(List<StudentAdditionDto> studentAdditionDtos) {
        Log log;
        List<LogDisplayDto> logDisplayDtos = new ArrayList<>();
        for (StudentAdditionDto studentAdditionDto : studentAdditionDtos) {
            StudentEntity studentEntity = new StudentEntity(studentAdditionDto.getName(), studentAdditionDto.getAge(), studentAdditionDto.getEmail(), studentAdditionDto.getCity());
            log = logMapper.convertDataToLogEntity(studentEntity, 200L, "OK");
            logRepository.addLogs(log);
            logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(log));
        }
        return logDisplayDtos;
    }

}
