package com.parth.importer.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Long id;
    private Long studentId;
    private String name;
    private int age;
    private String email;
    private String city;
    private Long statsCode;
    private String statusMessage;
}
