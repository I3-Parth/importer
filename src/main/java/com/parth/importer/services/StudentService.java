package com.parth.importer.services;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.dto.StudentDisplayDto;
import com.parth.importer.mapstructMapper.LogMapper;
import com.parth.importer.model.Log;
import com.parth.importer.repository.LogRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
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
        Log log;
        for (StudentAdditionDto studentAdditionDto : studentAdditionDtos) {
            try {
                HttpEntity<StudentAdditionDto> requestEntity = new HttpEntity<>(studentAdditionDto, httpHeaders);
                ResponseEntity<StudentDisplayDto> responseEntity = new RestTemplate().exchange(POST_STUDENTS_URL, HttpMethod.POST, requestEntity, StudentDisplayDto.class);
                HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
                log = logMapper.convertDataToLogEntity(studentAdditionDto,(long) statusCode.value(), statusCode.getReasonPhrase());
                logRepository.addLogs(log);
                logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(log));
            }
            catch (HttpClientErrorException | HttpServerErrorException exception){
                log = logMapper.convertDataToLogEntity(studentAdditionDto, (long) exception.getStatusCode().value(), exception.getMessage());
                logRepository.addLogs(log);
                logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(log));
            }
            catch (Exception exception){
                log = logMapper.convertDataToLogEntity(studentAdditionDto, Long.parseLong((String) exception.getMessage().subSequence(0,3)), exception.getMessage());
                logRepository.addLogs(log);
                logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(log));
            }
        }
        return logDisplayDtos;
    }
}
