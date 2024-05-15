package com.parth.importer.mapstructMapper;

import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.dto.StudentDisplayDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "Spring")
public interface StudentMapper {

  @Mapping(source = "name", target = "name")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "city", target = "city")
  StudentDisplayDto convertStudentAdditionDtoToStudentDisplayDto(StudentAdditionDto studentAdditionDto);

}
