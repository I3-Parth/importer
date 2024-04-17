package com.parth.importer.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String city;
    private Long statusCode;
    private String statusMessage;
}
