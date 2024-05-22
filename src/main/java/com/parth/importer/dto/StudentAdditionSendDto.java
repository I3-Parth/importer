package com.parth.importer.dto;

import lombok.Data;

@Data
public class StudentAdditionSendDto {
    private Long logId;
    private String name;
    private int age;
    private String email;
    private String city;
}
