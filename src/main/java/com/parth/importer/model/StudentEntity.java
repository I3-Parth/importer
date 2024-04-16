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

  public StudentEntity(String name, int age, String email, String city) {
    this.name = name;
    this.age = age;
    this.email = email;
    this.city = city;
  }
}
