package com.parth.importer.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {

  private Long id;
  private String name;
  private int age;
  private String email;
  private String city;

}
