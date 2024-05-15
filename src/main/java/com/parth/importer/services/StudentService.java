package com.parth.importer.services;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.dto.StudentDisplayDto;
import com.parth.importer.mapstructMapper.LogMapper;
import com.parth.importer.mapstructMapper.StudentMapper;
import com.parth.importer.model.LogEntity;
import com.parth.importer.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class StudentService {

    @Autowired
    LogRepository logRepository;

    @Autowired
    LogMapper logMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    KafkaTemplate<String, StudentAdditionDto> template;

    private static String POST_STUDENTS_URL = "http://localhost:8081/students";

    public List<LogDisplayDto> addStudents(List<StudentAdditionDto> studentAdditionDtos) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String token = getToken();
        httpHeaders.set("Authorization", "Bearer " + token);

        List<LogDisplayDto> logDisplayDtos = new ArrayList<>();
        LogEntity logEntity;
        for (StudentAdditionDto studentAdditionDto : studentAdditionDtos) {
            try {
                HttpEntity<StudentAdditionDto> requestEntity = new HttpEntity<>(studentAdditionDto, httpHeaders);
                ResponseEntity<StudentDisplayDto> responseEntity = new RestTemplate().exchange(POST_STUDENTS_URL, HttpMethod.POST, requestEntity, StudentDisplayDto.class);
                HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
                logEntity = logMapper.convertDataToLogEntity(studentAdditionDto,(long) statusCode.value(), statusCode.getReasonPhrase());
                logRepository.addLog(logEntity);
                logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(logEntity));
            }
            catch (HttpClientErrorException | HttpServerErrorException exception){
                logEntity = logMapper.convertDataToLogEntity(studentAdditionDto, (long) exception.getStatusCode().value(), exception.getMessage());
                logRepository.addLog(logEntity);
                logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(logEntity));
            }
            catch (Exception exception){
                logEntity = logMapper.convertDataToLogEntity(studentAdditionDto, Long.parseLong((String) exception.getMessage().subSequence(0,3)), exception.getMessage());
                logRepository.addLog(logEntity);
                logDisplayDtos.add(logMapper.convertLogEntityToLogDisplayDto(logEntity));
            }
        }
        return logDisplayDtos;
    }

    private String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String token = "";

        if (principal instanceof Jwt) {
            Jwt jwt = (Jwt) principal;
            token = jwt.getTokenValue();
        } else {
            throw new InvalidBearerTokenException("Invalid Bearer token exception!");
        }
        return token;
    }

    public List<StudentDisplayDto> sendStudentsToTopic(List<StudentAdditionDto> studentAdditionDtos){
        List<StudentDisplayDto> studentDisplayDtos = new ArrayList<>();
         for (StudentAdditionDto studentAdditionDto: studentAdditionDtos){
            CompletableFuture<SendResult<String, StudentAdditionDto>> send = template.send("student-info", studentAdditionDto);
            send.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent Student data: " + studentAdditionDtos.toString() + "\n with offset: " + result.getRecordMetadata().offset());
                    studentDisplayDtos.add(studentMapper.convertStudentAdditionDtoToStudentDisplayDto(studentAdditionDto));
                }
                else System.out.println("Unable to send data due to " + ex.getMessage());
            });
        }
        return studentDisplayDtos;
    }

}
