package com.parth.importer.mapstructMapper;

import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.dto.StudentAdditionSendDto;
import com.parth.importer.dto.StudentDisplayDto;
import com.parth.importer.model.LogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "Spring", uses = LogMapper.class)
public interface StudentMapper {

  @Mapping(source = "id", target = "logId")
  @Mapping(source = "name", target = "name")
  @Mapping(source = "age", target = "age")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "city", target = "city")
  StudentAdditionSendDto convertLogEntityToStudentAdditionSendDto(LogEntity logEntities);

}
