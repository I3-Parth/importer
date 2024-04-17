package com.parth.importer.mapstructMapper;

import com.parth.importer.dto.LogDisplayDto;
import com.parth.importer.dto.StudentAdditionDto;
import com.parth.importer.dto.StudentDisplayDto;
import com.parth.importer.model.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LogMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "statusCode", target = "statusCode")
    @Mapping(source = "statusMessage", target = "statusMessage")
    LogDisplayDto convertLogEntityToLogDisplayDto(Log log);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "studentAdditionDto.name", target = "name")
    @Mapping(source = "studentAdditionDto.age", target = "age")
    @Mapping(source = "studentAdditionDto.email", target = "email")
    @Mapping(source = "studentAdditionDto.city", target = "city")
    @Mapping(source = "statusCode", target = "statusCode")
    @Mapping(source = "statusMessage", target = "statusMessage")
    Log convertDataToLogEntity(StudentAdditionDto studentAdditionDto, Long statusCode, String statusMessage);
}
