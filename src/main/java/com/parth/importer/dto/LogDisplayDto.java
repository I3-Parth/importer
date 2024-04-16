package com.parth.importer.dto;

import lombok.Data;

@Data
public class LogDisplayDto {
    private Long id;
    private Long studentId;
    private String name;
    private int age;
    private String email;
    private String city;
    private Long statusCode;
    private String statusMessage;
}
