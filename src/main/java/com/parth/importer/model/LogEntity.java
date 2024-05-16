package com.parth.importer.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String city;
    private Long statusCode;
    private String statusMessage;
}
